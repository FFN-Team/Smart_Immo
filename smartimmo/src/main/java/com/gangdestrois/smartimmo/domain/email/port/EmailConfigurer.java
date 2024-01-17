package com.gangdestrois.smartimmo.domain.email.port;

import java.util.Map;

public interface EmailConfigurer {
    String getEmailHtmlBody(Map<String, Object> templateModel, String templateFile);
}
