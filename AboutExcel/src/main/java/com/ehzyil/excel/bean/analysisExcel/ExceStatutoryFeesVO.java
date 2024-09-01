package com.ehzyil.excel.bean.analysisExcel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 规费、税金项目计价表(表-13)
 *@program: eps-inventory
 *@description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceStatutoryFeesVO  {
    /**
     *序号
     */
    @Excel(name = "序号")
    private String seq;
    /**
     * 项目名称
     */
    @Excel(name = "项 目 名 称")
    private String name;
    /**
     * 计算基础
     */
    @Excel(name = "计 算 基 础")
    private String calbasis;
    /**
     *计算基数
     */
    @Excel(name = "计算基数")
    private String qtyformula;
    /**
     *计算费率（%）
     */
    @Excel(name = "计算费率（%）")
    private String rate;
    /**
     *金额（元）
     */
    @Excel(name = "金额（元）")
    private String total;

}
