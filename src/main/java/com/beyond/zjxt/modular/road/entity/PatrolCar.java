package com.beyond.zjxt.modular.road.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class PatrolCar {

    @Id
    @TableId(value = "car_number")
    private String  carNumber;

     private Long deptId;
}
