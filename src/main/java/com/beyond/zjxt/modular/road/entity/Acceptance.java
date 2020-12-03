package com.beyond.zjxt.modular.road.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 验收核算表
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Data
public class Acceptance implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "acceptance_id", type = IdType.AUTO)
    private Integer acceptance_id;

    /**
     * 申请表ID
     */
    private Integer application_id;

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
    private String specific_size;

    /**
     * 工程量
     */
    private BigDecimal work_amount;

    /**
     * 单价
     */
    private BigDecimal unit_price;

    /**
     * 金额
     */
    private BigDecimal cost_price;

    /**
     * 初审结果： 0-未通过 1-已通过
     */
    private Integer first_audit_result;

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
    private Integer accept_organisation;

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
    private BigDecimal second_audit_price;
    /**
     * 核量描述
     */
    private String description;

    private Long roleId;
}
