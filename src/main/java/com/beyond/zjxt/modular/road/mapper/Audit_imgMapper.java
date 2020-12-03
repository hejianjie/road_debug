package com.beyond.zjxt.modular.road.mapper;

import com.beyond.zjxt.modular.road.entity.Audit_img;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 审核照片 Mapper 接口
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Mapper
public interface Audit_imgMapper extends BaseMapper<Audit_img> {
    List<Map<String, Object>> select(int acceptanceId);
}
