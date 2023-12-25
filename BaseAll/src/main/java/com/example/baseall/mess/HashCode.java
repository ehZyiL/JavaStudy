package com.example.baseall.mess;

import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * @author ehyzil
 * @Description
 * @create 2023-12-2023/12/25-11:04
 */
public class HashCode {
    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 1000; i++) {
            String str = getRandomString(60) + i;

            int hash1 = str.hashCode();
            int hash2 = str.hashCode() & Integer.MAX_VALUE;
            System.out.println(hash1);
            System.out.println(hash2);

            String s = String.valueOf(str.hashCode() == Integer.MAX_VALUE ? Integer.MAX_VALUE : Math.abs(str.hashCode()));
            System.out.println(s);
        }
        String hashStr0 = "35953305172933/";
        System.out.println(hashStr0.hashCode());            // 2147483647   Integer.MAX_VALUE
        System.out.println(Math.abs(hashStr0.hashCode()));  // 2147483647   Integer.MAX_VALUE
        System.out.println("-------------------");
        String hashStr = "359533051729330";
        System.out.println(hashStr.hashCode());             // -2147483648  Integer.MIN_VALUE
        System.out.println(Math.abs(hashStr.hashCode()));   // -2147483648  Integer.MIN_VALUE
        System.out.println(hashStr.hashCode() & Integer.MAX_VALUE);
        System.out.println("-------------------");
        String hashStr2 = "56800004874";
        System.out.println(hashStr2.hashCode());            // -2082984168
        System.out.println(Math.abs(hashStr2.hashCode()));  // 2082984168
        System.out.println("-------------------");
        String hashStr3 = "";
        System.out.println(hashStr3.hashCode());            // 0
        System.out.println(Math.abs(hashStr3.hashCode()));  // 0
        System.out.println("-------------------");


        System.out.println(getHashCode(hashStr0));
        System.out.println(getHashCode(hashStr));
        System.out.println(getHashCode(hashStr2));
        System.out.println(getHashCode(hashStr3));
    }

    /**
     * @param str
     * @return
     * @throws Exception
     */
    public static String getHashCode(String str) throws Exception {
        if (StringUtils.isEmpty(str)) {
            throw new Exception("不能为空");
        }
        int i = str.hashCode();
        if (i != 0) {
            String s = String.valueOf(str.hashCode() & Integer.MAX_VALUE);
            return s;
        }
        return getHashCode(str);
    }

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
