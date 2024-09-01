package com.ehzyil.excel.easypoi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.hutool.core.util.RandomUtil;
import com.ehzyil.excel.bean.StudentTransfer;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomizeExport {
    public static void main(String[] args) throws IOException {
        //封装表头
        List<ExcelExportEntity> entityList = new ArrayList<ExcelExportEntity>();
        entityList.add(new ExcelExportEntity("姓名", "name"));
        entityList.add(new ExcelExportEntity("班级", "className"));
        ExcelExportEntity entityTime = new ExcelExportEntity("手机号", "phoneNumber");
        entityTime.setWidth(20.0);
        entityList.add(entityTime);

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
                entityList, dataList);
        String file = ExportExample.class.getResource("/").getFile();
        System.out.println(file+"/temps/easypoi-user1.xls");
        FileOutputStream fos = new FileOutputStream(file+"/easypoi-user1.xlsx");
        workbook.write(fos);
        fos.close();

    }
}
