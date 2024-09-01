package com.ehzyil.excel.bean.analysisExcel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 计日工
 *@program: eps-inventory
 *@description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceOtherDayVO  {
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
     * 单位
     */
    @Excel(name = "单位")
    private String unit;
    /**
     *暂定数量
     */
    @Excel(name = "暂定数量")
    private String provisionalquantity;
    /**
     *金额(元)
     */
    @Excel(name = "实际数量")
    private String quantity;
    /**
     *综合单价（元）
     */
    @Excel(name = "综合单价（元）")
    private String price;
    /**
     *调整后金额(元)
     */
    @Excel(name = "合价（元）_暂定")
    private String provisionaltotal;
    /**
     *实际
     */
    @Excel(name = "实际")
    private String total;
}
