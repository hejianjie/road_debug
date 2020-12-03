package com.beyond.zjxt.modular.road.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.PatrolResult;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 巡检情况表 服务类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public interface Patrol_resultService extends IService<PatrolResult> {
    Page<Map<String, Object>> list(Date beginTime, Date endTime);

    PatrolResult selectById(Integer patrolResultId);

    List<Map<String,Object>> getView(Integer patrolResultId);
}
