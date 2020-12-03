package com.beyond.zjxt.modular.road.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.Supervisor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.beyond.zjxt.modular.road.entity.SupervisorDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理单位 服务类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public interface SupervisorService extends IService<Supervisor> {

    /**
     * 获取所有建设性质列表,并且可以根据建设性质名查询获取列表
     */
    Page<Map<String, Object>> SupervisorList(String supervisorNameCondition);

    /**
     * 获取菜单树形列表
     *
     * @author fengshuonan
     * @Date 2019/2/23 22:02
     */
    public List<Map<String, Object>> selectSupervisorList(String supervisorNameCondition);

    /**
     * 新增
     * @param supervisor
     */
    @Transactional(rollbackFor = Exception.class)
    public  void addSupervisor(Supervisor supervisor);

    /***
     * 根据编号查询supervisor
     */
    public  Supervisor selectSupervisorById(int supervisorId);

    /**
     * 修改
     * @param
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateSupervisor(Supervisor supervisor);

    /**
     * 根据负责人编号查询它负责的巡查单位
     */
    List<Supervisor> selectSupervisorByUserId(int userId);

    void removeSupervisorById(int supervisorId);

    /***
     * 以某个区县的身份登录查询出其下级的所有养护站
     */
    Page<Map<String, Object>> selectSupervisorCountyListByParentId(String supervisorNameCondition, int parentId);

    void addSupervisorCounty(Supervisor supervisor);

    //区县，所级联一个下拉框
    List<SupervisorDTO> selectSupervisorCascader();

    Object CountyList();
}
