package com.beyond.zjxt.modular.road.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.Hazard_base_info;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 病害基础信息 服务类
 * </p>
 *
 * @author lhd
 * @since 2019-11-22
 */
public interface Hazard_base_infoService extends IService<Hazard_base_info> {
    Object getById(int id);
    Object getAll();

    List<Map<String,Object>> selectHazardBaseInfoCascader();

    Object CountyList();

    Page<Map<String,Object>> hazardBaseInfoList(String name);


}
