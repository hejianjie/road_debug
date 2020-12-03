package com.beyond.zjxt.modular.road.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;


public class ConstructTypeWrapper extends BaseControllerWrapper {

    public ConstructTypeWrapper(Map<String, Object> single) {
        super(single);
    }

    public ConstructTypeWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public ConstructTypeWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public ConstructTypeWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {

    }
}
