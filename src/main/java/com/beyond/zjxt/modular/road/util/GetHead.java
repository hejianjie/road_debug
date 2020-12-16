package com.beyond.zjxt.modular.road.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author :zjk
 * @Date :Create in 16:22 2020-12-16
 * @Description 获取表头
 **/
public class GetHead {
    public  List<List<String>> getMorningCheckHead(){
        String bigTitle = "2019年大连市干线公路小修工程完成量汇总表(3月份）";
        List<List<String>> head = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<>();
        head0.add(bigTitle);
        head0.add("填报单位（盖章）：大连市甘井子区公路管理段");
        head0.add("班级");
        List<String> head1 = new ArrayList<>();
        head1.add(bigTitle);
        head1.add("姓名");
        List<String> head2 = new ArrayList<>();
        head2.add(bigTitle);
        head2.add("晨午检时间");
        List<String> head3 = new ArrayList<>();
        head3.add(bigTitle);
        head3.add("体温");
        List<String> head4 = new ArrayList<>();
        head4.add(bigTitle);
        head4.add("晨午检异常症状");
        List<String> head5 = new ArrayList<>();
        head5.add(bigTitle);
        head5.add("备注");
        head.add(head0);
        head.add(head1);
        head.add(head2);
        head.add(head3);
        head.add(head4);
        head.add(head5);

        return head;
    }
}
