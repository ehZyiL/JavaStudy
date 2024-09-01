package com.ehzyil.excel.bean.analysisExcel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 其他项目清单与计价汇总表
 *@program: eps-inventory
 *@description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceOtherContractingVO  {
    /**
     *序号
     */
    @Excel(name = "序号")
    private String seq;
    /**
     * 项目名称
     */
    @Excel(name = "项目名称")
    private String name;
    /**
     * 项目价值(元)
     */
    @Excel(name = "项目价值(元)")
    private String quantity;
    /**
     *服务内容
     */
    @Excel(name = "服务内容")
    private String content;
    /**
     *计算基础
     */
    @Excel(name = "计算基础")
    private String calbasis;
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
     *备注
     */
    @Excel(name = "备注")
    private String remark;
}
