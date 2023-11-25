package com.gangdestrois.smartimmo.domain.notification.port;

import com.gangdestrois.smartimmo.domain.notification.ProjectNotification;

import java.util.List;

public interface NotificationSpi {
    List<ProjectNotification> findAll();
}
