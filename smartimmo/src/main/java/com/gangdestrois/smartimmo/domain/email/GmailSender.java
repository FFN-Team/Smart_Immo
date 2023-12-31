package com.gangdestrois.smartimmo.domain.email;

import com.gangdestrois.smartimmo.domain.statusCode.HttpStatusCode;
import com.gangdestrois.smartimmo.infrastructure.apis.GoogleApi;
import com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException.EmailContentException;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import static com.gangdestrois.smartimmo.domain.ApplicationData.TECHNIMMO;
import static com.google.api.services.gmail.GmailScopes.GMAIL_SEND;
import static javax.mail.Message.RecipientType.TO;

public class GmailSender implements EmailSender {
    private static final Logger log = LogManager.getLogger(GmailSender.class);
    private final GoogleApi googleApi;

    public GmailSender() {
        this.googleApi = new GoogleApi();
    }

    public void sendEmail(String subject, String message, String senderEmail, String recipientEmail) throws GoogleJsonResponseException {
        Gmail service = initialize();

        Session session = Session.getDefaultInstance(new Properties(), null);
        MimeMessage email = new MimeMessage(session);
        setEmailContent(subject, message, senderEmail, recipientEmail, email);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            email.writeTo(buffer);
            byte[] rawMessageBytes = buffer.toByteArray();
            String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
            Message msg = new Message();
            msg.setRaw(encodedEmail);


            msg = service.users().messages().send("me", msg).execute();
            log.info("Message id: " + msg.getId());
            log.info(msg.toPrettyString());
        } catch (GoogleJsonResponseException e) {
            GoogleJsonError error = e.getDetails();
            if (error.getCode() == HttpStatusCode.FORBIDDEN.getCode()) {
                log.error("Unable to send message: " + error);
            } else {
                throw e;
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Gmail initialize() {
        HttpTransport httpTransport = new NetHttpTransport();
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        return new Gmail.Builder(httpTransport, jsonFactory,
                googleApi.getCredentials(Set.of(GMAIL_SEND), httpTransport, jsonFactory))
                .setApplicationName(TECHNIMMO)
                .build();
    }

    private void setEmailContent(String subject, String message, String senderEmail, String recipientEmail, MimeMessage email) {
        try {
            email.setFrom(new InternetAddress(senderEmail));
            email.addRecipient(TO, new InternetAddress(recipientEmail));
            email.setSubject(subject);
            email.setContent(message, "text/html");
        } catch (MessagingException e) {
            throw new EmailContentException(senderEmail, recipientEmail, message, "Error during set email content.");
        }
    }
}