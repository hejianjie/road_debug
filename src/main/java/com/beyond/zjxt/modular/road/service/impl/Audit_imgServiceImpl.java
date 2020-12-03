package com.beyond.zjxt.modular.road.service.impl;

import com.beyond.zjxt.modular.road.entity.Audit_img;
import com.beyond.zjxt.modular.road.mapper.Audit_imgMapper;
import com.beyond.zjxt.modular.road.service.Audit_imgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 审核照片 服务实现类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Service
public class Audit_imgServiceImpl extends ServiceImpl<Audit_imgMapper, Audit_img> implements Audit_imgService {

    @Override
    public Object select(int acceptanceId) {
        return this.baseMapper.select(acceptanceId);
    }

    @Override
    public List<Map<String, Object>> selectByAcceptanceId(Integer acceptanceId) {
        return this.baseMapper.select(acceptanceId);
    }
}
