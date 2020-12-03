package com.beyond.zjxt.modular.road.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

public class PendingQuantityWrapper  extends BaseControllerWrapper {
    public PendingQuantityWrapper(Map<String, Object> single) {
        super(single);
    }

    public PendingQuantityWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public PendingQuantityWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public PendingQuantityWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {

    }
}
