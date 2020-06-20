package com.mycode.edu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * 对接教务数据工具类
 */
@Component
public class EduDataSource {

    @Value("${edu.datasource.driver}")
    private String driver;
    @Value("${edu.datasource.url}")
    private String url;
    @Value("${edu.datasource.username}")
    private String username;
    @Value("${edu.datasource.password}")
    private String password;

    public String getEduDatasourceInfo(){
        return "edu.datasource：-h "+url+" -u "+username+" -p "+password;
    }

    public Connection getConnect() {
        Connection conn = null;
        try {
            Class.forName(this.driver);
            conn = DriverManager.getConnection(this.url, this.username, this.password);
        } catch (ClassNotFoundException e) {
            System.err.println("驱动加载失败！");
        } catch (SQLException e) {
            System.err.println("数据库连接失败！");
        }
        return conn;
    }

    public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

}
