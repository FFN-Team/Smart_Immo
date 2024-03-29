package com.gangdestrois.smartimmo.infrastructure.service;

import com.gangdestrois.smartimmo.domain.email.port.EmailConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;


@Component
public class ThymeleafConfigurer implements EmailConfigurer {
    private final SpringTemplateEngine thymeleafTemplateEngine;

    @Autowired
    public ThymeleafConfigurer(SpringTemplateEngine springTemplateEngine) {
        this.thymeleafTemplateEngine = springTemplateEngine;
    }

    @Override
    public String getEmailHtmlBody(Map<String, Object> templateModel, String templateFile) {
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);
        return thymeleafTemplateEngine.process(templateFile, thymeleafContext);
    }
}
