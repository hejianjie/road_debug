package com.beyond.zjxt.modular.road.service.impl;

import com.beyond.zjxt.modular.road.entity.Unit_price_type;
import com.beyond.zjxt.modular.road.mapper.Unit_price_typeMapper;
import com.beyond.zjxt.modular.road.service.Unit_price_typeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 单价种类表 服务实现类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Service
public class Unit_price_typeServiceImpl extends ServiceImpl<Unit_price_typeMapper, Unit_price_type> implements Unit_price_typeService {

    @Override
    public Object getAll() {
        return this.baseMapper.getAll();
    }
}
