package com.beyond.zjxt.modular.road.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.Road_hazard;
import com.beyond.zjxt.modular.road.mapper.Road_hazardMapper;
import com.beyond.zjxt.modular.road.service.Road_hazardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 巡检路段病害 服务实现类
 * </p>
 *
 * @author lhd
 * @since 2019-11-22
 */
@Service
public class Road_hazardServiceImpl extends ServiceImpl<Road_hazardMapper, Road_hazard> implements Road_hazardService {
    @Autowired
    private Road_hazardMapper road_hazardMapper;

    @Override
    public Object getById(int roadHazardId) {
        return this.baseMapper.getById(roadHazardId);
    }

    @Override
    public Map<String,Object> getStatus(Integer id) {
        return road_hazardMapper.getStatus(id);
    }

    @Override
    public Map<String, Object> selectRoadHazardByroadHazardId(Integer roadHazardId) {
        return this.baseMapper.selectRoadHazardByroadHazardId(roadHazardId);
    }

    @Override
    public List<Map<String, Object>> list(Integer patrolResultId,Long deptId) {
        return this.baseMapper.list(patrolResultId,deptId);
    }

    @Override
    public Map<String,Object> selectById(Integer roadHazardId) {
        return this.baseMapper.selectById(roadHazardId);
    }

    @Override
    public void updateStatus(Integer status, Integer roadHazardId) {
        this.baseMapper.updateStatus(status,roadHazardId);
    }

    @Override
    public Page<Map<String, Object>> selectByStatus(Long deptId) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.selectByStatus(page,deptId);
    }

    /**
     * 区县部门领导审核 查出来的病害必须是已经通过养护经理审核
     *
     * 而且 区县填报人员已经填报 建设性质不是保养类型的的
     * @return
     */
    @Override
    public Page<Map<String, Object>> countyFirstTrial(Long deptId) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.countyFirstTrial(page,deptId);
    }

    @Override
    public Page<Map<String, Object>> countySecondTrial(Long deptId) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.countySecondTrial(page,deptId);
    }

    @Override
    public Map<String, Object> selectCountyFirstTrialByroadHazardId(Integer roadHazardId) {
        return this.baseMapper.selectCountyFirstTrialByroadHazardId(roadHazardId);
    }

    @Override
    public Page<Map<String, Object>> selectRoadHazard(Long deptId) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.selectRoadHazard(page,deptId);
    }

    @Override
    public Page<Map<String, Object>> selectAllRoadHazard(Integer supervisorId,
                                                         Date beginTime,
                                                         Date endTime,
                                                         Integer roadSectionId,
                                                         Integer highwayId,
                                                         Integer stakeId,
                                                         Long deptId,
                                                         Integer hazardStatusId,
                                                         Long pid) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.selectAllRoadHazard(page, supervisorId, beginTime, endTime, roadSectionId, highwayId, stakeId, deptId,hazardStatusId,pid);
    }

    @Override
    public Page<Map<String, Object>> selectCountyRoadHazard(Integer supervisorId, Date beginTime, Date endTime, Integer roadSectionId, Integer highwayId, Integer stakeId, Long deptId,Integer hazardStatusId,Long pid) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.selectCountyRoadHazard(page, supervisorId, beginTime, endTime, roadSectionId, highwayId, stakeId, deptId,hazardStatusId,pid);
    }

    @Override
    public Map<String, Object> selectSingleHazard(Integer roadHazardId) {
        return this.baseMapper.selectSingleHazard(roadHazardId);
    }
}
