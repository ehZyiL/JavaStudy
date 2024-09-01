package com.ehzyil.excel.bean.analysisExcel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 承包人提供材料和工程设备一览表
 *@program: eps-inventory
 *@description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceContractorCostVO  {
    /**
     *序号
     */
    @Excel(name = "序号")
    private String seq;
    /**
     * 名称、规格、型号
     */
    @Excel(name = "名称、规格、型号")
    private String name;
    /**
     * 单位
     */
    @Excel(name = "单位")
    private String unit;
    /**
     *数量
     */
    @Excel(name = "数量")
    private String quantity;
    /**
     *风险系数（%）
     */
    @Excel(name = "风险系数（%）")
    private String providerrate;
    /**
     *基准单价（元）
     */
    @Excel(name = "基准单价（元）")
    private String referenceprice;
    /**
     *投标单价（元）
     */
    @Excel(name = "投标单价（元）")
    private String bidprice;
    /**
     *发承包人确认单价（元）
     */
    @Excel(name = "发承包人确认单价（元）")
    private String confirmprice;
    /**
     *备注
     */
    @Excel(name = "备注")
    private String remark;
}
