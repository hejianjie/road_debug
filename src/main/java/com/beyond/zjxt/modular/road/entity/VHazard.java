package com.beyond.zjxt.modular.road.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @Author: RaymondZhao
 * @Datetime: 2019/12/12 15:10
 * @Description:
 */
@Data
@Entity
@Table(name = "v_hazard")
public class VHazard {

    @Id
    private Integer roadHazardId;

    private Integer patrolResultId;

    private Integer userId;

    private String position;

    private String hazardType;

    private String hazardUnitName;

    private String specificSize;

    private LocalDateTime detectTime;

    private String description;
}
