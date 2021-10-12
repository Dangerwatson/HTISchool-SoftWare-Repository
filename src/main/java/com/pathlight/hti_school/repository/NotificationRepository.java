package com.pathlight.hti_school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pathlight.hti_school.entities.Notifications;

public interface NotificationRepository extends JpaRepository<Notifications, Long> {

}
