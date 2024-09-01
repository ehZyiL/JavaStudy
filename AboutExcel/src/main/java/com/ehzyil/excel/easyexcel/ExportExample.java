package com.ehzyil.excel.easyexcel;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.excel.EasyExcel;
import com.ehzyil.excel.bean.StudentTransfer;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExportExample {
    public static void main(String[] args) throws Exception {
        List<StudentTransfer> dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            StudentTransfer StudentTransfer = new StudentTransfer();
            StudentTransfer.setName("张三" + i);
            StudentTransfer.setClassName("班级"+ i);
            StudentTransfer.setPhoneNumber(RandomUtil.randomNumbers(11));
            dataList.add(StudentTransfer);
        }

        EasyExcel.write(ExportExample.class.getResource("/").getFile()+"/easypoi-user1.xls", StudentTransfer.class).sheet("用户信息").doWrite(dataList);

    }

}
