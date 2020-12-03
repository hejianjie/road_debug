package com.beyond.zjxt.modular.road.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.Sys_user;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public interface Sys_userMapper extends BaseMapper<Sys_user> {
    List<Integer> selectCountyAuditor1(Long deptId);
    List<Integer> selectCountyAuditor2(Long deptId);
    List<Integer> selectCityAuditor1(Long deptId);
    List<Integer> selectCityAuditor2(Long deptId);

    Sys_user selectOne(int userId);

    int deleteByUserId(int userId);
    Page<Map<String,Object>> selectByRoleId(@Param("page") Page page,@Param("roleId")Long roleId);
    int addUser(@Param("name") String name,@Param("account") String account);
    int resetPassword(@Param("userId") int userId);
}
