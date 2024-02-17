package com.gangdestrois.smartimmo.infrastructure.service;

import com.gangdestrois.smartimmo.domain.email.port.EmailSender;
import com.gangdestrois.smartimmo.domain.statusCode.HttpStatusCode;
import com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum;
import com.gangdestrois.smartimmo.infrastructure.rest.error.InternalServerErrorException;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import static com.gangdestrois.smartimmo.domain.tool.ApplicationData.TECHNIMMO;
import static java.util.Objects.nonNull;
import static javax.mail.Message.RecipientType.TO;

@Component
public class GmailApi implements EmailSender {
    private static final Logger log = LogManager.getLogger(GmailApi.class);

    @Autowired
    public GmailApi() {}

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
            if (nonNull(error) && error.getCode() == HttpStatusCode.FORBIDDEN.getCode()) {
                log.error("Unable to send message: " + error);
            } else {
                throw e;
            }
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Gmail initialize() {
        NetHttpTransport httpTransport = new NetHttpTransport();
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        try {
            return new Gmail.Builder(httpTransport, jsonFactory,
                    GoogleApi.getCredentials(httpTransport))
                    .setApplicationName(TECHNIMMO)
                    .build();
        } catch (IOException e) {
            throw new InternalServerErrorException(ExceptionEnum.GOOGLE_CREDENTIALS_ERROR,
                    "error during google api connexion for gmail service.");
        }
    }

    private void setEmailContent(String subject, String message, String senderEmail, String recipientEmail, MimeMessage email) {
        try {
            email.setFrom(new InternetAddress(senderEmail));
            email.addRecipient(TO, new InternetAddress(recipientEmail));
            email.setSubject(subject);
            email.setContent(message, "text/html");
        } catch (MessagingException e) {
            throw new InternalServerErrorException(ExceptionEnum.EMAIL_CONTENT_ERROR,
                    String.format("Error during set email content for sender %s, recipient %s and message : \n'%s.'",
                            senderEmail, recipientEmail, message));
        }
    }
}