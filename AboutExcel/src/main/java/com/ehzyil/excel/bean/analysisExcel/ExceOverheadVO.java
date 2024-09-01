package com.ehzyil.excel.bean.analysisExcel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 总价措施项目清单与计价表
 *@program: eps-inventory
 *@description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceOverheadVO  {
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
     * 项目名称
     */
    @Excel(name = "项目名称")
    private String name;
    /**
     * 计算基础
     */
    @Excel(name = "计算基础")
    private String qtyformula;
    /**
     *费率(%)
     */
    @Excel(name = "费率(%)")
    private String rate;
    /**
     *金额(元)
     */
    @Excel(name = "金额(元)")
    private String total;
    /**
     *调整费率(%)
     */
    @Excel(name = "调整费率(%)")
    private String originalrate;
    /**
     *调整后金额(元)
     */
    @Excel(name = "调整后金额(元)")
    private String originaltotal;
    /**
     *备注
     */
    @Excel(name = "备注")
    private String remark;
}
