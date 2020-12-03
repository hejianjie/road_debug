package com.beyond.zjxt.modular.road.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "v_notification")
public class VNotification {

    @Id
    private Integer notificationId;

    private String title;

    private String publisher;

    private String content;

    private LocalDateTime publishTime;
}
