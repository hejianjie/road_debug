package com.beyond.zjxt.modular.road.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "v_appraisal_done")
public class VAppraisalDone {
    @Id
    private Integer appraisalId;

    private String nationalHighwayName;

    private String roadSectionName;

    private String beginStake;

    private String endStake;

    private String position;

    private String roadSurface;

    private String square;

    private String specificSize;

    private Integer hazardStatus;

    private BigDecimal costPrice;

    private Integer roadStatus;

    private Long thirdPartyId;

    private Integer evaluated;

    private Integer applicationId;

    private Integer roadHazardId;

    private Integer patrolResultId;

    private LocalDateTime beginTime;

    private LocalDateTime endTime;
}
