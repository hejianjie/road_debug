package com.beyond.zjxt.modular.road.service.impl;

import com.beyond.zjxt.modular.road.entity.Supervisor;
import com.beyond.zjxt.modular.road.entity.Sys_dept;
import com.beyond.zjxt.modular.road.mapper.Sys_deptMapper;
import com.beyond.zjxt.modular.road.service.Sys_deptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Service
public class Sys_deptServiceImpl extends ServiceImpl<Sys_deptMapper, Sys_dept> implements Sys_deptService {


    @Override
    public List<Integer> getPid(Long deptId) {
        return this.baseMapper.getPid(deptId);
    }

    @Override
    public List<Map<String, Object>> selectCounty() {

        return this.baseMapper.selectCounty();
    }

    @Override
    public List<Map<String, Object>> selectDepartByCountyId(int countyId) {
        return this.baseMapper.selectDepartByCountyId(countyId);
    }


}
