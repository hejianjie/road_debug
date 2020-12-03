package com.beyond.zjxt.modular.road.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.Road_hazard;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.Map;

import java.util.List;

/**
 * <p>
 * 巡检路段病害 Mapper 接口
 * </p>
 *
 * @author lhd
 * @since 2019-11-22
 */
@Mapper
public interface Road_hazardMapper extends BaseMapper<Road_hazard> {
    List<Map<String,Object>> list(@Param("patrolResultId")Integer patrolResultId,@Param("deptId")Long deptId);

    Map<String,Object> selectById(@Param("roadHazardId") Integer roadHazardId);

    List<Map<String,Object>> getById(@Param("roadHazardId") int roadHazardId);


    void updateStatus(@Param("status") Integer status,@Param("roadHazardId") Integer roadHazardId);

    Map<String,Object> getStatus(@Param("id") Integer id);

    /**
     * 查询一个病害
     * @param roadHazardId
     * @return
     */
    Map<String,Object> selectRoadHazardByroadHazardId(@Param("roadHazardId") Integer roadHazardId);


    Page<Map<String,Object>>selectByStatus(@Param("page")Page page,@Param("deptId")Long deptId);

    /**
     *  区县部门领导审核 查出来的病害必须是已经通过养护经理审核
     *  而且 区县填报人员已经填报 建设性质不是保养类型的的-->
     */
    Page<Map<String,Object>>countyFirstTrial(@Param("page")Page page,@Param("deptId") Long deptId);

    /**
     *  区县部门领导审核 查出来的病害必须是已经通过养护经理审核
     *  而且 区县填报人员已经填报 建设性质不是保养类型的的-->
     */
    Page<Map<String,Object>>countySecondTrial(@Param("page")Page page,@Param("deptId") Long deptId);

    /***
     * 查询一个申请病害
     */
    Map<String,Object> selectCountyFirstTrialByroadHazardId(@Param("roadHazardId") Integer roadHazardId);

    Page<Map<String,Object>> selectRoadHazard(@Param("page")Page page,@Param("deptId") Long deptId);

    Page<Map<String, Object>> selectAllRoadHazard(@Param("page") Page page,
                                                  @Param("supervisorId") Integer supervisorId,
                                                  @Param("beginTime") Date beginTime,
                                                  @Param("endTime") Date endTime,
                                                  @Param("roadSectionId") Integer roadSectionId,
                                                  @Param("highwayId") Integer highwayId,
                                                  @Param("stakeId") Integer stakeId,
                                                  @Param("deptId") Long deptId,
                                                  @Param("hazardStatusId")Integer hazardStatusId,
                                                  @Param("pid")Long pid);

    Map<String, Object> selectSingleHazard(@Param("roadHazardId") Integer roadHazardId);

    Page<Map<String, Object>> selectCountyRoadHazard(@Param("page") Page page,
                                                  @Param("supervisorId") Integer supervisorId,
                                                  @Param("beginTime") Date beginTime,
                                                  @Param("endTime") Date endTime,
                                                  @Param("roadSectionId") Integer roadSectionId,
                                                  @Param("highwayId") Integer highwayId,
                                                  @Param("stakeId") Integer stakeId,
                                                  @Param("deptId") Long deptId,
                                                     @Param("hazardStatusId")Integer hazardStatusId,
                                                     @Param("pid")Long pid
                                                    );

}
