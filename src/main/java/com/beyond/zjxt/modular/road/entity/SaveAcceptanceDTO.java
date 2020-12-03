package com.beyond.zjxt.modular.road.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SaveAcceptanceDTO {
    /**
     * 申请表ID
     */
    private Integer applicationId;

    /**
     * 完成日期
     */
    private LocalDateTime finish_time;

    /**
     * 审核日期
     */
    private LocalDateTime audit_time;

    private Integer unit;

    /**
     * 具体尺寸
     */
    private String specificSize;

    /**
     * 工程量
     */
    private BigDecimal workAmount;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 金额
     */
    private BigDecimal costPrice;

    /**
     * 初审结果： 0-未通过 1-已通过
     */
    private Integer firstAuditResult;

    /**
     * 初审人1
     */
    private Integer auditor_one;

    /**
     * 初审人2
     */
    private Integer auditor_two;

    /**
     * 初审人3
     */
    private Integer auditor_three;

    /**
     * 验收核算单位 县区|市级|第三方
     */
    private Integer acceptOrganisation;

    /**
     * 状态：0-初核 1-复核
     */
    private Integer status;

    /**
     * 初核日期
     */
    private String first_audit_date;
    /**
     * 扣款金额
     */
    private BigDecimal deduction;
    /**
     * 复合金额
     */
    private BigDecimal secondAuditPrice;
    /**
     * 核量描述
     */
    private String description;

    private String roleId;
}
