package com.beyond.zjxt.modular.road.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * @Author :zjk
 * @Date :Create in 20:10 2019-11-19
 * @Description
 **/
public class PatrolResultWrapper extends BaseControllerWrapper {
    public PatrolResultWrapper(Map<String, Object> single) {
        super(single);
    }

    public PatrolResultWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public PatrolResultWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public PatrolResultWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {

    }
}
