package com.beyond.zjxt.modular.road.mapper;

import com.beyond.zjxt.modular.road.entity.Unit_price_type;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 单价种类表 Mapper 接口
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public interface Unit_price_typeMapper extends BaseMapper<Unit_price_type> {
    List<Map<String,Object>> getAll();
}
