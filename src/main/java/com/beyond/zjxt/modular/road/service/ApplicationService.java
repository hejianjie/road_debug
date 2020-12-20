package com.beyond.zjxt.modular.road.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.Application;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import java.util.Date;


/**
 * <p>
 * 申请表 服务类
 * </p>
 *
 * @author lhd
 * @since 2019-11-21
 */
public interface ApplicationService extends IService<Application> {
    Object selectOne(int applicationId);

    Application selectbyRId(int id);

    Integer getStatus(Integer applicationId);

    int changeStatus(int status,int applicationId);

    List<Map<String, Object>> selectByRoadHazardId(@Param("roadHazardId")int roadHazardId);

    Page<Map<String,Object>> selectCityDeptUnderAudit(String role);

    Page<Map<String, Object>> selectCityExecutiveUnderAudit();

    Page<Map<String, Object>> selectHistory(Long userId);

    int addOne(int roadHazardId, int userId, int construct_type, int construct_project, int construct_project_detail, int hazard_unit, String specificSize,
               float work_amount, float unit_price, int unit_price_type, String work_frequency, float appraisal_cost, String date, int estimated_finish_time, Date time);

    int updateCityDeptAuditResult(Long userId,String role, String auditSuggestion, String feedback, Integer applicationId, Date auditDate);

    int updateCityExecutiveAuditResult(Long userId,String role, String auditSuggestion, String feedback, Integer applicationId, Date auditDate);


    Page<Map<String,Object>> cityDeptAuditHistory(String role);

    Page<Map<String,Object>> cityExecutiveHistory();

    int firstAuditor(int firstAuditorId,String firstAuditorResult,int applicationId);

    int countyAuditor(String role,int auditorId,String auditorResult,int applicationId);

    Page<Map<String,Object>>ReffeAuditerUnderList(Long userId);

    Map<String, Object>selectByApplicationId(Integer applicationId);

    Page<Map<String, Object>> supremoUnderAuditList();

    void updateSupremoStatus(Long userId,String status,Date auditDate,Integer applicationId);

    void supremoAutoAudit(Long userId,Date auditDate,BigDecimal limitMoney);

    Page<Map<String, Object>> supremoAuditHistory();


    void deleteApplication(int id);

    void autoAuditAfterExeAudit(BigDecimal limitMoney,Integer applicationId,Date auditDate);

    Page<Map<String, Object>> ReffAuditHistory(Long userId);

    Map<String,Object> getApplyDate(Integer roadHazardId);

    List<Map<String,Object>> getView(int applicationId);

    List<Map<String, Object>> getSummaryOfRepair(Date yearMonth, Long deptId);

    List<Map<String, Object>> getDetailOfRepair(Date yearMonth);

    List<Map<String, Object>> cityExport(Date yearMonth, int[] nationHighway);

    void updateStatus(int applicationId, String check);
}
