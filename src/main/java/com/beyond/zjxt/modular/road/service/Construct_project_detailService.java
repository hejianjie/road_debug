package com.beyond.zjxt.modular.road.service;

import com.beyond.zjxt.modular.road.entity.Construct_project_detail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 建设项目细目表 服务类
 * </p>
 *
 * @author lhd
 * @since 2019-11-22
 */
public interface Construct_project_detailService extends IService<Construct_project_detail> {
    Object selectByProjectId(int constructProjectId);
}
