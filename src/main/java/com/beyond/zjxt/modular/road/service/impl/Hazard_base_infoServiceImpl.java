package com.beyond.zjxt.modular.road.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.CascaderDTO;
import com.beyond.zjxt.modular.road.entity.Hazard_base_info;
import com.beyond.zjxt.modular.road.entity.SupervisorDTO;
import com.beyond.zjxt.modular.road.mapper.Hazard_base_infoMapper;
import com.beyond.zjxt.modular.road.service.Hazard_base_infoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 病害基础信息 服务实现类
 * </p>
 *
 * @author lhd
 * @since 2019-11-22
 */
@Service
public class Hazard_base_infoServiceImpl extends ServiceImpl<Hazard_base_infoMapper, Hazard_base_info> implements Hazard_base_infoService {
    @Autowired
    private Hazard_base_infoMapper hazard_base_infoMapper;

    @Override
    public Object getById(int id) {
        return hazard_base_infoMapper.getById(id);
    }
    @Override
    public Object getAll() {
        return hazard_base_infoMapper.getAll();
    }

    @Override
    public List<Map<String,Object>> selectHazardBaseInfoCascader() {
        return this.baseMapper.getAll();
    }

    @Override
    public Object CountyList() {
        List<Map<String,Object>>list=this.selectHazardBaseInfoCascader();
        List<CascaderDTO> resultList = new ArrayList<>();
        CascaderDTO all = new CascaderDTO();
        all.setLabel("全部");
        all.setChildren(null);

        resultList.add(all);
        for (Map<String, Object> map : list) {
            CascaderDTO temp = new CascaderDTO();
            temp.label = (String) map.get("name");
            int infoId=(Integer) map.get("info_id");
//            long value1=Long.valueOf("")
            temp.value = (long)infoId;
            resultList.add(temp);
        }
        return resultList;
    }

    @Override
    public Page<Map<String, Object>> hazardBaseInfoList(String name) {
        Page page = LayuiPageFactory.defaultPage();
        return hazard_base_infoMapper.hazardBaseInfoList(page,name);
    }
}
