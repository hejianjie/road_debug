package com.beyond.zjxt.modular.road.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.beyond.zjxt.config.web.MonthSheetWriteHandler;

import com.beyond.zjxt.core.shiro.ShiroKit;

import com.beyond.zjxt.modular.road.entity.SmallScaleMeterHead;
import com.beyond.zjxt.modular.road.entity.SummaryOfMinorRepairsHead;
import com.beyond.zjxt.modular.road.service.ApplicationService;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author :zjk
 * @Date :Create in 19:15 2020-12-16
 * @Description 导出excel
 **/
@Controller
@RequestMapping("/export")
public class ExportExcelController {
    @Autowired
    ApplicationService applicationService;
    @RequestMapping("/exportSummaryOfMinorRepair")
    public void exportSummaryOfMinorRepair(HttpServletResponse response, @RequestParam("yearMonth") Date yearMonth) throws IOException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(yearMonth);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        ShiroKit.setSessionAttr("year", year);
        ShiroKit.setSessionAttr("month", month);
        List<Map<String,Object>> data = applicationService.getSummaryOfRepair(yearMonth);
        List<SummaryOfMinorRepairsHead> list = new ArrayList<>();
        BigDecimal preALlCost = new BigDecimal(0);
        BigDecimal finAllCost = new BigDecimal(0);
        for (int i = 0; i < data.size(); i++) {
            preALlCost = preALlCost.add((BigDecimal) data.get(i).get("price"));
            finAllCost = finAllCost.add((BigDecimal) data.get(i).get("finPrice"));
        }
        for (int i = 0; i < data.size() + 2; i++) {
            SummaryOfMinorRepairsHead summaryOfMinorRepairsHead = new SummaryOfMinorRepairsHead();

        if (i == 0) { // 设置合计行
            summaryOfMinorRepairsHead.setProjectName("甘井子");
            summaryOfMinorRepairsHead.setOperationFrequency("合计");
            summaryOfMinorRepairsHead.setPrice(preALlCost.divide(new BigDecimal(10000)));
            summaryOfMinorRepairsHead.setFinOperationFrequency("合计");
            summaryOfMinorRepairsHead.setFinPrice(finAllCost.divide(new BigDecimal(10000)));
        }else if (i == data.size() + 1) { // 设置最后一行填报人信息
            summaryOfMinorRepairsHead.setProjectName("单位负责人:");
            summaryOfMinorRepairsHead.setUnit("部门负责人:");
            summaryOfMinorRepairsHead.setFinOperationFrequency("填报人:");
        }

        else{

            summaryOfMinorRepairsHead.setFinOperationFrequency((String) data.get(i - 1).get("finOperationFrequency"));
            summaryOfMinorRepairsHead.setFinPrice((BigDecimal) data.get(i - 1).get("finPrice"));
            summaryOfMinorRepairsHead.setFinWorkAmount((BigDecimal) data.get(i -1).get("finWorkAmount"));
            summaryOfMinorRepairsHead.setNote((String) data.get(i - 1).get(""));
            summaryOfMinorRepairsHead.setOrderNum((long) i + 1);
            summaryOfMinorRepairsHead.setPrice((BigDecimal) data.get(i - 1).get("price"));
            summaryOfMinorRepairsHead.setProjectName((String) data.get(i - 1).get("projectName"));
            summaryOfMinorRepairsHead.setDetailProjectName((String) data.get(i - 1).get("detailProjectName"));
            summaryOfMinorRepairsHead.setUnit((String) data.get(i - 1).get("unit"));
            summaryOfMinorRepairsHead.setUnitPrice((BigDecimal) data.get(i - 1).get("unitPrice"));
            summaryOfMinorRepairsHead.setWorkAmount((BigDecimal) data.get(i - 1).get("workAmount"));
            summaryOfMinorRepairsHead.setProjectType((String) data.get(i - 1).get("projectType"));
            summaryOfMinorRepairsHead.setOperationFrequency((String) data.get(i - 1).get("finOperationFrequency"));
        }

            list.add(summaryOfMinorRepairsHead);
        }

        List<SmallScaleMeterHead> list1 = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            SmallScaleMeterHead head = new SmallScaleMeterHead();
            if (i == 0) { // 设置合计行
                head.setProjectName("甘井子");
                head.setOperationFrequency("合计");
                head.setPrice("10");
                head.setFinOperationFrequency("合计");
                head.setFinPrice("10");}
            else if (i == 10) { // 设置最后一行填报人信息
                head.setProjectName("单位负责人:");
                head.setUnitPrice("部门负责人:");
                head.setFinOperationFrequency("填报人:");
                }else { //表格数据
                head.setOrderNum((long) i);
                head.setRouteName("丹东线");
                head.setProjectName("路基小修");
                head.setSmallProjectName("维修浆砌边沟");
                head.setProjectType("小修类");
                head.setBeginToEndStake("558.800");
                head.setSpecificSize("2*0.3*0.2");
                head.setUnit("立方米");
                head.setUnitPrice("260");
                head.setWorkAmount("0.12");
                head.setOperationFrequency("1");
                head.setPrice("0.1");
                head.setBeforImg("修复前");
                head.setFinSpecificSize("2*0.3*0.2");
                head.setFinUnit("立方米");
                head.setFinUnitPrice("260");
                head.setFinWorkAmount("0.12");
                head.setFinOperationFrequency("1");
                head.setFinPrice("0.1");
                head.setAfterImg("修复后");
            }
            list1.add(head);
        }


        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
// 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("haha", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
//内容样式策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
//垂直居中,水平居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
//设置 自动换行
        contentWriteCellStyle.setWrapped(true);
// 字体策略
        WriteFont contentWriteFont = new WriteFont();
// 字体大小
        contentWriteFont.setFontHeightInPoints((short) 10);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
//头策略使用默认
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();

        // 两个sheet：小修汇总表 / 小修明细表
        //设置输出excel版本,不设置默认为xlsx
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).excelType(ExcelTypeEnum.XLS).build();
        // 小修汇总表
        WriteSheet writeSheet = EasyExcel.writerSheet(0, "小修汇总表(国省干线)")
                .head(SummaryOfMinorRepairsHead.class)
                //设置拦截器或自定义样式
                .registerWriteHandler(new MonthSheetWriteHandler())
                .registerWriteHandler(new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle))
                //设置默认样式及写入头信息开始的行数
                .useDefaultStyle(true).relativeHeadRowIndex(2)
                .build();
        excelWriter.write(list, writeSheet);
        // 小修明细表
        WriteSheet writeSheet1 = EasyExcel.writerSheet(1, "小修明细表(国省干线)")
                .head(SmallScaleMeterHead.class)
                .registerWriteHandler(new MonthSheetWriteHandler())
                .registerWriteHandler(new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle))
                .useDefaultStyle(true).relativeHeadRowIndex(2)
                .build();
        excelWriter.write(list1, writeSheet1);
        // 关闭
        excelWriter.finish();
    }
}
