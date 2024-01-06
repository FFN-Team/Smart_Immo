package com.gangdestrois.smartimmo.domain.email;

import com.gangdestrois.smartimmo.domain.email.port.EmailApi;
import com.gangdestrois.smartimmo.domain.event.EventType;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectSpi;
import com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException.ContactOnSocialMediaUnauthorizedException;
import com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException.EmailSenderNotFoundExceptionException;
import com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException.NotFoundException;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class EmailManager implements EmailApi {
    private final SpringTemplateEngine thymeleafTemplateEngine;
    private final EmailSender emailSender;
    private final ProspectSpi prospectSpi;

    public EmailManager(SpringTemplateEngine thymeleafTemplateEngine, EmailSender emailSender, ProspectSpi prospectSpi) {
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
        this.emailSender = emailSender;
        this.prospectSpi = prospectSpi;
    }

    public EmailManager(SpringTemplateEngine thymeleafTemplateEngine, ProspectSpi prospectSpi) {
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
        this.emailSender = null;
        this.prospectSpi = prospectSpi;
    }

    @Override
    public void configAndSendEmail(Long prospectId, EventType eventType) throws Exception {
        if (isNull(emailSender))
            throw new EmailSenderNotFoundExceptionException("EmailSender", "No email sender found.");
        var prospect = prospectSpi.findById(prospectId).orElseThrow(() -> new NotFoundException(prospectId, "prospect"));
        if (!prospect.authorizeContactOnSocialMedia())
            throw new ContactOnSocialMediaUnauthorizedException(prospectId, "contact",
                    "this prospect does not wish to be contacted via social networks.");
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
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);
        String htmlBody = thymeleafTemplateEngine.process(templateFile, thymeleafContext);
        emailSender.sendEmail(subject, htmlBody, from, to);
    }
}
