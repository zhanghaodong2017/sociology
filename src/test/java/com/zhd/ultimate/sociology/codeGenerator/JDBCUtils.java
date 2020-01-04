package com.zhd.ultimate.sociology.codeGenerator;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-04 14:38
 */
public class JDBCUtils {

    private static final String url = "jdbc:mysql://115.29.108.117:3306/zhd";
    private static final String root = "root";
    private static final String password = "123";


    public static RowSet executeQuery(String sql, String... params) {
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
}
