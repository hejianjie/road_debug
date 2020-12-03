package com.beyond.zjxt.modular.road.service;

import com.beyond.zjxt.modular.road.entity.Unit_price_type;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 单价种类表 服务类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public interface Unit_price_typeService extends IService<Unit_price_type> {
    Object getAll();
}
