package com.ehzyil.excel.easypoi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.util.RandomUtil;
import com.ehzyil.excel.bean.StudentTransfer;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
        //生成excel文档
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户","用户信息"),
                StudentTransfer.class, dataList);
        String file = ExportExample.class.getResource("/").getFile();
        System.out.println(file+"/temps/easypoi-user1.xls");
        FileOutputStream fos = new FileOutputStream(file+"/easypoi-user1.xls");
        workbook.write(fos);
        fos.close();
    }

}
