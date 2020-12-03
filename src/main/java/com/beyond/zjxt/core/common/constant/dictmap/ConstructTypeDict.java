package com.beyond.zjxt.core.common.constant.dictmap;

import com.beyond.zjxt.core.common.constant.dictmap.base.AbstractDictMap;

/***
 * 编辑建设性质的字典映射，因为编辑是需要id的
 */
public class ConstructTypeDict extends AbstractDictMap {
    @Override
    public void init() {
        put("constructTypeId", "建设性质编号");
        put("constructTypeName", "建设性质名称");
    }

    @Override
    protected void initBeWrapped() {

    }
}
