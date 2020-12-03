package com.beyond.zjxt.modular.road.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beyond.zjxt.modular.road.entity.VApplicationInfo;
import com.beyond.zjxt.modular.road.mapper.V_ApplicationInfoMapper;
import com.beyond.zjxt.modular.road.service.V_ApplicationInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author :zjk
 * @Date :Create in 10:06 2019-11-28
 * @Description
 **/
@Service
public class V_ApplicationInfoServiceImpl extends ServiceImpl<V_ApplicationInfoMapper, VApplicationInfo> implements V_ApplicationInfoService {
    @Override
    public Map<String, Object> selectByApplicationId(Integer applicationId) {
        return this.baseMapper.selectByApplicationId(applicationId);
    }
}
