package com.ehzyil.excel.bean.analysisExcel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 材料（工程设备）暂估单价及调整表
 *@program: eps-inventory
 *@description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelProvisionalMaterialVO  {
    /**
     *序号
     */
    @Excel(name = "序号")
    private String seq;
    /**
     *材料(工程设备)名称、规格、型号
     */
    @Excel(name = "材料(工程设备)名称、规格、型号")
    private String nameAndSpecification;
    /**
     *计量单位
     */
    @Excel(name = "计量单位")
    private String unit;
    /**
     *数量_暂估
     */
    @Excel(name = "数量_暂估")
    private String provisionalquantity;
    /**
     * 确认
     */
    @Excel(name = "确认")
    private String quantity;
    /**
     * 暂估(元)_单价
     */
    @Excel(name = "暂估(元)_单价")
    private String provisionalprice;
    /**
     * 暂估(元)_合价
     */
    @Excel(name = "暂估(元)_合价")
    private String provisionaltotal;
    /**
     * 确认(元)_单价
     */
    @Excel(name = "确认(元)_单价")
    private String price;
    /**
     * 确认(元)_合价
     */
    @Excel(name = "确认(元)_合价")
    private String total;
    /**
     * 差额±(元)_单价
     */
    @Excel(name = "差额±(元)_单价")
    private String pricedifference;
    /**
     * 差额±(元)_合价
     */
    @Excel(name = "差额±(元)_合价")
    private String totaldifference;
    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;
}
