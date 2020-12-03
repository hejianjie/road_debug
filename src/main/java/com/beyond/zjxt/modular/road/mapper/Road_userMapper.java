package com.beyond.zjxt.modular.road.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.Road_user;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author beyond
 * @since 2019-11-22
 */
public interface Road_userMapper extends BaseMapper<Road_user> {
    List<Map<String,Object>> selectOne(int userId);

    /**
     * 根据电话号码判断是否这个用户存在
     */
    public  Road_user selectByTelephone(@Param("telephone") String telephone);

    /***
     * 添加用户
     */
    //   public int addRoadUser(@Param("user_name") String userName,@Param("telephone") String telephone,
    // @Param("organization_id") int  organizationId, @Param("account") String account,
    //  @Param("password") String password,@Param("status") int status,@Param("role") String role);
    public int addRoadUser(Road_user roadUser);
    /**
     * 判断账号是否已经被占用
     */
    public Road_user selectByAccount(@Param("account") String account);


    Page<Map<String, Object>> selectAllInspector(@Param(("Page"))Page page);

    Page<Map<String, Object>> selectInspectorByOrganizationId(@Param(("Page"))Page page, @Param("organizationId") int organizationId);

    List<Map<String,Object>> selectInspectorById(@Param("inspectorId") int inspectorId);

    int selectInspectorByAccount(@Param("account") String account);

    int selectInspectorByAccountAndId(@Param("account") String account,@Param("inspectorId") int inspectorId);

    int resetInspectorPassword(@Param("inspectorId") int inspectorId);

    int freezeInspector(@Param("roadUserId") int roadUserId);

    int thawInspector(@Param("roadUserId") int roadUserId);

    int addInspector(@Param("userName") String userName,@Param("gender") int gender,@Param("age") int age,@Param("telephone") String telephone,@Param("organization_id") int organization_id,@Param("account") String account,@Param("password") String password);

    int updateInspector(@Param("userName") String userName,@Param("gender") int gender,@Param("age") int age,@Param("telephone") String telephone,@Param("organizationId") int organizationId,@Param("account") String account,@Param("roadUserId") int roadUserId);

    int deleteInspector(@Param("roadUserId") int roadUserId);
}
