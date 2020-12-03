package com.beyond.zjxt.modular.road.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author :zjk
 * @Date :Create in 16:56 2019-12-06
 * @Description
 **/
public class supremoLimitmoney implements Serializable {
    private static final long serialVersionUID=1L;
    /**
     * 主键
     */
    @TableId(value = "application_id", type = IdType.AUTO)
    private Integer id;

    private Long city_supremo_id;

    private BigDecimal limit_money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCity_supremo_id() {
        return city_supremo_id;
    }

    public void setCity_supremo_id(Long city_supremo_id) {
        this.city_supremo_id = city_supremo_id;
    }

    public BigDecimal getLimit_money() {
        return limit_money;
    }

    public void setLimit_money(BigDecimal limit_money) {
        this.limit_money = limit_money;
    }

    @Override
    public String toString() {
        return "supremoLimitmoney{" +
                "id=" + id +
                ", city_supremo_id=" + city_supremo_id +
                ", limit_money=" + limit_money +
                '}';
    }
}
