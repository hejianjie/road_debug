package com.beyond.zjxt.modular.road.service.impl;

import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.core.common.exception.BizExceptionEnum;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.Application;
import com.beyond.zjxt.modular.road.mapper.ApplicationMapper;
import com.beyond.zjxt.modular.road.service.ApplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;



import java.math.BigDecimal;

import java.util.List;

import java.util.Map;

import java.util.Date;


/**
 * <p>
 * 申请表 服务实现类
 * </p>
 *
 * @author lhd
 * @since 2019-11-21
 */
@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements ApplicationService {

    @Override
    public Object selectOne(int applicationId) {
        System.out.println("===============================");
        return this.baseMapper.selectOne(applicationId);
    }

    @Override
    public Application selectbyRId(int roadHazardId){
        return this.baseMapper.selectbyRId(roadHazardId);
    };

    @Override
    public Integer getStatus(Integer applicationId) {
        return this.baseMapper.getStatus(applicationId);

    }

    @Override
    public int changeStatus(int status, int applicationId) {
        return this.baseMapper.changeStatus(status,applicationId);
    }

    @Override
    public List<Map<String, Object>> selectByRoadHazardId(int roadHazardId) {
        return this.baseMapper.selectByRoadHazardId(roadHazardId);
    }

    @Override


    public Page<Map<String, Object>> selectCityDeptUnderAudit(String role) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.selectCityDeptUnderAudit(page, role);
    }

    @Override
    public Page<Map<String, Object>> selectCityExecutiveUnderAudit() {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.selectCityExecutiveUnderAudit(page);
    }

    @Override
    public Page<Map<String, Object>> selectHistory(Long userId) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.selectHistory(page,userId);
    }

    @Override
    public int addOne(int roadHazardId, int userId, int construct_type, int construct_project, int construct_project_detail, int hazard_unit, String specificSize, float work_amount, float unit_price, int unit_price_type, String work_frequency, float appraisal_cost, String date, int estimated_finish_time,Date Time) {
        return this.baseMapper.addOne(roadHazardId, new Date(), userId, construct_type, construct_project, construct_project_detail, hazard_unit, specificSize, work_amount, unit_price, unit_price_type, work_frequency, appraisal_cost, date, estimated_finish_time, Time);

    }

    @Override
    public int updateCityDeptAuditResult(Long userId,String role, String auditSuggestion, String feedback, Integer applicationId, Date auditDate) {
        System.out.println(role);
        return  this.baseMapper.updateCityDeptAuditResult(userId,role, auditSuggestion, feedback,applicationId,auditDate);
    }

    @Override
    public int updateCityExecutiveAuditResult(Long userId,String role, String auditSuggestion, String feedback, Integer applicationId, Date auditDate) {
        return this.baseMapper.updateCityExecutiveAuditResult(userId,role,auditSuggestion,feedback,applicationId,auditDate);
    }

    @Override
    public Page<Map<String, Object>> cityDeptAuditHistory(String role) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.cityDeptAuditHistory(page,role);
    }

    @Override
    public Page<Map<String, Object>> cityExecutiveHistory() {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.cityExecutiveHistory(page);
    }

    @Override
    public int firstAuditor(int firstAuditorId, String firstAuditorResult,int applicationId) {
        System.out.println("mapper");
        System.out.println(firstAuditorResult);
        System.out.println(applicationId);
        System.out.println(firstAuditorId);
        return this.baseMapper.firstAuditor(firstAuditorId,firstAuditorResult,applicationId);
    }

    @Override
    public int countyAuditor(String role, int auditorId, String auditorResult, int applicationId) {
        return this.baseMapper.countyAuditor(role, auditorId, auditorResult, applicationId);
    }

    @Override
    public Page<Map<String, Object>> ReffeAuditerUnderList(Long userId) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.ReffeAuditerUnderList(page,userId);
    }

    @Override
    public Map<String, Object>selectByApplicationId(Integer applicationId) {
        return this.baseMapper.selectByApplicationId(applicationId);
    }

    @Override
    public Page<Map<String, Object>> supremoUnderAuditList() {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.supremoUnderAuditList(page);
    }

    @Override
    public void updateSupremoStatus(Long userId,String status,Date auditDate, Integer applicationId) {
        this.baseMapper.updateSupremoStatus(userId,status,auditDate,applicationId);
    }

    @Override
    public void supremoAutoAudit(Long userId,Date auditDate,BigDecimal limitMoney) {
        this.baseMapper.supremoAutoAudit(userId,auditDate,limitMoney);
    }

    @Override
    public Page<Map<String, Object>> supremoAuditHistory() {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.supremoAuditHistory(page);
    }

    @Override
    public void deleteApplication(int id) {
        int res = 0 ;
        res =  this.baseMapper.deleteApplication(id);
        if(res<1){
            throw new ServiceException(BizExceptionEnum.DELETE_FAIL);
        }
    }

    @Override
    public void autoAuditAfterExeAudit(BigDecimal limitMoney, Integer applicationId,Date auditDate) {
        this.baseMapper.autoAuditAfterExeAudit(limitMoney,applicationId,auditDate);
    }

    @Override
    public Page<Map<String, Object>> ReffAuditHistory(Long userId) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.ReffAuditHistory(page,userId);
    }

    @Override
    public Map<String,Object> getApplyDate(Integer roadHazardId) {
        return this.baseMapper.getApplyDate(roadHazardId);
    }

    @Override
    public List<Map<String,Object>> getView(int applicationId) {
        return this.baseMapper.getView(applicationId);
    }
}
