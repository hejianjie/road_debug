package com.beyond.zjxt.modular.road.repository;

import com.beyond.zjxt.modular.road.entity.VNotification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VNotificationRepository extends JpaRepository<VNotification, Integer> {
    Page<VNotification> findAll(Pageable pageable);
}
