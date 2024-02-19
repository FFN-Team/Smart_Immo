package com.gangdestrois.smartimmo.domain.email;

import com.gangdestrois.smartimmo.domain.email.port.EmailApi;
import com.gangdestrois.smartimmo.domain.email.port.EmailConfigurer;
import com.gangdestrois.smartimmo.domain.email.port.EmailSender;
import com.gangdestrois.smartimmo.domain.error.ExceptionEnum;
import com.gangdestrois.smartimmo.domain.error.NotFoundException;
import com.gangdestrois.smartimmo.domain.error.UnauthorizedException;
import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectSpi;
import org.springframework.jmx.export.notification.UnableToSendNotificationException;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class EmailManager implements EmailApi {
    private final EmailConfigurer emailConfigurer;
    private final EmailSender emailSender;
    private final ProspectSpi prospectSpi;

    public EmailManager(EmailConfigurer emailConfigurer, EmailSender emailSender, ProspectSpi prospectSpi) {
        this.emailConfigurer = emailConfigurer;
        this.emailSender = emailSender;
        this.prospectSpi = prospectSpi;
    }

    public EmailManager(EmailConfigurer emailConfigurer, ProspectSpi prospectSpi) {
        this.emailConfigurer = emailConfigurer;
        this.emailSender = null;
        this.prospectSpi = prospectSpi;
    }

    @Override
    public void configAndSendEmail(Long prospectId, EventType eventType) throws Exception {
        if (isNull(emailSender))
            throw new NotFoundException(ExceptionEnum.EMAIL_SENDER_NOT_FOUND,
                    "No email sender found.");
        var prospect = prospectSpi.findById(prospectId).orElseThrow(() -> new NotFoundException(ExceptionEnum.PROSPECT_NOT_FOUND,
                String.format("Prospect with id %d not found.", prospectId)));
        if (!prospect.authorizeContactOnSocialMedia())
            throw new UnauthorizedException(ExceptionEnum.CONTACT_ON_SOCIAL_MEDIA_UNAUTHORIZED,
                    String.format("this prospect %s with id %d does not wish to be contacted via social networks.", prospect.getCompleteName(),
                            prospectId));
        String from = "plantefloni@gmail.com";
        String to = prospect.getMail();
        Map<String, Object> templateModel = switch (eventType) {
            case PROSPECT_MAY_BUY_BIGGER_HOUSE, PROJECT_DUE_DATE_APPROACHING -> setTemplateModel(prospect);
        };
        sendEmail(from, to, eventType.emailSubject(), templateModel, eventType.getEmailTemplate());
    }

    private Map<String, Object> setTemplateModel(Prospect prospect) {
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("prospectName", prospect.getCompleteName());
        templateModel.put("name", "Florine");
        templateModel.put("contact", "06 64 32 96 29");
        return templateModel;
    }

    public void sendEmail(String from, String to, String subject, Map<String, Object> templateModel, String templateFile)
            throws Exception {
        var htmlBody = emailConfigurer.getEmailHtmlBody(templateModel, templateFile);
        if (nonNull(emailSender)) emailSender.sendEmail(subject, htmlBody, from, to);
        else throw new UnableToSendNotificationException("emailSender is null");
    }
}
