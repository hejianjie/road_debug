package com.beyond.zjxt.config.web;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.beyond.zjxt.core.shiro.ShiroKit;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author :zjk
 * @Date :Create in 19:28 2020-12-16
 * @Description
 **/
public class MonthSheetWriteHandler implements SheetWriteHandler {
    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = simpleDateFormat.format(date);
        int month = ShiroKit.getSessionAttr("month");
        int year = ShiroKit.getSessionAttr("year");
        String dept = ShiroKit.getUser().getDeptName();
        Workbook workbook = writeWorkbookHolder.getWorkbook();
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight((short) 400);
        cellStyle.setFont(font);
        Font font1 = workbook.createFont();
        font1.setBold(true);
        font1.setFontHeight((short) 300);
        CellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setFont(font1);
        if (writeSheetHolder.getSheetNo() == 0) {
            // 小修汇总表
            Sheet sheet = workbook.getSheetAt(0);
            //设置标题
            Row row1 = sheet.createRow(0);
            row1.setHeight((short) 800);
            Cell cell1 = row1.createCell(0);
            cell1.setCellValue(year + "年大连市干线公路小修工程完成量汇总表(" + month + "月份）");
            cell1.setCellStyle(cellStyle);
            sheet.addMergedRegionUnsafe(new CellRangeAddress(0, 0, 0, 12));
            //设置填表日期,填报人,联系方式
            Row row2 = sheet.createRow(1);
            row2.setHeight((short) 500);
            Cell row2Cell1 = row2.createCell(0);
            row2Cell1.setCellValue("填报单位（盖章）：" + dept);
            Cell row2Cell2 = row2.createCell(9);

            row2Cell2.setCellValue("填报日期：" + formatDate);
            row2Cell1.setCellStyle(cellStyle1);
            row2Cell2.setCellStyle(cellStyle1);
        }
        if (writeSheetHolder.getSheetNo() == 1) {
            // 小修明细表
            Sheet sheet1 = workbook.getSheetAt(1);
            //设置标题
            Row sheet1Row = sheet1.createRow(0);
            sheet1Row.setHeight((short) 800);
            Cell sheet1RowCell = sheet1Row.createCell(0);
            sheet1RowCell.setCellValue(year + "年" + dept +"干线公路小修工程完成情况明细表(" + month + "月份）");
            sheet1RowCell.setCellStyle(cellStyle);
            sheet1.addMergedRegionUnsafe(new CellRangeAddress(0, 0, 0, 20));
            //设置填表日期,填报人,联系方式
            Row sheet1Row1 = sheet1.createRow(1);
            Cell sheet1Row1Cell = sheet1Row1.createCell(0);
            sheet1Row1Cell.setCellValue("填报单位（盖章）：" + dept);
            Cell sheet1Row1Cell1 = sheet1Row1.createCell(15);
            sheet1Row1Cell1.setCellValue("填报日期：" + formatDate);
            sheet1Row1Cell.setCellStyle(cellStyle1);
            sheet1Row1Cell1.setCellStyle(cellStyle1);
        }
    }
}
