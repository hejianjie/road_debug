package com.beyond.zjxt.modular.road.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @Author :zjk
 * @Date :Create in 15:57 2020-12-16
 * @Description 小修汇总表表头
 **/
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ContentRowHeight(35)
@HeadRowHeight(40)
public class SummaryOfMinorRepairsHead {
    @ColumnWidth(10)
    @ExcelProperty(value = {"序号"}, index = 0)
    private Long orderNum;

    @ColumnWidth(20)
    @ExcelProperty(value = {"项目名称"}, index = 1)
    private String projectName;

    @ColumnWidth(20)
    @ExcelProperty(value = {"细目名称"}, index = 2)
    private String detailProjectName;

    @ColumnWidth(15)
    @ExcelProperty(value = {"建设性质"}, index = 3)
    private String projectType;

    @ColumnWidth(15)
    @ExcelProperty(value = {"单位"}, index = 4)
    private String unit;

    @ColumnWidth(15)
    @ExcelProperty(value = {"单价（元）"}, index = 5)
    private BigDecimal unitPrice;



    @ColumnWidth(10)
    @ExcelProperty(value = {"计划","作业工作量"}, index = 6)
    private BigDecimal workAmount;

    @ColumnWidth(10)
    @ExcelProperty(value = {"计划","作业频率"}, index = 7)
    private String operationFrequency;

    @ColumnWidth(10)
    @ExcelProperty(value = {"计划","金额(万元)"}, index = 8)
    private BigDecimal price;



    @ColumnWidth(10)
    @ExcelProperty(value = {"完成","作业工程量"}, index = 9)
    private BigDecimal finWorkAmount;

    @ColumnWidth(10)
    @ExcelProperty(value = {"完成","作业频率"}, index = 10)
    private String finOperationFrequency;

    @ColumnWidth(10)
    @ExcelProperty(value = {"完成","金额(万元)"}, index = 11)
    private BigDecimal finPrice;

    @ColumnWidth(10)
    @ExcelProperty(value = {"备注"}, index = 12)
    private String note;
}
