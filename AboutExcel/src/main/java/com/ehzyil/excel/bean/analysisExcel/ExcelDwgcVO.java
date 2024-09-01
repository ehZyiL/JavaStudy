package com.ehzyil.excel.bean.analysisExcel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 *@program: eps-inventory
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelDwgcVO  {
    /**
     *工程名称
     */
    @Excel(name = "单项工程招标控制价汇总表_工程名称：")
    private String name_sectionalworks;
    /**
     *单位工程名称
     */
    @Excel(name = "单位工程名称")
    private String name;
    /**
     *金额(元)
     */
    @Excel(name = "金额(元)")
    private BigDecimal total;
    /**
     *招标人
     */
    @Excel(name = "其中：（元）_暂估价")
    private String provisionalmaterial;
    /**
     *招标人
     */
    @Excel(name = "安全文明施工费")
    private String costforhse;
    /**
     * 规费
     */
    @Excel(name = "规费")
    private String statutoryfees;

}
