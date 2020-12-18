package com.beyond.zjxt.modular.road.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.converters.string.StringImageConverter;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @Author: Wdz
 * @Date 2020/12/17 10:39
 * @Description: 小修明细表表头
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ContentRowHeight(35)
@HeadRowHeight(40)
public class SmallScaleMeterHead {
    @ColumnWidth(10)
    @ExcelProperty(value = {"序号"}, index = 0)
    private Long orderNum;

    @ColumnWidth(15)
    @ExcelProperty(value = {"路线名称"}, index = 1)
    private String routeName;

    @ColumnWidth(20)
    @ExcelProperty(value = {"项目名称"}, index = 2)
    private String projectName;

    @ColumnWidth(20)
    @ExcelProperty(value = {"细目名称"}, index = 3)
    private String smallProjectName;

    @ColumnWidth(15)
    @ExcelProperty(value = {"建设性质"}, index = 4)
    private String projectType;

    @ColumnWidth(20)
    @ExcelProperty(value = {"起止桩号"}, index = 5)
    private String beginToEndStake;

    @ColumnWidth(15)
    @ExcelProperty(value = {"计划","几何尺寸"}, index = 6)
    private String specificSize;

    @ColumnWidth(10)
    @ExcelProperty(value = {"计划","单位"}, index = 7)
    private String unit;

    @ColumnWidth(10)
    @ExcelProperty(value = {"计划","单价(元)"}, index = 8)
    private BigDecimal unitPrice;

    @ColumnWidth(10)
    @ExcelProperty(value = {"计划","作业工作量"}, index = 9)
    private BigDecimal workAmount;

    @ColumnWidth(10)
    @ExcelProperty(value = {"计划","作业频率"}, index = 10)
    private String operationFrequency;

    @ColumnWidth(10)
    @ExcelProperty(value = {"计划","金额(万元)"}, index = 11)
    private BigDecimal price;

    @ColumnWidth(10)
    @ExcelProperty(value = {"计划","修复前图片"}, index = 12, converter = StringImageConverter.class)
    private String beforeImg;

    @ColumnWidth(15)
    @ExcelProperty(value = {"完成","几何尺寸"}, index = 13)
    private String finSpecificSize;

    @ColumnWidth(10)
    @ExcelProperty(value = {"完成", "单位"}, index = 14)
    private String finUnit;

    @ColumnWidth(10)
    @ExcelProperty(value = {"完成", "单价(元)"}, index = 15)
    private BigDecimal finUnitPrice;

    @ColumnWidth(10)
    @ExcelProperty(value = {"完成", "作业工作量"}, index = 16)
    private BigDecimal finWorkAmount;

    @ColumnWidth(10)
    @ExcelProperty(value = {"完成", "作业频率"}, index = 17)
    private String finOperationFrequency;

    @ColumnWidth(10)
    @ExcelProperty(value = {"完成", "金额(万元)"}, index = 18)
    private BigDecimal finPrice;

    @ColumnWidth(10)
    @ExcelProperty(value = {"完成", "修复后图片"}, index = 19, converter = StringImageConverter.class)
    private String afterImg;

    @ColumnWidth(10)
    @ExcelProperty(value = {"备注"}, index = 20)
    private String note;


}
