package com.ehzyil.excel.bean;

import lombok.Data;

@Data
public class Qd_provisional_material_item {
    // 序列化版本号
    private static final long serialVersionUID = 1L;

    /**
     * 用于兼容老写法
     */
    public static final transient Qd_provisional_material_item ROW_MAPPER = new Qd_provisional_material_item();

    // 主键名称
    public static final String ID = "id";
    // 普通属性名称
    public static final String REF = "ref";
    public static final String REMARK = "remark";
    public static final String SPECIFICATION = "specification";
    public static final String TOTALDIFFERENCE = "totaldifference";
    public static final String PROVISIONALQUANTITY = "provisionalquantity";
    public static final String PROVISIONALTOTAL = "provisionaltotal";
    public static final String TOTAL = "total";
    public static final String PROVISIONALPRICE = "provisionalprice";
    public static final String PRICE = "price";
    public static final String PRICEDIFFERENCE = "pricedifference";
    public static final String QUANTITY = "quantity";
    public static final String PID = "pid";
    public static final String NAME = "name";
    public static final String UNIT = "unit";
    public static final String SEQ = "seq";
    public static final String PROVISIONAL_MATERIAL_ID = "provisional_material_id";
    public static final String UNITWORKS_ID = "unitworks_id";
    public static final String QDPROJECT_ID = "qdproject_id";
    public static final String SECTIONALWORKS_ID = "sectionalworks_id";

    /**
     * 编号
     */
    private String id;

    /**
     * 编码
     */
    private String ref;

    /**
     * 备注
     */
    private String remark;

    /**
     * 型号规格
     */
    private String specification;

    /**
     * 合价差额（元)
     */
    private java.math.BigDecimal totaldifference;

    /**
     * 暂估数量
     */
    private java.math.BigDecimal provisionalquantity;

    /**
     * 暂估合价(元)
     */
    private java.math.BigDecimal provisionaltotal;

    /**
     * 确认合价(元)
     */
    private java.math.BigDecimal total;

    /**
     * 暂估单价(元)
     */
    private java.math.BigDecimal provisionalprice;

    /**
     * 确认单价(元)
     */
    private java.math.BigDecimal price;

    /**
     * 单价差额（元)
     */
    private java.math.BigDecimal pricedifference;

    /**
     * 确认数量
     */
    private java.math.BigDecimal quantity;

    /**
     * 父id
     */
    private String pid;

    /**
     * 名称
     */
    private String name;

    /**
     * 计量单位
     */
    private String unit;

    /**
     * 顺序号
     */
    private Integer seq;

    /**
     * 暂估价材料ID[QD_PROVISIONAL_MATERIAL.ID]
     */
    private String provisional_material_id;

    /**
     * 单位工程id【QD_UNITWORKS.id】
     */
    private String unitworks_id;

    /**
     * 建设项目id【qd_QDROJECT.id】
     */
    private String qdproject_id;

    /**
     * 单项工程id【QD_SECTIONALWORKS.ID】
     */
    private String sectionalworks_id;
}