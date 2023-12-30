package com.gangdestrois.smartimmo.domain.email;

import com.gangdestrois.smartimmo.domain.email.port.EmailApi;
import com.gangdestrois.smartimmo.domain.event.EventType;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class EmailManager implements EmailApi {
    private final SpringTemplateEngine thymeleafTemplateEngine;
    private final EmailSender emailSender;

    public EmailManager(SpringTemplateEngine thymeleafTemplateEngine, EmailSender emailSender) {
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
        this.emailSender = emailSender;
    }

    @Override
    public void configAndSendEmail(Prospect prospect, EventType eventType) throws Exception {
        if (!prospect.authorizeContactOnSocialMedia()) return;
        String from = "plantefloni@gmail.com";
        String to = prospect.getMail();
        Map<String, Object> templateModel = setTemplateModel(prospect);
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
