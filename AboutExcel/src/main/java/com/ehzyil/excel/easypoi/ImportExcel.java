package com.ehzyil.excel.easypoi;


import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.ehzyil.excel.bean.Qd_provisional_material_item;
import com.ehzyil.excel.bean.analysisExcel.ExcelProvisionalMaterialVO;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

import static com.ehzyil.excel.ResourceLoader.getResourceAsStream;

public class ImportExcel {

    public static void main(String[] args) {

        // 读取 Excel 文件
        try (InputStream inputStream = getResourceAsStream("材料（工程设备）暂估单价及调整表.xlsx")) {
            ImportParams params = new ImportParams();
            //表头在第几行
            params.setTitleRows(3);
            //表头有两行
            params.setHeadRows(2);
            //是否需要通过key-value导入方法，获取特定字段
            params.setReadSingleCell(true);

            List<Map<String, Object>> qd_provisional_material_itemList = new ArrayList<>();
            Integer seq = 0;

            List<ExcelProvisionalMaterialVO> result = ExcelImportUtil.importExcel(inputStream, ExcelProvisionalMaterialVO.class, params);
            if (result != null && result.size() > 0) {
                for (ExcelProvisionalMaterialVO exceOverheadVO : result) {
                    if (StringUtils.isNotEmpty(exceOverheadVO.getSeq()) && StringUtils.isNumeric(exceOverheadVO.getSeq())) {
                        Qd_provisional_material_item qd_provisional_material_item = new Qd_provisional_material_item();
                        BeanUtil.copyProperties(exceOverheadVO, qd_provisional_material_item);
                        // 设置名称和型号规格
                        if (StringUtils.isNotEmpty(exceOverheadVO.getNameAndSpecification())) {
                            String[] nameAndSpecification = exceOverheadVO.getNameAndSpecification().split(" ");
                            qd_provisional_material_item.setName(nameAndSpecification[0]);
                            qd_provisional_material_item.setSpecification(nameAndSpecification[1]);
                            if (nameAndSpecification.length > 1) {
                                qd_provisional_material_item.setSpecification(nameAndSpecification[1]);
                            }
                        }
                        qd_provisional_material_item.setId(UUID.fastUUID().toString());
                        qd_provisional_material_item.setSeq(Integer.valueOf(exceOverheadVO.getSeq()));
                        qd_provisional_material_item.setTotal(new BigDecimal(Optional.ofNullable(exceOverheadVO.getTotal()).orElse("0")));
                        qd_provisional_material_itemList.add(BeanUtil.beanToMap(qd_provisional_material_item));
                    }
                }
            }
            System.out.println(qd_provisional_material_itemList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
