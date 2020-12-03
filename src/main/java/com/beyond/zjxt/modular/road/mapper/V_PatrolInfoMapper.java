package com.beyond.zjxt.modular.road.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.Road_hazard;
import com.beyond.zjxt.modular.road.entity.VPatrolInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.Map;

/**
 * @Author :zjk
 * @Date :Create in 17:00 2019-11-22
 * @Description
 **/
public interface V_PatrolInfoMapper extends BaseMapper<VPatrolInfo> {
    Page<Map<String,Object>> list(@Param("page")Page page, @Param("patrolResultId") Integer patrolResultId,
                                  @Param("supervisorId") Integer supervisorId,
                                  @Param("patrolerName") String patrolerName,
                                  @Param("beginTime") Date beginTime,
                                  @Param("endTime") Date endTime,
                                  @Param("roadSectionId") Integer roadSectionId,
                                  @Param("highwayId") Integer highwayId,
                                  @Param("stakeId") Integer stakeId,
                                  @Param("deptId") Long deptId,
                                  @Param("pid")Long pid
                                  );


    Page<Map<String,Object>> deptList(@Param("page")Page page, @Param("patrolResultId") Integer patrolResultId,
                                  @Param("supervisorId") Integer supervisorId,
                                  @Param("patrolerName") String patrolerName,
                                  @Param("beginTime") Date beginTime,
                                  @Param("endTime") Date endTime,
                                  @Param("roadSectionId") Integer roadSectionId,
                                  @Param("highwayId") Integer highwayId,
                                  @Param("stakeId") Integer stakeId,
                                  @Param("deptId") Long deptId,
                                      @Param("pid")Long pid
    );

    Page<Map<String,Object>> cityList(@Param("page")Page page, @Param("patrolResultId") Integer patrolResultId,
                                      @Param("supervisorId") Integer supervisorId,
                                      @Param("patrolerName") String patrolerName,
                                      @Param("beginTime") Date beginTime,
                                      @Param("endTime") Date endTime,
                                      @Param("roadSectionId") Integer roadSectionId,
                                      @Param("highwayId") Integer highwayId,
                                      @Param("stakeId") Integer stakeId,
                                      @Param("deptId") Long deptId,
                                      @Param("pid")Long pid
    );


    Map selectByPatrolResultId(@Param("patrolResultId") Integer patrolResultId);
}
