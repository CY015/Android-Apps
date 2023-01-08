package com.example.myapplication;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil2 {

    private static Connection conn = null;

        private static Connection getSQLConnection(String ip, String user, String pwd, String db)
        {
            Connection con = null;
            try
            {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
//                con = DriverManager.getConnection("jdbc:jtds:sqlserver://" + ip + ":1433;DatabaseName=" + db + ";charset=utf8", user, pwd);
                con = DriverManager.getConnection("jdbc:jtds:sqlserver://" + ip + ":1433;DatabaseName=" + db, user, pwd);
            } catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
            return con;
        }

        public static String QuerySQL()
        {
            String result = "";
            try
            {
                if(conn == null){
                    conn = getSQLConnection("192.168.0.101", "sa", "cheyi", "testApp");
                }

                String sql = "SELECT * From [user]";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next())
                {
                    int id = rs.getInt("userID");
                    String s2 = rs.getString("name");
                    result += String.valueOf(id) + "  -  " + s2 + "\n";
                    System.out.println(String.valueOf(id) + "  -  " + s2);
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
                result += "查询数据异常!" + e.getMessage();
            }
            return result;
        }

    /**
     * 向服务器数据库插入数据
     */
    public static int insertIntoData(int uID, String uName) {
        int result = 0;
        try {
            if (conn == null) {
                conn = getSQLConnection("192.168.0.101", "sa", "cheyi", "testApp");
            }
            Statement stmt = conn.createStatement();

            if (conn == null || stmt == null) {
                return result ;
            }
            //插入sql语句（[user] 为表名，userID, name为要插入的字段名）
            String sql = "INSERT INTO [user](userID, name) VALUES ('"+ uID +"', '"+ uName +"')";
            result = stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result ;
    }

    /**
     * 数据更新方法
     */
    public static int update(int uID, String uName) {
        int result = 0;
        try {
            if (conn == null) {
                conn = getSQLConnection("192.168.0.101", "sa", "cheyi", "testApp");
            }
            Statement stmt = conn.createStatement();
            if (conn == null || stmt == null) {
                return result;
            }
            // 数据更新sql语句
            String sqlStr = "update [user] set name = '"+ uName +"' where uID = '"+ uID +"'";
            // 数据删除sql语句
            // String sqlStr = "delete from Tb_Id where id = '0001'";

            result = stmt.executeUpdate(sqlStr);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return result;
    }

    /**
     * 数据删除方法
     */
    public static int delete(int uID, String uName) {
        int result = 0;
        try {
            if (conn == null) {
                conn = getSQLConnection("192.168.0.101", "sa", "cheyi", "testApp");
            }
            Statement stmt = conn.createStatement();
            if (conn == null || stmt == null) {
                return result;
            }
            // 数据更新sql语句
            // String sqlStr = "update Tb_Id set id = '110' where id = '0001'";
            // 数据删除sql语句
            String sqlStr = "DELETE FROM [user] WHERE userID = '"+ uID +"'";

            result = stmt.executeUpdate(sqlStr);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();

        }
        return result;
    }

}
