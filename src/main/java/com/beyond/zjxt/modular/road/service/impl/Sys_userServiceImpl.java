package com.beyond.zjxt.modular.road.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.Sys_user;
import com.beyond.zjxt.modular.road.mapper.Sys_userMapper;
import com.beyond.zjxt.modular.road.service.Sys_userService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Service
public class Sys_userServiceImpl extends ServiceImpl<Sys_userMapper, Sys_user> implements Sys_userService {

    @Override
    public List<Integer> selectCountyAuditor1(Long deptId) {
        return this.baseMapper.selectCountyAuditor1(deptId);
    }

    @Override
    public List<Integer> selectCountyAuditor2(Long deptId) {
        return this.baseMapper.selectCountyAuditor2(deptId);
    }

    @Override
    public List<Integer> selectCityAuditor1(Long deptId) {
        return this.baseMapper.selectCityAuditor1(deptId);
    }

    @Override
    public List<Integer> selectCityAuditor2(Long deptId) {
        return this.baseMapper.selectCityAuditor2(deptId);
    }

    @Override
    public Sys_user selectOne(int userId) {
        return this.baseMapper.selectOne(userId);
    }

    @Override
    public int deleteByUserId(int userId) {
        return this.baseMapper.deleteByUserId(userId);
    }

    @Override
    public Page<Map<String, Object>> selectByRoleId(Long roleId) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.selectByRoleId(page,roleId);
    }

    @Override
    public int addUser(String name, String account) {
        return this.baseMapper.addUser(name,account);
    }

    @Override
    public int resetPassword(int userId) {
        return this.baseMapper.resetPassword(userId);
    }
}
