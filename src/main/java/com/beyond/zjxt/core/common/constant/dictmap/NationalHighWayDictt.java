package com.beyond.zjxt.core.common.constant.dictmap;

import com.beyond.zjxt.core.common.constant.dictmap.base.AbstractDictMap;

public class NationalHighWayDictt extends AbstractDictMap {
    @Override
    public void init() {


        put("highwayName", "国道名称");
        put("overallLength", "国道长度");
        put("sectionCount", "国道段数");
        put("stakeCount", "国道桩数");
        put("status", "国道状态");
    }

    @Override
    protected void initBeWrapped() {

    }
}
