package com.beyond.zjxt.modular.road.service.impl;

import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.core.common.exception.BizExceptionEnum;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.CascaderDTO;
import com.beyond.zjxt.modular.road.entity.Road_user;
import com.beyond.zjxt.modular.road.entity.Supervisor;
import com.beyond.zjxt.modular.road.entity.SupervisorDTO;
import com.beyond.zjxt.modular.road.mapper.SupervisorMapper;
import com.beyond.zjxt.modular.road.service.Road_userService;
import com.beyond.zjxt.modular.road.service.SupervisorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理单位 服务实现类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Service
public class SupervisorServiceImpl extends ServiceImpl<SupervisorMapper, Supervisor> implements SupervisorService {
    @Autowired
    Road_userService roadUserService;
    /**
     * 获取所有建设性质列表,并且可以根据建设性质名查询获取列表
     */
    @Override
    public Page<Map<String, Object>> SupervisorList(String supervisorNameCondition) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.SupervisorList(page,supervisorNameCondition);
    }


    /**
     * 获取菜单树形列表
     */
    @Override
    public List<Map<String, Object>> selectSupervisorList(String supervisorNameCondition) {
        List<Map<String, Object>> maps = this.baseMapper.selectSupervisorList(supervisorNameCondition);
        if (maps == null) {
            maps = new ArrayList<>();
        }
        return maps;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSupervisor(Supervisor supervisor) {
        //先添加密码和账号到用户表
        if (ToolUtil.isOneEmpty(supervisor, supervisor.getSupervisorName(),supervisor.getUserName(),supervisor.getAccount(),
                supervisor.getTelephone(),supervisor.getPassword(),supervisor.getParentId())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        //表设计有问题，如果巡查单位需要有userid，那么userid必须从保存user后获取，
        // 而user表里面的organizationid又要从supervisor表获取，
        // 只能让supervisor表的userid暂时变为可以为空，对supervisor表进行两次操作
        String icon = "";//新建养护站
        if(supervisor.getParentId()==1)//如果是最右上角的新建区县
        {
            icon = "layui-icon layui-icon-set";
        }
        supervisor.setIcon(icon);
        this.baseMapper.addSupervisor(supervisor);
        System.out.println("用户编号:"+supervisor.getSupervisorId());
        //根据supervisor获取supervisor_id
        //注意巡查单位名称也要注意唯一性
        //巡查单位名称暂时没有注意唯一性判断
        Road_user obj = roadUserService.selectByTelephone(supervisor.getTelephone());
        Road_user obj2 = roadUserService.selectByAccount(supervisor.getAccount());
        if(obj != null){
            throw new ServiceException(BizExceptionEnum.SupervisorManagement_EXISTED);
        }
        if(obj2 != null){
            throw new ServiceException(BizExceptionEnum.ACCOUNT_EXISTED);
        }
        //获取用户的编号保存进suoervisor表
        Road_user user = new Road_user();
        user.setUser_name(supervisor.getUserName());
        user.setTelephone(supervisor.getTelephone());
        user.setOrganization_id(supervisor.getSupervisorId());
        user.setAccount(supervisor.getAccount());
        user.setPassword(supervisor.getPassword());
        user.setStatus(1);
        user.setRole("巡查员");
        roadUserService.addRoadUser(user);
        this.baseMapper.updateUserIdById(user.getUser_id(),supervisor.getSupervisorId());

    }

    @Override
    public Supervisor selectSupervisorById(int supervisorId) {
        return baseMapper.selectSupervisorById(supervisorId);
    }

    /***
     * 修改巡查单位要注意巡查单位的负责人是否已经负责了其它的巡查单位则提示换个人
     * 是否一个人可以负责两个地方
     * @param supervisor
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSupervisor(Supervisor supervisor) {
        //修改可能改变账号和电话号码两个字段，因此也要判断是否除了本人之前外是否有人占用
        this.baseMapper.updateSupervisorNameById(supervisor.getSupervisorName(),supervisor.getSupervisorId());
        Road_user user = new Road_user();
        user.setUser_id(supervisor.getUserId());
        user.setUser_name(supervisor.getUserName());
        user.setTelephone(supervisor.getTelephone());
        user.setAccount(supervisor.getAccount());
        user.setPassword(supervisor.getPassword());
        user.setStatus(1);
        user.setRole("巡查员");
        Road_user oldUser = roadUserService.selectUserById(supervisor.getUserId());
        //如果现在的账号和之前的不一样那么我们就需要判断修改的新账号是否有人占用
        if(!oldUser.getAccount().equals(user.getAccount())){
            if(roadUserService.selectByAccount(user.getAccount())!=null){
                throw new ServiceException(BizExceptionEnum.ACCOUNT_EXISTED);
            }
        }
        //如果现在的电话号码和之前的不一样了也要判断
        if(!oldUser.getTelephone().equals(supervisor.getTelephone())){
            if(roadUserService.selectByTelephone(user.getTelephone())!=null){
                throw new ServiceException(BizExceptionEnum.SupervisorManagement_EXISTED);
            }
        }

        roadUserService.updateById(user);
    }

    @Override
    public List<Supervisor> selectSupervisorByUserId(int userId) {
        return baseMapper.selectSupervisorByUserId(userId);
    }

    @Override
    public void removeSupervisorById(int supervisorId) {
        baseMapper.removeSupervisorById(supervisorId);
    }

    /***
     * 养护站分页（某个区县角色登录）
     * @param supervisorNameCondition
     * @param parentId
     * @return
     */
    @Override
    public Page<Map<String, Object>> selectSupervisorCountyListByParentId(String supervisorNameCondition, int parentId) {
        Page  page = LayuiPageFactory.defaultPage();
        return baseMapper.selectSupervisorCountyListByParentId(page,supervisorNameCondition,parentId);
    }

    /***
     * M某个养护站添加
     * @param supervisor
     */
    @Override
    public void addSupervisorCounty(Supervisor supervisor) {
        //先添加密码和账号到用户表
        if (ToolUtil.isOneEmpty(supervisor, supervisor.getSupervisorName(),supervisor.getUserName(),supervisor.getAccount(),
                supervisor.getTelephone(),supervisor.getPassword(),supervisor.getParentId())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        //表设计有问题，如果巡查单位需要有userid，那么userid必须从保存user后获取，
        // 而user表里面的organizationid又要从supervisor表获取，
        // 只能让supervisor表的userid暂时变为可以为空，对supervisor表进行两次操作
        String icon = "";//新建养护站
        supervisor.setIcon(icon);
        this.baseMapper.addSupervisor(supervisor);
        System.out.println("用户编号:"+supervisor.getSupervisorId());
        //根据supervisor获取supervisor_id
        //注意巡查单位名称也要注意唯一性
        Road_user obj = roadUserService.selectByTelephone(supervisor.getTelephone());
        if(obj != null){
            throw new ServiceException(BizExceptionEnum.SupervisorManagement_EXISTED);
        }
        //获取用户的编号保存进suoervisor表
        Road_user user = new Road_user();
        user.setUser_name(supervisor.getUserName());
        user.setTelephone(supervisor.getTelephone());
        user.setOrganization_id(supervisor.getSupervisorId());
        user.setAccount(supervisor.getAccount());
        user.setPassword(supervisor.getPassword());
        user.setStatus(1);
        user.setRole("巡查员");
        roadUserService.addRoadUser(user);
        this.baseMapper.updateUserIdById(user.getUser_id(),supervisor.getSupervisorId());
    }

    @Override
    public List<SupervisorDTO> selectSupervisorCascader() {
        return baseMapper.selectSupervisorCascader();
    }

    /***
     * 区县市级联转换
     */
    @Override
    public  Object CountyList(){
        List<SupervisorDTO> Countylist = this.selectSupervisorCascader();
        List<SupervisorDTO> Yanghulist = this.baseMapper.selectCountyCascader();
        List<CascaderDTO> resultList = new ArrayList<>();
        CascaderDTO all = new CascaderDTO();
        all.setChildren(null);
        all.setLabel("全部");
        resultList.add(all);

        for(SupervisorDTO t : Countylist ){
            CascaderDTO temp = new CascaderDTO();
            temp.label = t.getSupervisorName();
            temp.value = t.getSupervisorId();
            List<CascaderDTO> childList = new ArrayList<>();
            for(SupervisorDTO t1 : Yanghulist){
                if(t1.getParentId() == temp.value){
                    CascaderDTO temp2 = new CascaderDTO();
                    temp2.label = t1.getSupervisorName();
                    temp2.value = t1.getSupervisorId();
                    childList.add(temp2);
                }
            }
            if(childList.size()>0){
                temp.children = childList;
            }
            resultList.add(temp);

        };

        for(CascaderDTO c : resultList){
            System.out.println(c.label+"0000000"+c.value);
            if(c.children!=null){
                System.out.println(c.children.size());
            }
        };

        return  resultList;
    }

}
