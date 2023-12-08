package com.gangdestrois.smartimmo.domain.event.port;

import com.gangdestrois.smartimmo.domain.event.ProjectNotification;

import java.util.List;
import java.util.Optional;

public interface NotificationSpi {
    List<ProjectNotification> findAll();

    Integer save(ProjectNotification event);

    Optional<ProjectNotification> findProjectNotificationById(Integer projectNotificationId);
}
