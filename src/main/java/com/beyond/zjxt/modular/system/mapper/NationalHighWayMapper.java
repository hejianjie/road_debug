package com.beyond.zjxt.modular.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.RoadSectionDTO;
import com.beyond.zjxt.modular.road.entity.StakeCascaderDTO;
import com.beyond.zjxt.modular.system.entity.NationalHighWay;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NationalHighWayMapper extends BaseMapper<NationalHighWay> {
    /**
     * 获取所有部门列表,并且可以根据国道名查询获取列表
     */
    Page<Map<String, Object>> HighWayList(@Param("page") Page page, @Param("highwayNameCondition") String highwayNameCondition);
    public void setNationalHighWay(NationalHighWay nationalHighWay);
    /**
     * 添加国道
     */
    public void addNationalHighWay(NationalHighWay nationalHighWay);

    /**
     * 是否有相同的国道名
     */
    public NationalHighWay selectHighwayName(@Param("highway_name") String highway_name);
    /**
     * 修改国道
     */
   public void updateNationalHighWayById(NationalHighWay nationalHighWay);
    /**
     * 删除国道
     */
   public void deleteHighWay(@Param("highway_id") int highway_id );

    /**
     * 国道，路段，桩级联
     */
    public List<NationalHighWay> selectHighWayCascader();
    public List<RoadSectionDTO>  selectRoadSectionCascader();
    public List<StakeCascaderDTO> selectStakeCascader();
}
