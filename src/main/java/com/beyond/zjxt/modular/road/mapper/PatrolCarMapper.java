package com.beyond.zjxt.modular.road.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.PatrolCar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface PatrolCarMapper extends BaseMapper<PatrolCar> {

    Page<Map<String, Object>> getCarList(@Param(("Page")) Page page,@Param(("did")) Long did,@Param(("number")) String number);
}
