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
public class ExceOtherVO  {
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
     * 金额(元)
     */
    @Excel(name = "金额(元)")
    private String total;
    /**
     *结算金额(元)
     */
    @Excel(name = "结算金额(元)")
    private String settlementtotal;
    /**
     *备注
     */
    @Excel(name = "备注")
    private String remark;
}
