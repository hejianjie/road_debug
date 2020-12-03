package com.beyond.zjxt.modular.road.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 巡检情况表
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Data
public class PatrolResult implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "patrol_result_id", type = IdType.AUTO)
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
    private Integer status;

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
