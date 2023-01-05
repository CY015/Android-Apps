package com.example.myapplication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil2 {
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
                Connection conn = getSQLConnection("192.168.0.104", "sa", "cheyi", "testApp");
                String sql = "select * from [user]";
                Statement stmt = conn.createStatement();//
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

        public static void main(String[] args)
        {
            QuerySQL();
        }
}
