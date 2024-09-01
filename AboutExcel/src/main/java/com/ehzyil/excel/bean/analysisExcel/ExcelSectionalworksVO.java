package com.ehzyil.excel.bean.analysisExcel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 单项工程
 *@program: eps-inventory
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelSectionalworksVO  {
    /**
     * id
     */
    public String id;
    /**
     * 工程名称
     */
    public String name;
    /**
     * 工程路径
     */
    public String filepath;
}
