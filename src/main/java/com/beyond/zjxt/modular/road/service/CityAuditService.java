package com.beyond.zjxt.modular.road.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beyond.zjxt.modular.road.entity.CityAudit;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author :zjk
 * @Date :Create in 21:14 2019-12-03
 * @Description
 **/
public interface CityAuditService extends IService<CityAudit> {
    List<Map<String, Object>> selectByApplicationId(Integer applicationId);

    Map<String, Object> selectByAppliIdAndUserId(Integer applicationId, Long userId);

    void updateStatus(String status,Date auditDate,Integer applicationId,Long userId);

}
