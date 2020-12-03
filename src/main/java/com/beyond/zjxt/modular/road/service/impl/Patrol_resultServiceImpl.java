package com.beyond.zjxt.modular.road.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.PatrolResult;
import com.beyond.zjxt.modular.road.mapper.Patrol_resultMapper;
import com.beyond.zjxt.modular.road.service.Patrol_resultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 巡检情况表 服务实现类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Service
public class Patrol_resultServiceImpl extends ServiceImpl<Patrol_resultMapper, PatrolResult> implements Patrol_resultService {
    /**
     * 获取所有巡查列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:16 PM
     */
    public Page<Map<String, Object>> list(Date beginTime, Date endTime) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.list(page, beginTime, endTime);
    }

    @Override
    public PatrolResult selectById(Integer patrolResultId) {
        return this.baseMapper.selectById(patrolResultId);
    }

    @Override
    public List<Map<String, Object>> getView(Integer patrolResultId) {
        return this.baseMapper.getView(patrolResultId);
    }
}
