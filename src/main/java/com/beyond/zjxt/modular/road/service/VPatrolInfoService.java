package com.beyond.zjxt.modular.road.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.beyond.zjxt.modular.road.entity.Road_hazard_img;
import com.beyond.zjxt.modular.road.entity.VPatrolInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.Map;

public interface VPatrolInfoService extends IService<VPatrolInfo> {
    Page<Map<String,Object>> list(@Param("patrolResultId") Integer patrolResultId,
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
    Page<Map<String,Object>> deptList(@Param("patrolResultId") Integer patrolResultId,
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
    Page<Map<String,Object>> cityList(@Param("patrolResultId") Integer patrolResultId,
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
