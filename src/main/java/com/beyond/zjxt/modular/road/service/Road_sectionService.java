package com.beyond.zjxt.modular.road.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.Road_section;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 路段表 服务类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public interface Road_sectionService extends IService<Road_section> {

    Page<Map<String,Object>> selectAll();

    Page<Map<String,Object>> selectByHighwayId(int highwayId);

    int addRoadSection(String name,Double overallLength,Double beginAt,Double endAt,int supervisorId,int nationalHighwayId);

    int update(String name,Double overallLength,Double beginAt,Double endAt,int supervisorId,int nationalHighwayId,int roadSectionId);

    int deleteByRoadSectionId(int roadSectionId);

    List<Map<String, Object>> selectCountyByUserId(int userId);

    List<Map<String, Object>> selectDepartByCountyId(int countyId);

    List<Map<String,Object>> selectOneSection(int roadSectionId);
}
