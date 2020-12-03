package com.beyond.zjxt.modular.road.mapper;

import com.beyond.zjxt.modular.road.entity.Hazard_unit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 病害单位表 Mapper 接口
 * </p>
 *
 * @author lhd
 * @since 2019-11-22
 */
@Mapper
@Repository
public interface Hazard_unitMapper extends BaseMapper<Hazard_unit> {
    List<Map<String,Object>> getAll();
    Map<String,Object> getById(int id);
}
