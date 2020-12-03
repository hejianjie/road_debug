package com.beyond.zjxt.modular.road.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beyond.zjxt.modular.road.entity.supremoLimitmoney;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Author :zjk
 * @Date :Create in 20:32 2019-12-06
 * @Description
 **/
public interface supremoLimitmoneyMapper extends BaseMapper<supremoLimitmoney> {
    Map<String,Object> selectByUserId(@Param("userId") Long userId);

    void updateLimitMoney(@Param("money") BigDecimal money, @Param("userId") Long userId);

    Map<String,Object>selectTopOne();
}
