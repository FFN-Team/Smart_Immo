package com.gangdestrois.smartimmo.domain.event.port;

import com.gangdestrois.smartimmo.domain.event.ProjectNotification;
import com.gangdestrois.smartimmo.domain.event.ProspectNotification;

import java.util.List;

public interface NotificationSpi {
    List<ProjectNotification> findAll();

    Integer save(ProjectNotification event);

    Integer save(ProspectNotification prospectNotification);
}
