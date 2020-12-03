package com.beyond.zjxt.modular.road.mapper;

import com.beyond.zjxt.modular.road.entity.Sys_dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Mapper
public interface Sys_deptMapper extends BaseMapper<Sys_dept> {
    List<Integer> getPid(Long deptId);

    List<Map<String, Object>> selectCounty();

    List<Map<String, Object>> selectDepartByCountyId(@Param("countyId")int countyId);
}
