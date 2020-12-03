package com.beyond.zjxt.modular.road.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.Construct_project;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 建设项目表 Mapper 接口
 * </p>
 *
 * @author lhd
 * @since 2019-11-22
 */
@Mapper
public interface Construct_projectMapper extends BaseMapper<Construct_project> {
    List<Map<String,Object>> getAll();
    List<Map<String,Object>> getList( );
}
