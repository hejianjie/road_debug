package com.beyond.zjxt.modular.road.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.Acceptance;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 验收核算表 服务类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public interface AcceptanceService extends IService<Acceptance> {
    Object selectOne(int acceptanceId);

    int firstAuditor(int userId, int result , int acceptanceId);

    int secondAuditor(int userId, int result , int acceptanceId);

    Page<Map<String,Object>> selectPendingQuantity(Long deptId);

    Page<Map<String,Object>> selectPendingQuantityCounty(Long deptParentId,Long roleId);

    int changeStatus(int applicationId,int userId);

    Map<String,Object> findByAcceptanceId(Integer acceptanceId);

    Page<Map<String,Object>> getByApplicationId(int applicationId);

    int addStatus(int acceptanceId);

    int getApplicationStatus(String acceptanceId);

    void setAuditOne(Long id);

    void setAuditTwo(Long id);
}
