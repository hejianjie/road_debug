package com.beyond.zjxt.modular.road.mapper;

import com.beyond.zjxt.modular.road.entity.Road_hazard_img;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 巡检病害图片表 Mapper 接口
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public interface Road_hazard_imgMapper extends BaseMapper<Road_hazard_img> {
    List<Map<String,Object>> selectByRoadHazardId(@Param("roadHazardId") Integer roadHazardId);
}
