package com.beyond.zjxt.modular.road.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class RoadSection {

    @Id
    private Integer roadSectionId;

    private String name;

    private BigDecimal overallLength;

    private BigDecimal beginAt;

    private BigDecimal endAt;

    private Integer supervisorId;

    private Integer nationalHighwayId;

}
