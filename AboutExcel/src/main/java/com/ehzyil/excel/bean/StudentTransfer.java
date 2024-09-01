package com.ehzyil.excel.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.util.Beta;

import java.beans.BeanProperty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentTransfer {
    @ExcelProperty("姓名")
    @Excel(name = "姓名")
    private String name;
    @ExcelProperty("班级")
    @Excel(name = "班级")
    private String className;
    @ExcelProperty("辅导员姓名")
    @Excel(name = "辅导员姓名")
    private String counselorName;
    @ExcelProperty("转往单位")
    @Excel(name = "转往单位")
    private String transferUnit;
    @ExcelProperty("生源地")
    @Excel(name = "生源地")
    private String originPlace;
    @ExcelProperty("联系电话")
    @Excel(name = "联系电话")
    private String phoneNumber;
    @ExcelProperty("档案转寄详细地址")
    @Excel(name = "档案转寄详细地址")
    private String archiveAddress;
    @ExcelProperty("档案转寄单位邮编")
    @Excel(name = "档案转寄单位邮编")
    private String archivePostalCode;
    @ExcelProperty("档案转寄联系人")
    @Excel(name = "档案转寄联系人")
    private String archiveContactPerson;
    @ExcelProperty("档案转寄联系电话")
    @Excel(name = "档案转寄联系电话")
    private String archiveContactNumber;
}