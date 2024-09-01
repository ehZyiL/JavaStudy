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
public class ExcelUnitworksVO  {
    /**
     *序号
     */
    @Excel(name = "序号")
    private String seq;
    /**
     *汇 总 内 容
     */
    @Excel(name = "汇 总 内 容")
    private String name;
    /**
     *金额(元)
     */
    @Excel(name = "金额(元)")
    private String total;
    /**
     *其中：暂估价(元)
     */
    @Excel(name = "其中：暂估价(元)")
    private String appraisal;
}
