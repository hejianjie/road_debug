package com.beyond.zjxt.modular.road.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 单价种类表
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public class Unit_price_type implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "unit_price_type_id", type = IdType.AUTO)
    private Integer unit_price_type_id;

    /**
     * 名称
     */
    private String unit_price_type_name;

    /**
     * 单价
     */
    private BigDecimal unit_price;


    public Integer getUnit_price_type_id() {
        return unit_price_type_id;
    }

    public void setUnit_price_type_id(Integer unit_price_type_id) {
        this.unit_price_type_id = unit_price_type_id;
    }

    public String getUnit_price_type_name() {
        return unit_price_type_name;
    }

    public void setUnit_price_type_name(String unit_price_type_name) {
        this.unit_price_type_name = unit_price_type_name;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    @Override
    public String toString() {
        return "Unit_price_type{" +
        "unit_price_type_id=" + unit_price_type_id +
        ", unit_price_type_name=" + unit_price_type_name +
        ", unit_price=" + unit_price +
        "}";
    }
}
