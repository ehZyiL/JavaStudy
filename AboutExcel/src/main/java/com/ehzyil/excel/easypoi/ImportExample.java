package com.ehzyil.excel.easypoi;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.core.bean.BeanUtil;
import com.ehzyil.excel.bean.StudentTransfer;

import java.io.InputStream;
import java.util.List;

import static com.ehzyil.excel.ResourceLoader.getResourceAsStream;

public class ImportExample {
    public static void main(String[] args) {
        try (InputStream inputStream = getResourceAsStream("档案去向 的副本.xlsx")) {

            ImportParams params = new ImportParams();
            //表头在第几行
            params.setTitleRows(0);
            //表头有两行
            params.setHeadRows(1);
            //是否需要通过key-value导入方法，获取特定字段
            params.setReadSingleCell(true);

            List<StudentTransfer> result = ExcelImportUtil.importExcel(inputStream, StudentTransfer.class, params);
            if (result != null && result.size() > 0) {
                result.forEach(System.out::println);
                System.out.println(BeanUtil.beanToMap(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
