package com.example.login.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 数据库的链接和关闭
 **/

public class DBUtil {
    private static final String CLS = "net.sourceforge.jtds.jdbc.Driver";
    private static final String URL ="jdbc:jtds:sqlserver://120.48.5.10:1433;DatabaseName=DB";
    private static final String USER ="sa";
    private static final String PWD ="Lnb123456";

    public static Connection conn;    //连接对象
    public static Statement stmt;    //命令集
    public static PreparedStatement pStmt;  //预编译命令
    public static ResultSet rs;    //结果集

    //连接数据库
    public static void getConnection(){
        try{
            Class.forName(CLS);
            conn = DriverManager.getConnection(URL, USER, PWD);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //关闭数据库
    public static void closeAll() {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
            if (pStmt != null) {
                pStmt.close();
                pStmt = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

