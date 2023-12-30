package com.gangdestrois.smartimmo.domain.email.port;

import com.gangdestrois.smartimmo.domain.event.EventType;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

public interface EmailApi {
    void configAndSendEmail(Prospect prospect, EventType eventType) throws Exception;
}
