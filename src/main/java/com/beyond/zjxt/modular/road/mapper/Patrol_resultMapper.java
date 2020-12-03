package com.beyond.zjxt.modular.road.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.PatrolResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 巡检情况表 Mapper 接口
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public interface Patrol_resultMapper extends BaseMapper<PatrolResult> {
    /**
     * 获取所有巡查情况列表
     */
    Page<Map<String, Object>> list(@Param("page") Page page, @Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

    PatrolResult selectById(@Param("patrolResultId") Integer patrolResultId);

    List<Map<String,Object>> getView(@Param("patrolResultId") Integer patrolResultId);
}
