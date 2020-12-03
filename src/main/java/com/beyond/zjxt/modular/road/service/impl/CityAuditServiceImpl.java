package com.beyond.zjxt.modular.road.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beyond.zjxt.modular.road.entity.CityAudit;
import com.beyond.zjxt.modular.road.mapper.CityAuditMapper;
import com.beyond.zjxt.modular.road.service.CityAuditService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author :zjk
 * @Date :Create in 21:15 2019-12-03
 * @Description
 **/
@Service
public class CityAuditServiceImpl extends ServiceImpl<CityAuditMapper, CityAudit> implements CityAuditService {
    @Override
    public List<Map<String, Object>> selectByApplicationId(Integer applicationId) {
        return this.baseMapper.selectByApplicationId(applicationId);
    }

    @Override
    public Map<String, Object> selectByAppliIdAndUserId(Integer applicationId, Long userId) {
        return this.baseMapper.selectByAppliIdAndUserId(applicationId,userId);
    }

    @Override
    public void updateStatus(String status, Date auditDate,Integer applicationId,Long userId) {
        this.baseMapper.updateStatus(status,auditDate,applicationId,userId);
    }
}
