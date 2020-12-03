package com.beyond.zjxt.core.common.constant.dictmap;

import com.beyond.zjxt.core.common.constant.dictmap.base.AbstractDictMap;

/***
 * 添加建设性质的字典映射，因为id是自增的所以这里不能带id
 */
public class ConstructTypeDictt extends AbstractDictMap {
    @Override
    public void init() {
        put("constructTypeName", "建设性质名称");
    }

    @Override
    protected void initBeWrapped() {

    }
}
