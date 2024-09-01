package com.ehzyil.excel.bean.analysisExcel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 发包人提供材料和工程设备一览表(表-20)
 *@program: eps-inventory
 *@description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceEmployerVO  {
    /**
     *序号
     */
    @Excel(name = "序号")
    private String seq;
    /**
     *材料(工程设备)名称、规格、型号
     */
    @Excel(name = "材料(工程设备)名称、规格、型号")
    private String name;
    /**
     *单位
     */
    @Excel(name = "单位")
    private String unit;
    /**
     *数量
     */
    @Excel(name = "数量")
    private String quantity;
    /**
     * 单价（元）
     */
    @Excel(name = "单价（元）")
    private String price;
    /**
     * 交货方式
     */
    @Excel(name = "交货方式")
    private String delivery;
    /**
     *送达地点
     */
    @Excel(name = "送达地点")
    private String location;
    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;
}
