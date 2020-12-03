package com.beyond.zjxt.modular.system.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

public class SupervisorWrapper  extends BaseControllerWrapper {

    public SupervisorWrapper(Map<String, Object> single) {
        super(single);
    }

    public SupervisorWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public SupervisorWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public SupervisorWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {

    }
}
