package com.beyond.zjxt.modular.road.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.Supervisor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beyond.zjxt.modular.road.entity.SupervisorDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理单位 Mapper 接口
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public interface SupervisorMapper extends BaseMapper<Supervisor> {

    //不能用重复的县区名
   Supervisor selectByName(@Param("supervisorName")String supervisorName);

    List<Map<String, Object>> selectCountyByUserId(@Param("userId")int userId);

    List<Map<String, Object>> selectDepartByCountyId(@Param("countyId")int countyId);

    /**
     * 获取所有市下面的区县和养护站
     */
    Page<Map<String, Object>> SupervisorList(@Param("page") Page page, @Param("supervisorNameCondition") String supervisorNameCondition);
    /**
     * 查询菜单树形列表
     *
     * @author fengshuonan
     * @Date 2019/2/23 22:03
     */
    List<Map<String, Object>> selectSupervisorList(@Param("supervisorNameCondition") String supervisorNameCondition);


    /***
     * 添加巡查员
     */
    // int addSupervisor(@Param("sName") String sName,@Param("parentId") int parentId,@Param("icon") String icon);
    int addSupervisor(@Param("supervisor")Supervisor supervisor);
    /**
     * 修改负责任id
     */
    void updateUserIdById(@Param("user_id") int userId,@Param("supervisor_id")int supervisorId);
    /**
     * 根据编号查询巡查单位
     */
    Supervisor selectSupervisorById(@Param("supervisorId")int supervisorId);
    /**
     * 修改巡查单位名称
     */
    void updateSupervisorNameById(@Param("supervisor_name") String supervisorName,@Param("supervisor_id")int supervisorId);
    /**
     * 根据负责任编号查它负责的巡查单位
     */
    List<Supervisor> selectSupervisorByUserId(@Param("userId")int userId);

    /**
     * 删除巡查管理单位
     * @param supervisorId
     */
    void removeSupervisorById(@Param("supervisorId")int supervisorId);

    /***
     * 以某个区县的身份登录查询出其下级的所有养护站
     */
    Page<Map<String, Object>> selectSupervisorCountyListByParentId(@Param("page") Page page, @Param("supervisorNameCondition") String supervisorNameCondition,@Param("parentId") int parentId);

 /***
  * 某个市下面的所有巡查单位
  */
 List<SupervisorDTO> selectSupervisorCascader();

 List<SupervisorDTO> selectCountyCascader();
}
