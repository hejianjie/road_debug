package com.beyond.zjxt.modular.road.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * @Author :zjk
 * @Date :Create in 19:00 2019-11-20
 * @Description
 **/
public class RoadHazardWrapper extends BaseControllerWrapper {
    public RoadHazardWrapper(Map<String, Object> single) {
        super(single);
    }

    public RoadHazardWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public RoadHazardWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public RoadHazardWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {

    }
}
