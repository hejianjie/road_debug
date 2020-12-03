package com.beyond.zjxt.modular.road.warpper;


import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * @Author :zjk
 * @Date :Create in 16:50 2019-11-24
 * @Description
 **/
public class PatrolInfoWrapper extends BaseControllerWrapper {
    public PatrolInfoWrapper(Map<String, Object> single) {
        super(single);
    }

    public PatrolInfoWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public PatrolInfoWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public PatrolInfoWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {

    }
}
