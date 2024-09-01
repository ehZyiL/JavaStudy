package com.ehzyil.excel.bean.analysisExcel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 专业工程暂估价及结算表(表-12-3)
 *@program: eps-inventory
 *@description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceProvisionalSpecialtyVO  {
    /**
     *序号
     */
    @Excel(name = "序号")
    private String seq;
    /**
     *工 程 名 称
     */
    @Excel(name = "工 程 名 称")
    private String name;
    /**
     * 工程内容
     */
    @Excel(name = "工程内容")
    private String content;
    /**
     * 暂估金额（元）
     */
    @Excel(name = "暂估金额（元）")
    private String provisionaltotal;
    /**
     *结算金额（元）
     */
    @Excel(name = "结算金额（元）")
    private String settlementtotal;
    /**
     *差额±(元)
     */
    @Excel(name = "差额±(元)")
    private String totaldifference;
    /**
     *备注
     */
    @Excel(name = "备注")
    private String remark;
}
