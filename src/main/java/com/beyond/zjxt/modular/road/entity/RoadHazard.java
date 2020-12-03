package com.beyond.zjxt.modular.road.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class RoadHazard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roadHazardId;

    /**
     * 巡查情况ID
     */
    private Integer patrolResultId;

    /**
     * 病害位置
     */
    private String position;

    /**
     * 病害情况
     */
    private Integer hazardStatus;

    /**
     * 尺寸类型
     */
    private Integer sizeType;

    /**
     * 具体尺寸
     */
    private String specificSize;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 经度
     */
    private BigDecimal longitude;

    /**
     * 纬度
     */
    private BigDecimal latitude;

    /**
     * 工程量
     */
    private BigDecimal workAmount;

    /**
     * 修复金额
     */
    private BigDecimal costPrice;

    /**
     * 潜在危险
     */
    private String potentialHazard;

    /**
     * 发现时间
     */
    private LocalDateTime detectTime;

    /**
     * 状态：0-未审核 1-已审核
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 巡检员ID
     */
    private Integer userId;
}
