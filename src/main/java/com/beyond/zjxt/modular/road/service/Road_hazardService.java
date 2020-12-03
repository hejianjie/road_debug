package com.beyond.zjxt.modular.road.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.Road_hazard;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 巡检路段病害 服务类
 * </p>
 *
 * @author lhd
 * @since 2019-11-22
 */
public interface Road_hazardService extends IService<Road_hazard> {
    List<Map<String,Object>> list(Integer patrolResultId,Long deptId);

    Map<String,Object> selectById(@Param("roadHazardId") Integer roadHazardId);

    Object getById(int roadHazardId);


    void updateStatus(Integer status,Integer roadHazardId);


    /**
     * @Author YuJunMing
     * @Date 2019/12/2 14:59
     * DESCRIPTION:
     */
    Map<String,Object> getStatus(Integer id);

    /**
     *
     *  roadHazard根据病害id查询病害信息
     * @return
     */
    Map<String,Object> selectRoadHazardByroadHazardId(Integer roadHazardId);
    Page<Map<String,Object>>selectByStatus(Long deptId);
    /**
     *  区县部门领导审核 查出来的病害必须是已经通过养护经理审核
     *  而且 区县填报人员已经填报 建设性质不是保养类型的的
     */
    Page<Map<String,Object>>countyFirstTrial(Long deptId);

    /**
     *  区县部门领导审核 查出来的病害必须是已经通过养护经理审核
     *  而且 区县填报人员已经填报 建设性质不是保养类型的的
     */
    Page<Map<String,Object>>countySecondTrial(Long deptId);


    /**
     *
     *  roadHazard根据病害id查询病害信息申请id
     * @return
     */
    Map<String,Object> selectCountyFirstTrialByroadHazardId(Integer roadHazardId);

    Page<Map<String,Object>> selectRoadHazard(Long deptId);

    Page<Map<String, Object>> selectAllRoadHazard(Integer supervisorId,
                                                  Date beginTime,
                                                  Date endTime,
                                                  Integer roadSectionId,
                                                  Integer highwayId,
                                                  Integer stakeId,
                                                  Long deptId,
                                                  Integer hazardStatusId,
                                                  Long pid);

    Page<Map<String, Object>> selectCountyRoadHazard(Integer supervisorId,
                                                  Date beginTime,
                                                  Date endTime,
                                                  Integer roadSectionId,
                                                  Integer highwayId,
                                                  Integer stakeId,
                                                  Long deptId,
                                                     Integer hazardStatusId,
                                                     Long pid);

    Map<String, Object> selectSingleHazard(Integer roadHazardId);


}
