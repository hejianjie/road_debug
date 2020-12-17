package com.beyond.zjxt.modular.road.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author :zjk
 * @Date :Create in 10:29 2020-12-17
 * @Description
 **/
@Data
@TableName("v_summary_of_minor_repair")
public class SummaryOfRepair {
    @TableId(value = "applicationId")
    private int application;

    private Date finshTime;

}
