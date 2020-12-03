package com.beyond.zjxt.modular.road.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.Road_user;
import com.beyond.zjxt.modular.road.mapper.Road_hazardMapper;
import com.beyond.zjxt.modular.road.mapper.Road_userMapper;
import com.beyond.zjxt.modular.road.entity.Road_user;
import com.beyond.zjxt.modular.road.service.Road_userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author beyond
 * @since 2019-11-22
 */
@Service
public class Road_userServiceImpl extends ServiceImpl<Road_userMapper, Road_user> implements Road_userService {
    @Autowired
    private Road_userMapper road_hazardMapper;
    @Autowired
    private Road_userMapper road_userMapper;
    @Override
    public Object selectOne(int id) {
        return road_hazardMapper.selectOne(id);
    }
    @Override
    public Road_user selectByTelephone(String telephone) {
        return baseMapper.selectByTelephone(telephone);
    }

    //@Override
    // public int addRoadUser(String userName, String telephone, int organizationId, String account, String password,int status,String role) {
    //  return baseMapper.addRoadUser(userName,telephone,organizationId,account,password, status,role);
    // }
    @Override
    public int addRoadUser(Road_user roadUser) {
        return baseMapper.addRoadUser(roadUser);
    }

    @Override
    public Road_user selectByAccount(String account) {
        return baseMapper.selectByAccount(account);
    }

    @Override
    public Road_user selectUserById(int userId) {
        return baseMapper.selectById(userId);
    }


    @Override
    public Page<Map<String, Object>> selectAllInspector() {
        Page page = LayuiPageFactory.defaultPage();
        Page<Map<String,Object>> mapPage=road_userMapper.selectAllInspector(page);
        for(Map<String,Object> map:mapPage.getRecords())
        {
            if(map.get("status")==(Object) 0)
            {
                map.replace("status","冻结");
            }
            else if(map.get("status")==(Object) 1)
            {
                map.replace("status","可用");
            }
            else if(map.get("status")==(Object) 2)
            {
                map.replace("status","已删除");
            }

            if(map.get("gender")==(Object) 0)
            {
                map.replace("gender","女");
            }
            else if(map.get("gender")==(Object) 1)
            {
                map.replace("gender","男");
            }
        }
        return mapPage;

    }

    @Override
    public Page<Map<String, Object>> selectInspectorByOrganizationId(int organizationId) {
        Page page= LayuiPageFactory.defaultPage();
        Page<Map<String,Object>> mapPage=road_userMapper.selectInspectorByOrganizationId(page,organizationId);
        for(Map<String,Object> map:mapPage.getRecords())
        {
            if(map.get("status")==(Object) 0)
            {
                map.replace("status","冻结");
            }
            else if(map.get("status")==(Object) 1)
            {
                map.replace("status","可用");
            }
            else if(map.get("status")==(Object) 2)
            {
                map.replace("status","已删除");
            }

            if(map.get("gender")==(Object) 0)
            {
                map.replace("gender","女");
            }
            else if(map.get("gender")==(Object) 1)
            {
                map.replace("gender","男");
            }
        }
        return mapPage;
    }

    @Override
    public List<Map<String, Object>> selectInspectorById(int inspectorId) {

        return road_userMapper.selectInspectorById(inspectorId);
    }

    @Override
    public int updateInspectorStatus(int roadUserId) {
        return road_userMapper.freezeInspector(roadUserId);
    }

    @Override
    public int thawInspector(int roadUserId) {
        return road_userMapper.thawInspector(roadUserId);
    }

    @Override
    public int addInspector(String userName, int gender, int age, String telephone, int organization_id, String account, String password) {
        return road_userMapper.addInspector(userName,gender,age,telephone,organization_id,account,password);
    }

    @Override
    public int updateInspector(String userName, int gender, int age, String telephone, int organization_id, String account, int roadUserId) {
        return road_userMapper.updateInspector(userName,gender,age,telephone,organization_id,account,roadUserId);
    }

    @Override
    public int deleteInspector(int roadUserId) {
        return road_userMapper.deleteInspector(roadUserId);
    }

    @Override
    public int selectInspectorByAccount(String account) {
        return road_userMapper.selectInspectorByAccount(account);
    }

    @Override
    public int selectInspectorByAccountAndId(String account, int inspectorId) {
        return road_userMapper.selectInspectorByAccountAndId(account,inspectorId);
    }

    @Override
    public int resetInspectorPassword(int inspectorId) {
        return road_userMapper.resetInspectorPassword(inspectorId);
    }
}
