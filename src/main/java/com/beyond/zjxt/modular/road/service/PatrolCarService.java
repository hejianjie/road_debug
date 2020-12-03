package com.beyond.zjxt.modular.road.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.beyond.zjxt.modular.road.entity.PatrolCar;

import java.util.Map;

public interface PatrolCarService extends IService<PatrolCar> {
    Page<Map<String, Object>> getCarList(Long did,String number);

    void insertCar(PatrolCar patrolCar);

    void deleteCar(String number);

}
