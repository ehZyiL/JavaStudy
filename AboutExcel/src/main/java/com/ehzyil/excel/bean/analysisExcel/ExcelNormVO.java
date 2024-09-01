package com.ehzyil.excel.bean.analysisExcel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *@program: eps-inventory
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelNormVO  {
    /**
     * 定额编号
     */
    @ExcelColumn(index = 0)
    private String ref;
    /**
     * 定额项目名称
     */
    @ExcelColumn(index = 1)
    private String name;
    /**
     * 定额单位
     */
    @ExcelColumn(index = 2)
    private String unit;
    /**
     * 数量
     */
    @ExcelColumn(index = 3)
    private String quantity;
    /**
     * 人工费
     */
    @ExcelColumn(index = 4)
    private String labor;
    /**
     * 材料费
     */
    @ExcelColumn(index = 5)
    private String material;
    /**
     * 机械费
     */
    @ExcelColumn(index = 6)
    private String machine;
    /**
     * 管理费和利润
     */
    @ExcelColumn(index = 7)
    private String overhead;
    /**
     * 人工费
     */
    @ExcelColumn(index = 8)
    private String labor_price;
    /**
     * 材料费
     */
    @ExcelColumn(index = 9)
    private String material_price;
    /**
     * 机械费
     */
    @ExcelColumn(index = 10)
    private String machine_price;
    /**
     * 管理费和利润
     */
    @ExcelColumn(index = 11)
    private String overhead_price;
}
// 定义注解用于标记Excel列索引
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface ExcelColumn {
    int index();
}