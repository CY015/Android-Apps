package com.example.myapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import android.util.Log;
import java.sql.ResultSet;
import java.sql.SQLException;

// 读取SQL Server
// https://blog.csdn.net/lpCrazyBoy/article/details/85229075
// https://blog.csdn.net/qq_40399080/article/details/97630894

// 手机连接android studio直接测试
// https://blog.csdn.net/hasfhaiogheiohf/article/details/104858071
// https://blog.csdn.net/qq_46941656/article/details/119935227
public class DBUtil {
    // DESKTOP-E2O0ACA
    private static String user = "sa";//数据库登录账号
    private static String password = "cheyi";//登录密码
    private static String DatabaseName = "testApp";//数据库名称
    private static String IP = "192.168.0.104";//数据库IP（也可写本机ip）


    /**
     * 连接字符串
     */
    private static String connectDB = "jdbc:jtds:sqlserver://" + IP + ":1433/" + DatabaseName + ";useunicode=true;characterEncoding=UTF-8";

    private static Connection conn = null;
    private static Statement stmt = null;

    /**
     * 连接数据库
     *
     * @return
     */
    private static Connection getSQLConnection() {
        Connection con = null;
        try {
            //加载驱动
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //连接数据库对象
            con = DriverManager.getConnection(connectDB, user,
                    password);
        } catch (Exception e) {
            System.out.println("no connection");
        }
        return con;
    }

    /**
     * 向服务器数据库插入数据
     */
    public static int insertIntoData(String values) {
        int result = 0;
        try {
            if (conn == null) {
                conn = getSQLConnection();
                stmt = conn.createStatement();
            }
            if (conn == null || stmt == null) {
                return result ;
            }
            //插入sql语句（Tb_Id 为表名，id为要插入的字段名）
            String sql = "INSERT INTO Tb_Id (id) VALUES ('"+values +"')";
            result = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result ;
    }


    /**
     * 数据查询方法
     */
    public static String search() {
        String result = "";
        try {
            if (conn == null) {
                conn = getSQLConnection();
                stmt = conn.createStatement();
            }
            if (conn == null || stmt == null) {
                return result += "No Connect";
            }
            String sqlStr = "select userID,name from user";
            // String sqlStr = "select id,userName from Tb_Id";
            ResultSet rs = stmt.executeQuery(sqlStr);

            while (rs.next()) {
                //将查出的内容读取出来，存入字符串中
                String idStr = rs.getString("userID");
                String nameStr = rs.getString("name");
                result += "\n"+ idStr +"----"+nameStr ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result += "No Data" + e.getMessage();

        }
        return result;
    }


    /**
     * 数据更新和删除方法
     */
    public static int update() {
        int result = 0;
        try {
            if (conn == null) {
                conn = getSQLConnection();
                stmt = conn.createStatement();
            }
            if (conn == null || stmt == null) {
                return result;
            }
            //数据更新sql语句
            //String sqlStr = "update Tb_Id set id = '110' where id = '0001'";
            //数据删除sql语句
            String sqlStr = "delete from Tb_Id where id = '0001'";

            result = stmt.executeUpdate(sqlStr);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return result;
    }



    /**
     * 关闭数据库链接
     */
    public static void closeConnect() {
        if (stmt != null) {
            try {
                stmt.close();
                stmt = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
