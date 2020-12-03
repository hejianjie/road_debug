package com.beyond.zjxt.modular.road.service.impl;

import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beyond.zjxt.core.common.exception.BizExceptionEnum;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.PatrolCar;
import com.beyond.zjxt.modular.road.mapper.PatrolCarMapper;
import com.beyond.zjxt.modular.road.service.PatrolCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class PatrolCarServiceImpl extends ServiceImpl<PatrolCarMapper, PatrolCar> implements PatrolCarService {
    @Autowired
    private PatrolCarMapper patrolCarMapper;
    @Override
    public Page<Map<String, Object>> getCarList(Long did,String number) {
        Page page = LayuiPageFactory.defaultPage();
        return patrolCarMapper.getCarList(page,did,number);
    }

    @Override
    public void insertCar(PatrolCar patrolCar) {
        int res = 0 ;
        try{
            res = patrolCarMapper.insert(patrolCar);
        }catch (Exception e){
            throw new ServiceException(BizExceptionEnum.INSERT_SAME_CAR);
        }

        if(res <1){
            throw new ServiceException(BizExceptionEnum.INSERT_FAIL);
        }
    }


    @Override
    public void deleteCar(String number) {
        int res = 0 ;
        res =  patrolCarMapper.deleteById(number);
        if(res<1){
            throw new ServiceException(BizExceptionEnum.DELETE_FAIL);
        }
    }
}
