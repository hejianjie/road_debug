package com.beyond.zjxt.modular.road.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "patrol_result")
public class IPatrolResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patrolResultId;

    /**
     * 路段ID
     */
    private Integer roadSectionId;

    /**
     * 巡检结构ID
     */
    private Integer patrolOrganizationId;

    /**
     * 路段状态 0 良好 1 出现问题
     */
    private Integer status = 0;

    /**
     * 问题描述
     */
    private String description;

    /**
     * 巡检车辆
     */
    private String patrolCar;

    /**
     * 巡检员
     */
    private Integer inspectorId;

    /**
     * 巡检开始时间
     */
    private Date beginTime;

    /**
     * 巡检结束时间
     */
    private Date endTime;

    /**
     * 巡检省道ID
     */
    private Integer nationalHighwayId;

    /**
     * 巡检开始时的桩ID
     */
    private Integer stakeBeginId;

    /**
     * 巡检结束时的桩ID
     */
    private Integer stakeEndId;
}
