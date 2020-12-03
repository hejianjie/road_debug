package com.beyond.zjxt.modular.road.service.impl;

import com.beyond.zjxt.modular.road.entity.Road_hazard_img;
import com.beyond.zjxt.modular.road.mapper.Road_hazard_imgMapper;
import com.beyond.zjxt.modular.road.service.Road_hazard_imgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 巡检病害图片表 服务实现类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Service
public class Road_hazard_imgServiceImpl extends ServiceImpl<Road_hazard_imgMapper, Road_hazard_img> implements Road_hazard_imgService {
    @Override
    public List<Map<String, Object>> selectByRoadHazardId(Integer roadHazardId) {
        return this.baseMapper.selectByRoadHazardId(roadHazardId);
    }
}
