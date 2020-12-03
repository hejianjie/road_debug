package com.beyond.zjxt.modular.road.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "stake")
public class IStake {

    @Id
    private Integer stakeId;

    private String name;

    private Integer roadSectionId;

    private BigDecimal stakeLocation;
}
