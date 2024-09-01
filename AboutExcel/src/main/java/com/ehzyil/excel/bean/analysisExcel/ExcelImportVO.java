package com.ehzyil.excel.bean.analysisExcel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *@program: eps-inventory
 *@description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelImportVO  {

    /**
     * 清单建设项目id
     */
    public String qdproject_id;

    /**
     * 清单业务表id
     */
    public String inventid;

    /**
     * 清单建设项目名称
     */
    public String name;
    /**
     * 类型【1.招标清单 2 .投标人清单 3 控制价清单】
     */
    private Integer type;
    /**
     * 单项工程id
     */
    public String sectionalworks_id;
    /**
     *单位工程id
     */
    private String unitworks_id;
    /**
     *单位工程名称
     */
    private String unitworks_name;
    /**
     *标段name
     */
    private String bid_name;
    /**
     * 当前sheetIndex
     */
    public Integer sheetIndex;
    /**
     * 当前单项工程顺序
     */
    public Integer seq;
    /**
     * sheet模板
     */
    public String sheetmb;
}
