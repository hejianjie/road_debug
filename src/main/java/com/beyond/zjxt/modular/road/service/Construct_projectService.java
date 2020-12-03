package com.beyond.zjxt.modular.road.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.Construct_project;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 建设项目表 服务类
 * </p>
 *
 * @author lhd
 * @since 2019-11-22
 */
public interface Construct_projectService extends IService<Construct_project> {
    Object getAll();
    List<Map<String,Object>> getList();


}
