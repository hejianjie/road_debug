package com.beyond.zjxt.modular.road.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.Application;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 申请表 Mapper 接口
 * </p>
 *
 * @author lhd
 * @since 2019-11-21
 */
@Mapper
public interface ApplicationMapper extends BaseMapper<Application> {
    List<Map<String, Object>> selectOne(int applicationId);

    Application selectbyRId(int roadHazardId);

    Integer getStatus(Integer applicationId);

    int changeStatus(@Param("status")int status,@Param("applicationId")int applicationId);

    List<Map<String, Object>> selectByRoadHazardId(@Param("roadHazardId")int roadHazardId);

    Page<Map<String,Object>> selectCityDeptUnderAudit(@Param("page") Page page, @Param("role") String role);

    Page<Map<String, Object>> selectCityExecutiveUnderAudit(@Param("page") Page page);

    Page<Map<String, Object>> selectHistory(@Param("page") Page page,@Param("userId") Long userId);

    int addOne(@Param("roadHazardId") int roadHazardId, @Param("applyTime") Date applyTime,  @Param("userId") int userId,@Param("construct_type") int construct_type, @Param("construct_project") int construct_project,@Param("construct_project_detail") int construct_project_detail, @Param("hazard_unit") int hazard_unit,@Param("specificSize") String specificSize,
               @Param("work_amount") float work_amount, @Param("unit_price") float unit_price, @Param("unit_price_type") int unit_price_type, @Param("work_frequency") String work_frequency, @Param("appraisal_cost") float appraisal_cost, @Param("date") String date,@Param("estimated_finish_time") int estimated_finish_time,@Param("Time") Date Time);

    int updateCityDeptAuditResult(@Param("userId")Long userId,@Param("role") String role, @Param("auditSuggestion") String auditSuggestion, @Param("feedback") String feedback, @Param("applicationId") Integer applicationId, @Param("auditDate") Date auditDate);

    int updateCityExecutiveAuditResult(@Param("userId")Long userId,@Param("role") String role, @Param("auditSuggestion") String auditSuggestion, @Param("feedback") String feedback, @Param("applicationId") Integer applicationId, @Param("auditDate") Date auditDate);

    Page<Map<String,Object>> cityDeptAuditHistory(@Param("page") Page page, @Param("role") String role);

    Page<Map<String, Object>> cityExecutiveHistory(@Param("page") Page page);

    int firstAuditor(@Param("firstAuditorId")int firstAuditorId, @Param("firstAuditorResult")String firstAuditorResult, @Param("applicationId")int applicationId);

    int countyAuditor(@Param("role") String role,@Param("auditorId") int auditorId,@Param("auditorResult") String auditorResult,@Param("applicationId") int applicationId);

    Page<Map<String,Object>>ReffeAuditerUnderList(@Param("page") Page page,@Param("userId") Long userId);

    Map<String, Object> selectByApplicationId(@Param("applicationId") Integer applicationId);

    Page<Map<String, Object>> supremoUnderAuditList(@Param("page") Page page);

    void updateSupremoStatus(@Param("userId") Long userId,@Param("status")String status,@Param("auditDate") Date auditDate,@Param("applicationId")Integer applicationId);


    void supremoAutoAudit(@Param("userId")Long userId,@Param("auditDate")Date auditDate,@Param("limitMoney")BigDecimal limitMoney);

    Page<Map<String, Object>> supremoAuditHistory(@Param("page") Page page);


    int deleteApplication(@Param("id") int id);

    void autoAuditAfterExeAudit(@Param("limitMoney")BigDecimal limitMoney,@Param("applicationId")Integer applicationId,@Param("auditDate")Date auditDate);

    Page<Map<String, Object>> ReffAuditHistory(@Param("page") Page page, @Param("userId") Long userId);

    Map<String, Object> getApplyDate(@Param("roadHazardId")Integer roadHazardId);

    List<Map<String,Object>> getView(@Param("applicationId") int applicationId);

    List<Map<String, Object>> getSummaryOfRepair(@Param("yearMonth") Date yearMonth);
}
