package com.beyond.zjxt.modular.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.poi.hpsf.Decimal;

import java.math.BigDecimal;
@Data
@TableName("national_highway")
public class NationalHighWay {

    private static final long serialVersionUID = 1L;

    /***
     * 主键id,国道id
     */
    @TableId(value = "highway_id", type = IdType.ID_WORKER)
    private int highwayId;

    /***
     * 国道名称
     */
    @TableField("highway_name")
    private String highwayName;

    /***
     * 国道总长
     */
    @TableField("overall_Length")
    private BigDecimal overallLength;

//    /***
//     * 国道分段数
//     */
//    @TableField("section_count")
//    private int sectionCount;

//    /***
//     * 国道总桩数
//     */
//    private int stakeCount;

    /***
     * 国道状态
     */
    @TableField("status")
    private int status;

    public int getHighwayId() {
        return highwayId;
    }

    public void setHighwayId(int highwayId) {
        this.highwayId = highwayId;
    }

    public String getHighwayName() {
        return highwayName;
    }

    public void setHighwayName(String highwayName) {
        this.highwayName = highwayName;
    }

    public BigDecimal getOverallLength() {
        return overallLength;
    }

    public void setOverallLength(BigDecimal overallLength) {
        this.overallLength = overallLength;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
