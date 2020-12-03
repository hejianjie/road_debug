package com.beyond.zjxt.modular.road.service;

import com.beyond.zjxt.modular.road.entity.Hazard_unit;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 病害单位表 服务类
 * </p>
 *
 * @author lhd
 * @since 2019-11-22
 */
public interface Hazard_unitService extends IService<Hazard_unit> {
    Object getAll();
    Object getById(int id);
}
