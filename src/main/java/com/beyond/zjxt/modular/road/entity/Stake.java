package com.beyond.zjxt.modular.road.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 桩
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Data
public class Stake implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "stake_id", type = IdType.AUTO)
    private Integer stake_id;

    /**
     * 桩名称
     */
    private String name;

    /**
     * 路段ID
     */
    private Integer road_section_id;


    public Integer getStake_id() {
        return stake_id;
    }

    public void setStake_id(Integer stake_id) {
        this.stake_id = stake_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRoad_section_id() {
        return road_section_id;
    }

    public void setRoad_section_id(Integer road_section_id) {
        this.road_section_id = road_section_id;
    }

    @Override
    public String toString() {
        return "Stake{" +
        "stake_id=" + stake_id +
        ", name=" + name +
        ", road_section_id=" + road_section_id +
        "}";
    }
}
