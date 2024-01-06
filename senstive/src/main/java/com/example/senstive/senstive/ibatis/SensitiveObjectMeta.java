package com.example.senstive.senstive.ibatis;

import com.example.senstive.senstive.ano.SensitiveField;
import lombok.Data;

import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Data
public class SensitiveObjectMeta {

    private static final String JAVA_LANG_OBJECT = "java.lang.object";
    /**
     * 是否启用脱敏
     */
    private Boolean enabledSensitiveReplace;

    /**
     * 类名
     */
    private String className;

    /**
     * 标注 SensitiveField 的成员
     */
    private List<SensitiveFieldMeta> sensitiveFieldMetaList;

    /**
     * 构建 SensitiveObjectMeta 对象
     *
     * @param param 对象实例
     * @return Optional<SensitiveObjectMeta> 可选的 SensitiveObjectMeta 对象
     */
    public static Optional<SensitiveObjectMeta> buildSensitiveObjectMeta(Object param) {
        if (isNull(param)) {
            return Optional.empty();
        }
        Class<?> clazz = param.getClass();
        SensitiveObjectMeta sensitiveObjectMeta = new SensitiveObjectMeta();
        sensitiveObjectMeta.setClassName(clazz.getName());

        List<SensitiveFieldMeta> sensitiveFieldMetaList = newArrayList();
        sensitiveObjectMeta.setSensitiveFieldMetaList(sensitiveFieldMetaList);
        boolean sensitiveField = parseAllSensitiveField(clazz, sensitiveFieldMetaList);
        sensitiveObjectMeta.setEnabledSensitiveReplace(sensitiveField);
        return Optional.of(sensitiveObjectMeta);
    }

    /**
     * 解析类中的所有 SensitiveField 字段
     *
     * @param clazz                  类实例
     * @param sensitiveFieldMetaList SensitiveFieldMeta 对象列表
     * @return boolean 是否包含 SensitiveField
     */
    private static boolean parseAllSensitiveField(Class<?> clazz, List<SensitiveFieldMeta> sensitiveFieldMetaList) {
        Class<?> tempClazz = clazz;
        boolean hasSensitiveField = false;

        while ((nonNull(tempClazz)) && !JAVA_LANG_OBJECT.equalsIgnoreCase(tempClazz.getName())) {
            for (java.lang.reflect.Field field : tempClazz.getDeclaredFields()) {
                SensitiveField sensitiveField = field.getAnnotation(SensitiveField.class);
                if (nonNull(sensitiveField)) {
                    SensitiveFieldMeta sensitiveFieldMeta = new SensitiveFieldMeta();
                    sensitiveFieldMeta.setName(field.getName());
                    sensitiveFieldMeta.setBindField(sensitiveField.bind());
                    sensitiveFieldMetaList.add(sensitiveFieldMeta);
                    hasSensitiveField = true;
                }

            }
            tempClazz = tempClazz.getSuperclass();
        }
        return hasSensitiveField;
    }


    @Data
    public static class SensitiveFieldMeta {
        /**
         * 默认根据字段名，找db中同名的字段
         */
        private String name;

        /**
         * 绑定的数据库字段别名
         */
        private String bindField;
    }
}