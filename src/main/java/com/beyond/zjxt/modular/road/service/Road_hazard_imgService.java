package com.beyond.zjxt.modular.road.service;

import com.beyond.zjxt.modular.road.entity.Road_hazard_img;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 巡检病害图片表 服务类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public interface Road_hazard_imgService extends IService<Road_hazard_img> {
    List<Map<String,Object>> selectByRoadHazardId(Integer roadHazardId);

}
