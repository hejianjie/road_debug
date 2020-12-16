package com.beyond.zjxt.modular.road.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.beyond.zjxt.config.web.MonthSheetWriteHandler;
import com.beyond.zjxt.modular.road.entity.SummaryOfMinorRepairsHead;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author :zjk
 * @Date :Create in 19:15 2020-12-16
 * @Description 导出excel
 **/
@Controller
@RequestMapping("/export")
public class ExportExcelController {
    @RequestMapping("/exportSummaryOfMinorRepair")
    public void exportSummaryOfMinorRepair(HttpServletResponse response) throws IOException {
        List<SummaryOfMinorRepairsHead> list = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            SummaryOfMinorRepairsHead summaryOfMinorRepairsHead = new SummaryOfMinorRepairsHead();
            summaryOfMinorRepairsHead.setFinOperationFrequency("252");
            summaryOfMinorRepairsHead.setFinPrice("151");
            summaryOfMinorRepairsHead.setFinWorkAmount("151");
            summaryOfMinorRepairsHead.setNote("101");
            summaryOfMinorRepairsHead.setOrderNum((long)i);
            summaryOfMinorRepairsHead.setPrice("1");
            summaryOfMinorRepairsHead.setProjectName("155");
            summaryOfMinorRepairsHead.setSmallProjectName("105");
            summaryOfMinorRepairsHead.setUnit("45");
            summaryOfMinorRepairsHead.setUnitPrice("101");
            summaryOfMinorRepairsHead.setWorkAmount("101");
            summaryOfMinorRepairsHead.setProjectType("54");
            summaryOfMinorRepairsHead.setOperationFrequency("848");
            list.add(summaryOfMinorRepairsHead);
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
        contentWriteFont.setFontHeightInPoints((short) 12);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
//头策略使用默认
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //excel如需下载到本地,只需要将response.getOutputStream()换成File即可(注释掉以上response代码)
        EasyExcel.write(response.getOutputStream(), SummaryOfMinorRepairsHead.class)
                //设置输出excel版本,不设置默认为xlsx
                .excelType(ExcelTypeEnum.XLS).head(SummaryOfMinorRepairsHead.class)
                //设置拦截器或自定义样式
                .registerWriteHandler(new MonthSheetWriteHandler())
                .registerWriteHandler(new HorizontalCellStyleStrategy(headWriteCellStyle,contentWriteCellStyle))
                .sheet("存量建筑垃圾堆体治理进度月报表")
                //设置默认样式及写入头信息开始的行数
                .useDefaultStyle(true).relativeHeadRowIndex(3)
                //这里的addsumColomn方法是个添加合计的方法,可删除
                .doWrite(list);

    }
}
