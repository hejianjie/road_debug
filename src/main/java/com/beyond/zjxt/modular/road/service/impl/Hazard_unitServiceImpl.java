package com.beyond.zjxt.modular.road.service.impl;

import com.beyond.zjxt.modular.road.entity.Hazard_unit;
import com.beyond.zjxt.modular.road.mapper.Hazard_base_infoMapper;
import com.beyond.zjxt.modular.road.mapper.Hazard_unitMapper;
import com.beyond.zjxt.modular.road.service.Hazard_unitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 病害单位表 服务实现类
 * </p>
 *
 * @author lhd
 * @since 2019-11-22
 */
@Service
public class Hazard_unitServiceImpl extends ServiceImpl<Hazard_unitMapper, Hazard_unit> implements Hazard_unitService {
    @Autowired
    private Hazard_unitMapper hazard_unitMapper;

    @Override
    public Object getAll() {
        return hazard_unitMapper.getAll();
    }

    @Override
    public Object getById(int id) {
        return hazard_unitMapper.getById(id);
    }
}
