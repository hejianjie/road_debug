package com.beyond.zjxt.modular.road.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 路段表
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public class Road_section implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "road_section_id", type = IdType.AUTO)
    private Integer road_section_id;

    /**
     * 路段名称
     */
    private String name;

    /**
     * 总长
     */
    private BigDecimal overall_length;

    /**
     * 总桩数
     */
    private Integer stake_count;

    /**
     * 起始位置
     */
    private BigDecimal begin_at;

    /**
     * 结束位置
     */
    private BigDecimal end_at;

    /**
     * 管理单位ID
     */
    private Integer supervisor_id;

    /**
     * 国道ID
     */
    private Integer national_highway_id;


    public Integer getRoad_section_id() {
        return road_section_id;
    }

    public void setRoad_section_id(Integer road_section_id) {
        this.road_section_id = road_section_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getOverall_length() {
        return overall_length;
    }

    public void setOverall_length(BigDecimal overall_length) {
        this.overall_length = overall_length;
    }

    public Integer getStake_count() {
        return stake_count;
    }

    public void setStake_count(Integer stake_count) {
        this.stake_count = stake_count;
    }

    public BigDecimal getBegin_at() {
        return begin_at;
    }

    public void setBegin_at(BigDecimal begin_at) {
        this.begin_at = begin_at;
    }

    public BigDecimal getEnd_at() {
        return end_at;
    }

    public void setEnd_at(BigDecimal end_at) {
        this.end_at = end_at;
    }

    public Integer getSupervisor_id() {
        return supervisor_id;
    }

    public void setSupervisor_id(Integer supervisor_id) {
        this.supervisor_id = supervisor_id;
    }

    public Integer getNational_highway_id() {
        return national_highway_id;
    }

    public void setNational_highway_id(Integer national_highway_id) {
        this.national_highway_id = national_highway_id;
    }

    @Override
    public String toString() {
        return "Road_section{" +
        "road_section_id=" + road_section_id +
        ", name=" + name +
        ", overall_length=" + overall_length +
        ", stake_count=" + stake_count +
        ", begin_at=" + begin_at +
        ", end_at=" + end_at +
        ", supervisor_id=" + supervisor_id +
        ", national_highway_id=" + national_highway_id +
        "}";
    }
}
