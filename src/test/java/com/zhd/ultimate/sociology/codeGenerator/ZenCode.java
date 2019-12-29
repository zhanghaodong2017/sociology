package com.zhd.ultimate.sociology.codeGenerator;

import com.alibaba.fastjson.JSON;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhanghaodong
 * @description 代码生成器-基于数据库：减少不必要的重复代码
 * @date: 2019-12-29 13:53
 * 操作步骤：1设计表结构，2生成mapper文件和代码，3生成Service、Controller和html代码
 */
public class ZenCode {
    private static final String url = "jdbc:mysql://115.29.108.117:3306/zhd";
    private static final String root = "root";
    private static final String password = "123";

    private static final String projectRoot = "/Users/zhanghaodong/work/myproject/sociology/";

    private static final String targetJava = "src/main/java/";
    private static final String targetResources = "src/main/resources/";
    private static final String controller = "com/zhd/ultimate/sociology/controller/";
    private static final String service = "com/zhd/ultimate/sociology/service/";
    private static final String serviceImpl = "com/zhd/ultimate/sociology/service/impl/";
    private static final String html = "templates/";

    private static final String sqlTableColumn = "select COLUMN_NAME,DATA_TYPE,COLUMN_COMMENT,COLUMN_KEY from information_schema.COLUMNS where table_name = ? order by ORDINAL_POSITION";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败！");
        }
    }

    private Configuration config;

    public static void main(String[] args) {
        String tableName = "student_grade";
        String tableEntityName = getEntityName(tableName);
        String firstUpTableName = getFirstUp(tableEntityName);
        List<MyTableColumn> columns = getColumnByTableName(tableName);
        System.out.println(JSON.toJSONString(columns));

        createControllerCode(tableEntityName, firstUpTableName);
        createServiceCode(tableEntityName, firstUpTableName);
        createServiceImplCode(tableEntityName, firstUpTableName);
    }

    private static void createControllerCode(String tableEntityName, String firstUpTableName) {
        try {
            Template controllerTemplate = getTemplate("controller.ftl");

            Map<String, Object> paramsMap = new HashMap<String, Object>();
            paramsMap.put("tableEntityName", tableEntityName);
            paramsMap.put("firstUpTableName", firstUpTableName);
            File file = new File(projectRoot + targetJava + controller, firstUpTableName + "Controller.java");
            System.out.println(file);
            OutputStream fos = new FileOutputStream(file);
            Writer out = new OutputStreamWriter(fos);
            controllerTemplate.process(paramsMap, out);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createServiceCode(String tableEntityName, String firstUpTableName) {
        try {
            Template serviceTemplate = getTemplate("service.ftl");

            Map<String, Object> paramsMap = new HashMap<String, Object>();
            paramsMap.put("tableEntityName", tableEntityName);
            paramsMap.put("firstUpTableName", firstUpTableName);
            File fileService = new File(projectRoot + targetJava + service, firstUpTableName + "Service.java");
            OutputStream fos = new FileOutputStream(fileService);
            Writer out = new OutputStreamWriter(fos);
            serviceTemplate.process(paramsMap, out);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createServiceImplCode(String tableEntityName, String firstUpTableName) {
        try {
            Template serviceTemplate = getTemplate("serviceImpl.ftl");

            Map<String, Object> paramsMap = new HashMap<String, Object>();
            paramsMap.put("tableEntityName", tableEntityName);
            paramsMap.put("firstUpTableName", firstUpTableName);
            File fileService = new File(projectRoot + targetJava + serviceImpl, firstUpTableName + "ServiceImpl.java");
            System.out.println(fileService);
            OutputStream fos = new FileOutputStream(fileService);
            Writer out = new OutputStreamWriter(fos);
            serviceTemplate.process(paramsMap, out);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Template getTemplate(String templateName) {
        try {
            //配置类
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);

            File dir = new File(projectRoot + "src/test/java/com/zhd/ultimate/sociology/codeGenerator");
            System.out.println(dir);
            cfg.setDirectoryForTemplateLoading(dir);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            return cfg.getTemplate(templateName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取表字段
    public static List<MyTableColumn> getColumnByTableName(String tableName) {
        RowSet rowSet = executeQuery(sqlTableColumn, tableName);
        List<MyTableColumn> tableColumns = new ArrayList<>();
        if (rowSet == null) {
            return tableColumns;
        }
        try {
            while (rowSet.next()) {
                String columnName = rowSet.getString("COLUMN_NAME");
                String dataType = rowSet.getString("DATA_TYPE");
                String comment = rowSet.getString("COLUMN_COMMENT");
                String columnKey = rowSet.getString("COLUMN_KEY");
                MyTableColumn column = new MyTableColumn();
                column.setColumnName(columnName);
                String entityName = getEntityName(columnName);
                column.setEntityName(entityName);
                String entityNameUp = getFirstUp(entityName);
                column.setFirstUpEntityName(entityNameUp);
                column.setDataType(dataType);
                column.setComment(comment);
                if (StringUtils.equals("PRI", columnKey)) {
                    column.setPriKey(true);
                }
                tableColumns.add(column);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableColumns;
    }

    private static String getFirstUp(String name) {
        return name.substring(0, 1).toUpperCase().concat(name.substring(1));
    }

    private static String getEntityName(String columnName) {
        if (StringUtils.isBlank(columnName)) {
            return "";
        }
        char[] chars = columnName.toCharArray();
        StringBuilder builder = new StringBuilder();
        boolean isUp = false;
        for (int i = 0; i < chars.length; i++) {
            String str = String.valueOf(chars[i]);
            if (StringUtils.equals(str, "_")) {
                isUp = true;
                continue;
            }
            if (isUp) {
                builder.append(str.toUpperCase());
                isUp = false;
            } else {
                builder.append(str);
            }
        }
        return builder.toString();

    }

    private static RowSet executeQuery(String sql, String... params) {
        //获取连接
        Connection con = null;
        //创建statement对象
        PreparedStatement statement = null;
        ResultSet rs = null;
        CachedRowSet rowSet = null;
        try {
            con = DriverManager.getConnection(url, root, password);
            statement = con.prepareStatement(sql);
            if (params != null) {
                for (int i = 1; i <= params.length; i++) {
                    statement.setString(i, params[i - 1]);
                }
            }
            //发送并执行sql
            rs = statement.executeQuery();
            RowSetFactory rowSetFactory = RowSetProvider.newFactory();
            //创建指定的RowSet
            rowSet = rowSetFactory.createCachedRowSet();
            //将ResultSet放到RowSet中
            rowSet.populate(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 6 释放资源
            close(rs);
            close(statement);
            close(con);
        }
        return rowSet;
    }

    private static void close(AutoCloseable closeable) {
        try {
            closeable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Data
    public static class MyTableColumn {
        private String columnName;
        private String entityName;
        private String firstUpEntityName;
        private String dataType;
        private String comment;
        private boolean priKey;

    }

}
