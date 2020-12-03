package com.beyond.zjxt.modular.road.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 第三方核量
 * </p>
 *
 * @author beyond
 * @since 2019-12-06
 */
@Data
public class Inspection_company implements Serializable {

    private static final long serialVersionUID=1L;

    private Long third_party_id;

    private Long application_id;

    private int status;

}
