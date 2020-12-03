package com.beyond.zjxt.modular.road.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.VPatrolInfo;
import com.beyond.zjxt.modular.road.mapper.V_PatrolInfoMapper;
import com.beyond.zjxt.modular.road.service.VPatrolInfoService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @Author :zjk
 * @Date :Create in 16:24 2019-11-24
 * @Description 巡查信息
 **/
@Service
public class VPatrolInforServiceImpl extends ServiceImpl<V_PatrolInfoMapper, VPatrolInfo> implements VPatrolInfoService {
    @Override
    public Page<Map<String, Object>> list(Integer patrolResultId, Integer supervisorId, String patrolerName, Date beginTime, Date endTime, Integer roadSectionId, Integer highwayId, Integer stakeId,Long deptId,Long pid) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.list(page,patrolResultId,supervisorId,patrolerName,beginTime,endTime,roadSectionId,highwayId,stakeId,deptId,pid);
    }
    @Override
    public Page<Map<String, Object>> deptList(Integer patrolResultId, Integer supervisorId, String patrolerName, Date beginTime, Date endTime, Integer roadSectionId, Integer highwayId, Integer stakeId,Long deptId,Long pid) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.deptList(page,patrolResultId,supervisorId,patrolerName,beginTime,endTime,roadSectionId,highwayId,stakeId,deptId,pid);
    }
    @Override
    public Page<Map<String, Object>> cityList(Integer patrolResultId, Integer supervisorId, String patrolerName, Date beginTime, Date endTime, Integer roadSectionId, Integer highwayId, Integer stakeId,Long deptId,Long pid) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.cityList(page,patrolResultId,supervisorId,patrolerName,beginTime,endTime,roadSectionId,highwayId,stakeId,deptId,pid);
    }

    @Override
    public Map selectByPatrolResultId(Integer patrolResultId) {
        return this.baseMapper.selectByPatrolResultId(patrolResultId);
    }
}
