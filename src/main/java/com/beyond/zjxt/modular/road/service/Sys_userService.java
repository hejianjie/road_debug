package com.beyond.zjxt.modular.road.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.Sys_user;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public interface Sys_userService extends IService<Sys_user> {
    List<Integer> selectCountyAuditor1(Long deptId);
    List<Integer> selectCountyAuditor2(Long deptId);
    List<Integer> selectCityAuditor1(Long deptId);
    List<Integer> selectCityAuditor2(Long deptId);

    Sys_user selectOne(int userId);

    int deleteByUserId(int userId);
    Page<Map<String,Object>> selectByRoleId(Long roleId);
    int addUser(String name, String account);
    int resetPassword(int userId);
}
