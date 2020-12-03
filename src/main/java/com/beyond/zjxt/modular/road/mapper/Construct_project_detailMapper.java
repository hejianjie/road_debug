package com.beyond.zjxt.modular.road.mapper;

import com.beyond.zjxt.modular.road.entity.Construct_project_detail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 建设项目细目表 Mapper 接口
 * </p>
 *
 * @author lhd
 * @since 2019-11-22
 */
@Mapper
public interface Construct_project_detailMapper extends BaseMapper<Construct_project_detail> {
    List<Map<String,Object>> selectByProjectId(int constructProjectId);


}
