package com.beyond.zjxt.modular.road.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.Hazard_base_info;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 病害基础信息 Mapper 接口
 * </p>
 *
 * @author lhd
 * @since 2019-11-22
 */
@Mapper
public interface Hazard_base_infoMapper extends BaseMapper<Hazard_base_info> {
    List<Map<String,Object>> getById(int id);
    List<Map<String,Object>> getAll();

    Page<Map<String, Object>> hazardBaseInfoList(@Param("Page") Page page,@Param("name") String name);
}
