package com.beyond.zjxt.modular.road.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AddAppraisalDTO {
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
     * 发现时间
     */
    private LocalDateTime appraisalTime;
    /**
     * 金额
     */
    private BigDecimal costPrice;

    /**
     * 申请表ID
     */
    private Integer applicationId;
    /**
     * 评估单位ID
     */
    private String appraisalOrganisationId;
}
