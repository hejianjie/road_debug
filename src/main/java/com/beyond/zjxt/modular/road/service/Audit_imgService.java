package com.beyond.zjxt.modular.road.service;

import com.beyond.zjxt.modular.road.entity.Audit_img;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 审核照片 服务类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public interface Audit_imgService extends IService<Audit_img> {
    Object select(int acceptanceId);
    List<Map<String,Object>> selectByAcceptanceId(Integer acceptanceId);
}
