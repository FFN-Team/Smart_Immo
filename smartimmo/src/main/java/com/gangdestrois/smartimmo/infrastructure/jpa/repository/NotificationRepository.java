package com.gangdestrois.smartimmo.infrastructure.jpa.repository;

import com.gangdestrois.smartimmo.infrastructure.jpa.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.validation.annotation.Validated;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {
    @Override
    List<NotificationEntity> findAll();
}
