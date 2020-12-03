package com.beyond.zjxt.modular.road.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.Road_section;
import com.beyond.zjxt.modular.road.mapper.Road_sectionMapper;
import com.beyond.zjxt.modular.road.mapper.SupervisorMapper;
import com.beyond.zjxt.modular.road.service.Road_sectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 路段表 服务实现类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Service
public class Road_sectionServiceImpl extends ServiceImpl<Road_sectionMapper, Road_section> implements Road_sectionService {

    @Autowired
    private Road_sectionMapper road_sectionMapper;
    @Autowired
    private SupervisorMapper supervisorMapper;

    @Override
    public Page<Map<String, Object>> selectAll() {
        Page page = LayuiPageFactory.defaultPage();
        Page<Map<String,Object>> mapPage=road_sectionMapper.selectAll(page);

        for(Map<String,Object> map:mapPage.getRecords())
        {
            if(map.get("status")==(Object) 0)
            {
                map.replace("status","良好");
            }
            else if(map.get("status")==(Object) 1)
            {
                map.replace("status","保养");
            }
            else if(map.get("status")==(Object) 2)
            {
                map.replace("status","维修");
            }
        }
        return mapPage;
    }

    @Override
    public Page<Map<String, Object>> selectByHighwayId(int highwayId) {
        Page page = LayuiPageFactory.defaultPage();
        Page<Map<String,Object>> mapPage=road_sectionMapper.selectByHighwayId(page,highwayId);
        for(Map<String,Object> map:mapPage.getRecords())
        {
            if(map.get("status")==(Object) 0)
            {
                map.replace("status","良好");
            }
            else if(map.get("status")==(Object) 1)
            {
                map.replace("status","保养");
            }
            else if(map.get("status")==(Object) 2)
            {
                map.replace("status","维修");
            }
        }
        return mapPage;
    }

    @Override
    public int addRoadSection(String name,Double overallLength,Double beginAt,Double endAt,int supervisorId,int nationalHighwayId) {
        return road_sectionMapper.addRoadSection(name,overallLength,beginAt,endAt,supervisorId,nationalHighwayId);
    }

    @Override
    public int update(String name,Double overallLength,Double beginAt,Double endAt,int supervisorId,int nationalHighwayId,int roadSectionId) {
        return road_sectionMapper.update(name,overallLength,beginAt,endAt,supervisorId,nationalHighwayId,roadSectionId);
    }

    @Override
    public int deleteByRoadSectionId(int roadSectionId) {
        return road_sectionMapper.deleteByRoadSectionId(roadSectionId);
    }

    @Override
    public List<Map<String, Object>> selectCountyByUserId(int userId) {
        return supervisorMapper.selectCountyByUserId(userId);
    }

    @Override
    public List<Map<String, Object>> selectDepartByCountyId(int countyId) {
        return supervisorMapper.selectDepartByCountyId(countyId);
    }

    @Override
    public List<Map<String, Object>> selectOneSection(int roadSectionId) {
        return road_sectionMapper.selectOneSection(roadSectionId);
    }
}
