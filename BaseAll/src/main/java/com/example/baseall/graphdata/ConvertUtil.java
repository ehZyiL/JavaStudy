//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.baseall.graphdata;
import java.math.BigDecimal;

public class ConvertUtil {
    public static String createString(Object obj) {
        if (obj == null) {
            return null;
        } else {
            return obj instanceof String ? (String)obj : obj.toString();
        }
    }

    public static Integer createInteger(Object obj) {
        if (obj == null) {
            return null;
        } else {
            return obj instanceof Integer ? (Integer)obj : Integer.valueOf(createString(obj));
        }
    }

    public static Long createLong(Object obj) {
        if (obj == null) {
            return null;
        } else {
            return obj instanceof Long ? (Long)obj : Long.valueOf(createString(obj));
        }
    }

    public static Double createDouble(Object obj) {
        if (obj == null) {
            return null;
        } else {
            return obj instanceof Double ? (Double)obj : Double.valueOf(createString(obj));
        }
    }

    public static BigDecimal createBigDecimal(Object obj) {
        if (obj == null) {
            return null;
        } else {
            return obj instanceof BigDecimal ? (BigDecimal)obj : new BigDecimal(createString(obj));
        }
    }

    public static Boolean createBoolean(Object obj) {
        if (obj == null) {
            return null;
        } else {
            return obj instanceof Boolean ? (Boolean)obj : Boolean.parseBoolean(createString(obj));
        }
    }
}
