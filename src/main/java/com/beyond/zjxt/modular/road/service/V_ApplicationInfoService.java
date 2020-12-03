package com.beyond.zjxt.modular.road.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beyond.zjxt.modular.road.entity.VApplicationInfo;

import java.util.Map;

public interface V_ApplicationInfoService extends IService<VApplicationInfo> {
    Map<String,Object> selectByApplicationId(Integer applicationId);
}
