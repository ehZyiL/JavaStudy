package com.ehzyil.excel.bean.analysisExcel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 单位工程投标报价汇总
 *@program: eps-inventory
 *@description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelDivisionalworksVO  {
    /**
     *序号
     */
    @Excel(name = "序号")
    private String seq;
    /**
     *项目编码
     */
    @Excel(name = "项目编码")
    private String ref;
    /**
     *项目名称
     */
    @Excel(name = "项目名称")
    private String name;
    /**
     *项目特征描述
     */
    @Excel(name = "项目特征描述")
    private String attr;
    /**
     *计量单位
     */
    @Excel(name = "计量单位")
    private String unit;
    /**
     *工程量
     */
    @Excel(name = "工程量")
    private String quantity;
    /**
     *金额（元）_综合单价
     */
    @Excel(name = "金额（元）_综合单价")
    private String price;
    /**
     *合价
     */
    @Excel(name = "合价")
    private String total;
    /**
     *其中_暂估价
     */
    @Excel(name = "其中_暂估价")
    private String appraisal;
}
