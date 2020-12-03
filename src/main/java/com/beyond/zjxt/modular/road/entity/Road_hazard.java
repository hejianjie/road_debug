package com.beyond.zjxt.modular.road.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 巡检路段病害
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Data
public class Road_hazard implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
   @TableId(value = "road_hazard_id", type = IdType.AUTO)
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

    private int user_id;

}
