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
public class ExceContractorPriceVO  {
    /**
     *序号
     */
    @Excel(name = "序号")
    private String seq;
    /**
     *名称、规格、型号
     */
    @Excel(name = "名称、规格、型号")
    private String name;
    /**
     * 变值权重 B
     */
    @Excel(name = "变值权重 B")
    private String weight;
    /**
     * 基本价格指数 F0
     */
    @Excel(name = "基本价格指数 F0")
    private String basicprice;
    /**
     *现行价格指数 F1
     */
    @Excel(name = "现行价格指数 F1")
    private String currentprice;
    /**
     *备注
     */
    @Excel(name = "备注")
    private String remark;
}
