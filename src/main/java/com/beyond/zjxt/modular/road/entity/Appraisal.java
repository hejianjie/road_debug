package com.beyond.zjxt.modular.road.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 评估表
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */

@Data
public class Appraisal implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "appraisal_id", type = IdType.AUTO)
    private Integer appraisal_id;

    /**
     * 申请表ID
     */
    private Integer application_id;

    /**
     * 评估时间
     */
    private LocalDateTime appraisal_time;

    /**
     * 评估单位ID
     */
    private Long appraisal_organisation_id;

    /**
     * 单位
     */
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
     * 扣款金额
     */
    private BigDecimal deduction;

    /**
     * 复核金额
     */
    private BigDecimal second_audit_price;

    /**
     * 复核结果：0-未通过 1-已通过
     */
    private Integer second_audit_result;

    /**
     * 审核人1
     */
    private Integer auditor_one;

    /**
     * 审核人2
     */
    private Integer auditor_two;

    /**
     * 审核照片
     */
    private String img;

    /**
     *评估备注
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
     * 病害位置
     */
    private String position;

    /**
     * 病害情况
     */
    private Integer hazardStatus;
    /**
     * 巡查情况ID
     */
    private Integer patrolResultId;

}
