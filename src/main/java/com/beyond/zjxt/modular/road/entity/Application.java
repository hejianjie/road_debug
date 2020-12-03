package com.beyond.zjxt.modular.road.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 申请表
 * </p>
 *
 * @author beyond
 * @since 2019-11-24
 */
public class Application implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "application_id", type = IdType.AUTO)
    private Integer application_id;

    /**
     * 病害状况
     */
    private int road_hazard;

    /**
     * 申请时间
     */
    private LocalDateTime apply_time;

    /**
     * 申请人
     */
    private Integer applicant;

    /**
     * 状态 13种
     */
    private Integer status;

    /**
     * 反馈意见
     */
    private String feedback;

    /**
     * 建设性质选项
     */
    private Integer type_selection;

    /**
     * 建设项目名称
     */
    private String project_name;

    /**
     * 细目名称
     */
    private String detail_name;

    /**
     * 申请单位
     */
    private Integer organization_id;

    /**
     * 具体尺寸
     */
    private BigDecimal specific_size;

    /**
     * 工程量
     */
    private BigDecimal work_amount;

    /**
     * 单价
     */
    private BigDecimal unit_price;

    /**
     * 单价种类
     */
    private Integer unit_price_type;

    /**
     * 作业频率
     */
    private String work_frequency;

    /**
     * 预估费用
     */
    private BigDecimal appraisal_cost;

    /**
     * 预估完成时间
     */
    private String estimated_finish_time;

    /**
     * 县区申报人
     */
    private Integer county_applicant;

    /**
     * 审核人1
     */
    private Integer auditor_one;

    /**
     * 审核人2
     */
    private Integer auditor_two;

    /**
     * 市区审核人1
     */
    private Integer city_auditor_one;

    /**
     * 市区审核人2
     */
    private Integer city_auditor_two;

    /**
     * 市区审核人3
     */
    private Integer city_auditor_three;

    /**
     * 市区审核人4
     */
    private Integer city_auditor_four;

    /**
     * 市级审核人意见
     */
    private String auditor_opinion;

    /**
     * 完成日期
     */
    private LocalDateTime finish_timed;

    /**
     * 初核日期
     */
    private LocalDateTime audit_time;

    /**
     * 完成时限
     */
    private int estimated_finish_duration;

    /**
     * 审核人1意见
     */
    private String auditor_one_result;

    /**
     * 审核人2意见
     */
    private String auditor_two_result;

    /**
     * 初审人
     */
    private int first_auditor;

    /**
     * 初审人意见
     */
    private String first_auditor_result;

    /**
     * 初审人意见
     */
    private Integer dateTime;


    public Integer getApplication_id() {
        return application_id;
    }

    public void setApplication_id(Integer application_id) {
        this.application_id = application_id;
    }

    public LocalDateTime getApply_time() {
        return apply_time;
    }

    public void setApply_time(LocalDateTime apply_time) {
        this.apply_time = apply_time;
    }

    public Integer getApplicant() {
        return applicant;
    }

    public void setApplicant(Integer applicant) {
        this.applicant = applicant;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Integer getType_selection() {
        return type_selection;
    }

    public void setType_selection(Integer type_selection) {
        this.type_selection = type_selection;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getDetail_name() {
        return detail_name;
    }

    public void setDetail_name(String detail_name) {
        this.detail_name = detail_name;
    }

    public Integer getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }

    public BigDecimal getSpecific_size() {
        return specific_size;
    }

    public void setSpecific_size(BigDecimal specific_size) {
        this.specific_size = specific_size;
    }

    public BigDecimal getWork_amount() {
        return work_amount;
    }

    public void setWork_amount(BigDecimal work_amount) {
        this.work_amount = work_amount;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public Integer getUnit_price_type() {
        return unit_price_type;
    }

    public void setUnit_price_type(Integer unit_price_type) {
        this.unit_price_type = unit_price_type;
    }

    public String getWork_frequency() {
        return work_frequency;
    }

    public void setWork_frequency(String work_frequency) {
        this.work_frequency = work_frequency;
    }

    public BigDecimal getAppraisal_cost() {
        return appraisal_cost;
    }

    public void setAppraisal_cost(BigDecimal appraisal_cost) {
        this.appraisal_cost = appraisal_cost;
    }

    public Integer getCounty_applicant() {
        return county_applicant;
    }

    public void setCounty_applicant(Integer county_applicant) {
        this.county_applicant = county_applicant;
    }

    public Integer getAuditor_one() {
        return auditor_one;
    }

    public void setAuditor_one(Integer auditor_one) {
        this.auditor_one = auditor_one;
    }

    public Integer getAuditor_two() {
        return auditor_two;
    }

    public void setAuditor_two(Integer auditor_two) {
        this.auditor_two = auditor_two;
    }

    public Integer getCity_auditor_one() {
        return city_auditor_one;
    }

    public void setCity_auditor_one(Integer city_auditor_one) {
        this.city_auditor_one = city_auditor_one;
    }

    public Integer getCity_auditor_two() {
        return city_auditor_two;
    }

    public void setCity_auditor_two(Integer city_auditor_two) {
        this.city_auditor_two = city_auditor_two;
    }

    public Integer getCity_auditor_three() {
        return city_auditor_three;
    }

    public void setCity_auditor_three(Integer city_auditor_three) {
        this.city_auditor_three = city_auditor_three;
    }

    public Integer getCity_auditor_four() {
        return city_auditor_four;
    }

    public void setCity_auditor_four(Integer city_auditor_four) {
        this.city_auditor_four = city_auditor_four;
    }

    public String getAuditor_opinion() {
        return auditor_opinion;
    }

    public void setAuditor_opinion(String auditor_opinion) {
        this.auditor_opinion = auditor_opinion;
    }

    public LocalDateTime getFinish_timed() {
        return finish_timed;
    }

    public void setFinish_timed(LocalDateTime finish_timed) {
        this.finish_timed = finish_timed;
    }

    public LocalDateTime getAudit_time() {
        return audit_time;
    }

    public void setAudit_time(LocalDateTime audit_time) {
        this.audit_time = audit_time;
    }

    public String getAuditor_one_result() {
        return auditor_one_result;
    }

    public void setAuditor_one_result(String auditor_one_result) {
        this.auditor_one_result = auditor_one_result;
    }

    public String getAuditor_two_result() {
        return auditor_two_result;
    }

    public void setAuditor_two_result(String auditor_two_result) {
        this.auditor_two_result = auditor_two_result;
    }

    @Override
    public String toString() {
        return "Application{" +
        "application_id=" + application_id +
        ", road_hazard=" + road_hazard +
        ", apply_time=" + apply_time +
        ", applicant=" + applicant +
        ", status=" + status +
        ", feedback=" + feedback +
        ", type_selection=" + type_selection +
        ", project_name=" + project_name +
        ", detail_name=" + detail_name +
        ", organization_id=" + organization_id +
        ", specific_size=" + specific_size +
        ", work_amount=" + work_amount +
        ", unit_price=" + unit_price +
        ", unit_price_type=" + unit_price_type +
        ", work_frequency=" + work_frequency +
        ", appraisal_cost=" + appraisal_cost +
        ", estimated_finish_time=" + estimated_finish_time +
        ", county_applicant=" + county_applicant +
        ", auditor_one=" + auditor_one +
        ", auditor_two=" + auditor_two +
        ", city_auditor_one=" + city_auditor_one +
        ", city_auditor_two=" + city_auditor_two +
        ", city_auditor_three=" + city_auditor_three +
        ", city_auditor_four=" + city_auditor_four +
        ", auditor_opinion=" + auditor_opinion +
        ", finish_timed=" + finish_timed +
        ", audit_time=" + audit_time +
        ", estimated_finish_duration=" + estimated_finish_duration +
        ", auditor_one_result=" + auditor_one_result +
        ", auditor_two_result=" + auditor_two_result +
        "}";
    }
}
