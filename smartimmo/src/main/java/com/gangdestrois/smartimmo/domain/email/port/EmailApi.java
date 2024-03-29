package com.gangdestrois.smartimmo.domain.email.port;

import com.gangdestrois.smartimmo.domain.event.enums.EventType;

public interface EmailApi {
    void configAndSendEmail(Long prospectId, EventType eventType) throws Exception;
}
