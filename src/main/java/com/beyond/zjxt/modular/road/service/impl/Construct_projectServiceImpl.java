package com.beyond.zjxt.modular.road.service.impl;

import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.core.common.exception.BizExceptionEnum;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.Construct_project;
import com.beyond.zjxt.modular.road.entity.Construct_type;
import com.beyond.zjxt.modular.road.mapper.Construct_projectMapper;
import com.beyond.zjxt.modular.road.service.Construct_projectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 建设项目表 服务实现类
 * </p>
 *
 * @author lhd
 * @since 2019-11-22
 */
@Service
public class Construct_projectServiceImpl extends ServiceImpl<Construct_projectMapper, Construct_project> implements Construct_projectService {
    @Autowired
    private Construct_projectMapper construct_projectMapper;

    @Override
    public Object getAll() {
        return construct_projectMapper.getAll();
    }

    @Override
    public List<Map<String, Object>> getList( ) {
        return construct_projectMapper.getList();
    }


}
