package com.beyond.zjxt.modular.road.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 延期申请表
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public class Deferral_application implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "deferral_application_id", type = IdType.AUTO)
    private Integer deferral_application_id;

    /**
     * 申请表ID
     */
    private Integer application_id;

    /**
     * 延期原因
     */
    private String deferral_reason;

    /**
     * 申请日期
     */
    private LocalDateTime apply_time;

    /**
     * 延期日期
     */
    private LocalDateTime deferral_time;

    /**
     * 初审结果 0-未通过 1-通过
     */
    private Integer city_review;

    /**
     * 市级初审意见
     */
    private String review_opinion;


    public Integer getDeferral_application_id() {
        return deferral_application_id;
    }

    public void setDeferral_application_id(Integer deferral_application_id) {
        this.deferral_application_id = deferral_application_id;
    }

    public Integer getApplication_id() {
        return application_id;
    }

    public void setApplication_id(Integer application_id) {
        this.application_id = application_id;
    }

    public String getDeferral_reason() {
        return deferral_reason;
    }

    public void setDeferral_reason(String deferral_reason) {
        this.deferral_reason = deferral_reason;
    }

    public LocalDateTime getApply_time() {
        return apply_time;
    }

    public void setApply_time(LocalDateTime apply_time) {
        this.apply_time = apply_time;
    }

    public LocalDateTime getDeferral_time() {
        return deferral_time;
    }

    public void setDeferral_time(LocalDateTime deferral_time) {
        this.deferral_time = deferral_time;
    }

    public Integer getCity_review() {
        return city_review;
    }

    public void setCity_review(Integer city_review) {
        this.city_review = city_review;
    }

    public String getReview_opinion() {
        return review_opinion;
    }

    public void setReview_opinion(String review_opinion) {
        this.review_opinion = review_opinion;
    }

    @Override
    public String toString() {
        return "Deferral_application{" +
        "deferral_application_id=" + deferral_application_id +
        ", application_id=" + application_id +
        ", deferral_reason=" + deferral_reason +
        ", apply_time=" + apply_time +
        ", deferral_time=" + deferral_time +
        ", city_review=" + city_review +
        ", review_opinion=" + review_opinion +
        "}";
    }
}
