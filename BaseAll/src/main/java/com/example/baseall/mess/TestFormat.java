package com.example.baseall.mess;

public class TestFormat {
    public static String formatSqlString(String sqlString) {
        // 将 \\n 替换为换行符并去除所有引号
        sqlString = sqlString.replace("\\n", "\n").replaceAll("\"", "");

        // 分割成多行
        String[] lines = sqlString.split("\\+");
        StringBuilder formattedSql = new StringBuilder();

        for (String line : lines) {
            line = line.trim(); // 去除行首尾的空白字符
            line = line.replace(":", ""); // 去除冒号

            if (!line.isEmpty()) { // 只有在非空行才进行添加
                if (line.toLowerCase().startsWith("select") ||
                        line.toLowerCase().startsWith("from") ||
                        line.toLowerCase().startsWith("where") ||
                        line.toLowerCase().contains("order by")) {
                    formattedSql.append(line).append("\n");
                } else if (line.toLowerCase().startsWith("and") || line.toLowerCase().contains("in (")) {
                    formattedSql.append("  ").append(line).append("\n");
                } else {
                    formattedSql.append("     ").append(line).append("\n");
                }
            }
        }

        return formattedSql.toString().trim();
    }
    public static void main(String[] args) {
        String sqlString = "\"select jc_smj_bg.*, jc_smjdy.sfyxq,jc_smjdy.smjbs\\n\" +\n" +
                "                \"  from jc_smj_bg, jc_smjdy\\n\" +\n" +
                "                \" where jc_smj_bg.smjdybh = jc_smjdy.id\\n\" +\n" +
                "                \"   and jc_smj_bg.ztbh = :ztbh order by jc_smj_bg.sxh\\n\"";

        String formattedSql = formatSqlString(sqlString);
        System.out.println(formattedSql);
    }
}
