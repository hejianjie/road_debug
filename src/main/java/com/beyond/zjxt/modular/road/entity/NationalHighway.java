package com.beyond.zjxt.modular.road.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class NationalHighway {

    @Id
    private Integer highwayId;

    private String highwayName;

    private Integer status;

    private BigDecimal overallLength;

}
