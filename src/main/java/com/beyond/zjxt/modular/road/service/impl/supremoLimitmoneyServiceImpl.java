package com.beyond.zjxt.modular.road.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beyond.zjxt.modular.road.entity.supremoLimitmoney;
import com.beyond.zjxt.modular.road.mapper.supremoLimitmoneyMapper;
import com.beyond.zjxt.modular.road.service.supremoLimitmoneyService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Author :zjk
 * @Date :Create in 20:37 2019-12-06
 * @Description
 **/
@Service
public class supremoLimitmoneyServiceImpl extends ServiceImpl<supremoLimitmoneyMapper, supremoLimitmoney> implements supremoLimitmoneyService {
    @Override
    public Map<String, Object> selectByUserId(Long userId) {
        return this.baseMapper.selectByUserId(userId);
    }

    @Override
    public void updateLimitMoney(BigDecimal money, Long userId) {
         this.baseMapper.updateLimitMoney(money,userId);
    }

    @Override
    public Map<String, Object> selectTopOne() {
        return this.baseMapper.selectTopOne();
    }
}
