package com.beyond.zjxt.modular.road.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 国道
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public class National_highway implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "highway_id", type = IdType.AUTO)
    private Integer highway_id;

    /**
     * 国道名称
     */
    private String highway_name;

    /**
     * 总长
     */
    private BigDecimal overall_length;

    /**
     * 所含路段数
     */
    private Integer section_count;

    /**
     * 总桩数
     */
    private Integer stake_count;

    /**
     * 状态：0-良好 1-保养 2-维修
     */
    private Integer status;


    public Integer getHighway_id() {
        return highway_id;
    }

    public void setHighway_id(Integer highway_id) {
        this.highway_id = highway_id;
    }

    public String getHighway_name() {
        return highway_name;
    }

    public void setHighway_name(String highway_name) {
        this.highway_name = highway_name;
    }

    public BigDecimal getOverall_length() {
        return overall_length;
    }

    public void setOverall_length(BigDecimal overall_length) {
        this.overall_length = overall_length;
    }

    public Integer getSection_count() {
        return section_count;
    }

    public void setSection_count(Integer section_count) {
        this.section_count = section_count;
    }

    public Integer getStake_count() {
        return stake_count;
    }

    public void setStake_count(Integer stake_count) {
        this.stake_count = stake_count;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "National_highway{" +
        "highway_id=" + highway_id +
        ", highway_name=" + highway_name +
        ", overall_length=" + overall_length +
        ", section_count=" + section_count +
        ", stake_count=" + stake_count +
        ", status=" + status +
        "}";
    }
}
