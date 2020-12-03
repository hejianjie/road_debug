package com.beyond.zjxt.modular.road.service.impl;

import com.beyond.zjxt.modular.road.entity.Construct_project_detail;
import com.beyond.zjxt.modular.road.mapper.Construct_project_detailMapper;
import com.beyond.zjxt.modular.road.service.Construct_project_detailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 建设项目细目表 服务实现类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Service
public class Construct_project_detailServiceImpl extends ServiceImpl<Construct_project_detailMapper, Construct_project_detail> implements Construct_project_detailService {
    @Autowired
    private Construct_project_detailMapper construct_project_detailMapper;

    @Override
    public Object selectByProjectId(int constructProjectId) {
        return construct_project_detailMapper.selectByProjectId(constructProjectId);
    }
}
