package com.beyond.zjxt.modular.road.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.beyond.zjxt.modular.road.entity.Road_user;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author beyond
 * @since 2019-11-22
 */
public interface Road_userService extends IService<Road_user> {
    Object selectOne(int id);
    Road_user selectByTelephone(String telephone);
    //   int addRoadUser( String userName, String telephone, int  organizationId, String account, String password,int status,String role);
    int addRoadUser(Road_user roadUser);
    Road_user selectByAccount(String account);
    //根据id查询用户
    Road_user selectUserById(int userId);

    Page<Map<String, Object>> selectAllInspector();

    Page<Map<String, Object>> selectInspectorByOrganizationId(int organizationId);

    List<Map<String,Object>> selectInspectorById(int inspectorId);

    int updateInspectorStatus(int roadUserId);
    int thawInspector(int roadUserId);

    int addInspector(String userName,int gender,int age,String telephone,int organization_id,String account,String password);

    int updateInspector(String userName,int gender,int age,String telephone,int organization_id,String account,int roadUserId);

    int deleteInspector(int roadUserId);

    int selectInspectorByAccount(String account);

    int selectInspectorByAccountAndId(String account,int inspectorId);

    int resetInspectorPassword(int inspectorId);
}
