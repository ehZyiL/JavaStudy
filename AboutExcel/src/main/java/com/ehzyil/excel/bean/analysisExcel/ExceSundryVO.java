package com.ehzyil.excel.bean.analysisExcel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 暂列金额明细表(表-12-1)
 *@program: eps-inventory
 *@description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceSundryVO  {
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
     * 计量单位
     */
    @Excel(name = "计量单位")
    private String unit;
    /**
     * 暂定金额(元)
     */
    @Excel(name = "暂定金额(元)")
    private String total;
    /**
     *备注
     */
    @Excel(name = "备注")
    private String remark;
}
