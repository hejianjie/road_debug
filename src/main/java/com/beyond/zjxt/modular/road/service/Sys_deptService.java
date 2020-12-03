package com.beyond.zjxt.modular.road.service;

import com.beyond.zjxt.modular.road.entity.Supervisor;
import com.beyond.zjxt.modular.road.entity.Sys_dept;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public interface Sys_deptService extends IService<Sys_dept> {
    List<Integer> getPid(Long deptId);

    List<Map<String, Object>> selectCounty();

    List<Map<String, Object>> selectDepartByCountyId(@Param("countyId")int countyId);
}
