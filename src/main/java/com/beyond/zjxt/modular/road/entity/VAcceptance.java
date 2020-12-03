package com.beyond.zjxt.modular.road.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "v_acceptance")
public class VAcceptance {

    @Id
    private Integer acceptanceId;

    private Long acceptOrganizationId;

    private Integer roadHazardId;

    private Integer hazardStatus;

    private String position;

    private String hazardUnitName;

    private String specificSize;

    private Integer roadStatus;

    private String nationalHighwayName;

    private String roadSectionName;

    private String beginStake;

    private String endStake;

    private LocalDateTime beginTime;

    private LocalDateTime endTime;

    private BigDecimal costPrice;

    private Integer patrolResultId;

    private Integer applicationId;

    private String name;

}
