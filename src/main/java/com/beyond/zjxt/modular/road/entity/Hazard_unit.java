package com.beyond.zjxt.modular.road.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 病害单位表
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public class Hazard_unit implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "hazard_unit_id", type = IdType.AUTO)
    private Integer hazard_unit_id;

    /**
     * 体积、面积、长度、个数
     */
    private String hazard_unit_name;


    public Integer getHazard_unit_id() {
        return hazard_unit_id;
    }

    public void setHazard_unit_id(Integer hazard_unit_id) {
        this.hazard_unit_id = hazard_unit_id;
    }

    public String getHazard_unit_name() {
        return hazard_unit_name;
    }

    public void setHazard_unit_name(String hazard_unit_name) {
        this.hazard_unit_name = hazard_unit_name;
    }

    @Override
    public String toString() {
        return "Hazard_unit{" +
        "hazard_unit_id=" + hazard_unit_id +
        ", hazard_unit_name=" + hazard_unit_name +
        "}";
    }
}
