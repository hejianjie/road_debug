package com.beyond.zjxt.modular.road.mapper;

import com.beyond.zjxt.modular.road.entity.Road_section;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 路段表 Mapper 接口
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public interface Road_sectionMapper extends BaseMapper<Road_section> {

    Page<Map<String,Object>> selectAll(@Param("page") Page page);

    Page<Map<String,Object>> selectByHighwayId(@Param("page") Page page, @Param("highwayId")int highwayId);

    int addRoadSection(@Param("name")String name,@Param("overallLength")Double overallLength,@Param("beginAt")Double beginAt,@Param("endAt")Double endAt,@Param("supervisorId")int supervisorId,@Param("nationalHighwayId")int nationalHighwayId);

    int update(@Param("name")String name,@Param("overallLength")Double overallLength,@Param("beginAt")Double beginAt,@Param("endAt")Double endAt,@Param("supervisorId")int supervisorId,@Param("nationalHighwayId")int nationalHighwayId,@Param("roadSectionId")int roadSectionId);

    int deleteByRoadSectionId(@Param("roadSectionId")int roadSectionId);

    List<Map<String,Object>> selectOneSection(@Param("roadSectionId")int roadSectionId);
}
