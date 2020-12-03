package com.beyond.zjxt.modular.road.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beyond.zjxt.modular.road.entity.CityAudit;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author :zjk
 * @Date :Create in 21:17 2019-12-03
 * @Description
 **/
public interface CityAuditMapper extends BaseMapper<CityAudit> {
    List<Map<String, Object>> selectByApplicationId(@Param("applicationId") Integer applicationId);

    Map<String, Object> selectByAppliIdAndUserId(@Param("applicationId") Integer applicationId, @Param("userId") Long userId);

    void updateStatus(@Param("status")String status, @Param("auditDate")Date auditDate,@Param("applicationId")Integer applicationId,@Param("userId")Long userId);
}
