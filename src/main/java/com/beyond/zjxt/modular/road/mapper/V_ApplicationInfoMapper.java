package com.beyond.zjxt.modular.road.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beyond.zjxt.modular.road.entity.VApplicationInfo;

import java.util.Map;

/**
 * @Author :zjk
 * @Date :Create in 10:01 2019-11-28
 * @Description
 **/
public interface V_ApplicationInfoMapper extends BaseMapper<VApplicationInfo> {
    Map<String,Object>selectByApplicationId(Integer applicationId);
}
