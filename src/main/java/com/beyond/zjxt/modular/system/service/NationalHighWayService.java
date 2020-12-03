package com.beyond.zjxt.modular.system.service;

import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beyond.zjxt.core.common.exception.BizExceptionEnum;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.CascaderDTO;
import com.beyond.zjxt.modular.road.entity.RoadSectionDTO;
import com.beyond.zjxt.modular.road.entity.StakeCascaderDTO;
import com.beyond.zjxt.modular.system.entity.NationalHighWay;
import com.beyond.zjxt.modular.system.mapper.NationalHighWayMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NationalHighWayService extends ServiceImpl<NationalHighWayMapper, NationalHighWay> {

    /***
     * 分页，国道列表
     * @param highwayNameCondition
     * @return
     */
  public  Page<Map<String, Object>> HighWayList(String highwayNameCondition){
      Page page = LayuiPageFactory.defaultPage();
      return this.baseMapper.HighWayList(page,highwayNameCondition);

  }

    /**
     * 新增
     *
     * @author stylefeng
     * @Date 2019-03-13
     */

    public void addNationalHighWay(NationalHighWay nationalHighWay) {


      //  nationalHighWay.setHighwayId(6);
        //先判断是否有同名的国道
        System.out.println(nationalHighWay.getHighwayName()+"国道名称");
      NationalHighWay temp =  this.baseMapper.selectHighwayName(nationalHighWay.getHighwayName());
      BigDecimal s =  nationalHighWay.getOverallLength();
      nationalHighWay.setStatus(0);
        if(temp==null)
        { this.save(nationalHighWay);}
        else{
            throw new ServiceException(BizExceptionEnum.NATIONALHIGWAY_EXISTED);
        }
    }

    public void setNationalHighWay(NationalHighWay nationalHighWay){
        this.setNationalHighWay(nationalHighWay);
    }

    public void editNationalHighWay(NationalHighWay nationalHighWay) {

        if (ToolUtil.isOneEmpty(nationalHighWay, nationalHighWay.getHighwayName(), nationalHighWay.getOverallLength())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        System.out.println("国道编号"+nationalHighWay.getHighwayId());
    NationalHighWay   old = this.baseMapper.selectById(nationalHighWay.getHighwayId());
    if(old.getHighwayId() == nationalHighWay.getHighwayId()){
        this.baseMapper.updateById(nationalHighWay);
    }else{
        throw new ServiceException(BizExceptionEnum.NATIONALHIGWAY_EXISTED);
    }
//        //先判断是否有同名的国道
//        NationalHighWay temp =  this.baseMapper.selectHighwayName(nationalHighWay.getHighwayName());
//        if(temp==null)
//        { this.baseMapper.updateNationalHighWayById(nationalHighWay);}
//        else{
//            throw new ServiceException(BizExceptionEnum.NATIONALHIGWAY_EXISTED);
//        }
    }

    /**
     * 删除国道
     */
    public void deleteNationalHighway(int nationalHighwayId){
        this.baseMapper.deleteHighWay(nationalHighwayId);
    }

    public List<CascaderDTO> HighwayAndStakeANdRoadCascader() {
        List<CascaderDTO> resultList = new ArrayList<>();
        //这里要注意桩那里要拼接起始位置和结束位置为一个桩
        List<NationalHighWay> tempList1 = this.baseMapper.selectHighWayCascader();
        List<RoadSectionDTO> tempList2 = this.baseMapper.selectRoadSectionCascader();
        List<StakeCascaderDTO> tempList3 = this.baseMapper.selectStakeCascader();
        List<StakeCascaderDTO> tempList4 = this.baseMapper.selectStakeCascader();

        List<StakeCascaderDTO> list3 = new ArrayList<>();

        CascaderDTO all = new CascaderDTO();
        all.setLabel("全部");
        all.setChildren(null);

        resultList.add(all);

//        if (tempList3.size() >= 1) {
//            StakeCascaderDTO t = new StakeCascaderDTO();
//            t.setRoadSectionId(tempList3.get(0).getRoadSectionId());
//            t.setStakeId(tempList3.get(0).getStakeId());
//            t.setStakeName("0 ~ " + tempList3.get(0).getStakeName());
//            list3.add(t);
//
//            for (int i = 1; i < tempList3.size(); i++) {
//                if (tempList3.get(i - 1).getRoadSectionId() == tempList3.get(i).getRoadSectionId()) {
//                    StakeCascaderDTO t2 = new StakeCascaderDTO();
//                    t2.setRoadSectionId(tempList3.get(i).getRoadSectionId());
//                    t2.setStakeId(tempList3.get(i).getStakeId());
//                    if (tempList3.get(i - 1).getStakeName() != null && tempList3.get(i).getStakeName() != null && tempList3.get(i).getStakeName() != "") {
//                        t2.setStakeName(tempList3.get(i - 1).getStakeName() + " ~ " + tempList3.get(i).getStakeName());
//                    } else {
//                        t2.setStakeName(tempList3.get(i).getStakeName());
//                    }
//                    list3.add(t2);
//                } else {
//                    StakeCascaderDTO t1 = new StakeCascaderDTO();
//                    t1.setRoadSectionId(tempList3.get(i).getRoadSectionId());
//                    t1.setStakeId(tempList3.get(i).getStakeId());
//                    if (tempList3.get(i).getStakeName() != null && tempList3.get(i).getStakeName() != "") {
//                        t1.setStakeName("0 ~ " + tempList3.get(i).getStakeName());
//                    } else {
//                        t1.setStakeName(tempList3.get(i).getStakeName());
//                    }
//                    list3.add(t1);
//                }
//            }
//        }

        //输出测试
        for (int i = 0; i < list3.size(); i++) {
            System.out.println(list3.get(i).getStakeName());
        }

        for (NationalHighWay n : tempList1) {
            CascaderDTO c1 = new CascaderDTO();
            c1.value =(long) n.getHighwayId();
            c1.label = n.getHighwayName();
            List<CascaderDTO> resultList2 = new ArrayList<>();
            if (tempList2.size() > 0) {
                for (RoadSectionDTO r : tempList2) {
                    if (n.getHighwayId() == r.getHighwayId()) {
                        if (r.getRoadName() != null) {
                            CascaderDTO c2 = new CascaderDTO();
                            c2.value = r.getRoadSectionId();
                            c2.label = r.getRoadName();
                            List<CascaderDTO> resultList3 = new ArrayList<>();
                            if (tempList3.size() > 0) {
                                for (StakeCascaderDTO s : list3) {
                                    if (s.getStakeName() != null) {
                                        int a = r.getRoadSectionId();
                                        if (s.getRoadSectionId() == r.getRoadSectionId()) {
                                            CascaderDTO c3 = new CascaderDTO();
                                            c3.value = s.getStakeId();
                                            c3.label = s.getStakeName();
                                            resultList3.add(c3);
                                        }

                                    }
                                    if (resultList3.size() > 0) {
                                        c2.children = resultList3;
                                    }
                                }
                                resultList2.add(c2);
                            }
                        }

                    }
                    if (resultList2.size() > 0) {
                        c1.children = resultList2;
                    }
                }
                resultList.add(c1);
            }
        }
        return resultList;
    }



}
