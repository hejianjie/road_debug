package com.beyond.zjxt.modular.road.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.Acceptance;
import com.beyond.zjxt.modular.road.mapper.AcceptanceMapper;
import com.beyond.zjxt.modular.road.service.AcceptanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 验收核算表 服务实现类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Service
public class AcceptanceServiceImpl extends ServiceImpl<AcceptanceMapper, Acceptance> implements AcceptanceService {

    @Override
    public Object selectOne(int acceptanceId) {
        return this.baseMapper.selectOne(acceptanceId);
    }

    @Override
    public int firstAuditor(int userId, int result, int acceptanceId) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = format.format(date);
        return this.baseMapper.firstAuditor(userId,result,dateString,acceptanceId);
    }

    @Override
    public int secondAuditor(int userId, int result, int acceptanceId) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = format.format(date);
        return this.baseMapper.secondAuditor(userId,result,dateString,acceptanceId);
    }

    /**
     * 获取所有的待核量的病害列表，根据时间判断 但是目前数据库申请表里面的到期时间是字符串类型
     * @param deptId
     * @return
     */
    @Override
    public Page<Map<String, Object>> selectPendingQuantity(Long deptId) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.selectPendingQuantity(page,deptId);
    }

    @Override
    public Page<Map<String, Object>> selectPendingQuantityCounty(Long deptParentId,Long roleId) {
        Page page = LayuiPageFactory.defaultPage();
        System.out.println(deptParentId+"dfdsssssssssssssssssssss");
        return this.baseMapper.selectPendingQuantityCounty(page,deptParentId,roleId);
    }

    @Override
    public int changeStatus(int applicationId, int userId) {
        return this.baseMapper.changeStatus(applicationId,userId);
    }

    @Override
    public Map<String, Object> findByAcceptanceId(Integer acceptanceId) {
        return this.baseMapper.findByAcceptanceId(acceptanceId);
    }

    @Override
    public Page<Map<String, Object>> getByApplicationId(int applicationId) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.getByApplicationId(page,applicationId);
    }

    @Override
    public int addStatus(int acceptanceId) {
        return this.baseMapper.addStatus(acceptanceId);
    }
}
