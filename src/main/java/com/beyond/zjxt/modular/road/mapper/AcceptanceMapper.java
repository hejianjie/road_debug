package com.beyond.zjxt.modular.road.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.Acceptance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 验收核算表 Mapper 接口
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Mapper
public interface AcceptanceMapper extends BaseMapper<Acceptance> {
    List<Map<String, Object>> selectOne(int acceptanceId);

    int firstAuditor(@Param("userId")int userId, @Param("result")int result, @Param("date")String date , @Param("acceptanceId")int acceptanceId);

    int secondAuditor(@Param("userId")int userId, @Param("result")int result, @Param("date")String date , @Param("acceptanceId")int acceptanceId);

    /**
     * 获取所有的到期的待核量的申请列表
     */
    Page<Map<String,Object>> selectPendingQuantity(@Param("page")Page page,@Param("deptId") Long deptId);

    //selectPendingQuantityCounty

    /**
     * 获取所有的到期的待核量的申请列表
     */
    Page<Map<String,Object>> selectPendingQuantityCounty(@Param("page")Page page,@Param("deptParentId") Long deptParentId,@Param("roleId") Long roleId);

    int changeStatus(@Param("applicationId")int applicationId,@Param("userId")int userId);

    Map<String,Object> findByAcceptanceId(@Param("acceptanceId") Integer acceptanceId);

    Page<Map<String,Object>> getByApplicationId(@Param("page")Page page,@Param("applicationId")int applicationId);

    int addStatus(@Param("acceptanceId")int acceptanceId);

    int getApplicationStatus(@Param("acceptanceId") String acceptanceId);

    void setAuditOne(@Param("id") Long id);

    void setAuditTwo(@Param("id") Long id);
}
