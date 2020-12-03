package com.beyond.zjxt.modular.road.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "v_patrol_result")
public class VPatrolResult {

    @Id
    private Integer patrolResultId;

    private String nationalHighwayName;

    private String roadSectionName;

    private String beginStake;

    private String endStake;

    private String inspector;

    private String supervisorName;

    private String patrolCar;

    private LocalDateTime beginTime;

    private LocalDateTime endTime;

    private Integer issueCount;

    private Integer inspectorId;

    private Integer highwayId;

    private Integer roadSectionId;

    private Integer stakeBeginId;

    private Integer stakeEndId;

}
