package com.beyond.zjxt.modular.road.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beyond.zjxt.modular.road.entity.supremoLimitmoney;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Map;

public interface supremoLimitmoneyService extends IService<supremoLimitmoney> {
    Map<String,Object> selectByUserId(Long userId);

    void updateLimitMoney(BigDecimal money, Long userId);

    Map<String,Object>selectTopOne();

}
