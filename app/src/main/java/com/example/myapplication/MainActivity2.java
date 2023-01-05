package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// https://www.cnblogs.com/panqiaoyan/p/12869971.html
public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private TextView tvUName;
    private TextView tvUId;
    String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=JDBCDemo";
    String dbURL="jdbc:jtds:sqlserver://192.168.0.104:1433;DatabaseName=testApp";//数据库连接url
    String userName="sa";//数据库用户名
    String userPwd="cheyi";//数据库密码
    Connection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();//初始化组件
        btn.setOnClickListener(this);
    }

    private void init() {
        btn = (Button)findViewById(R.id.button);
        tvUName = (TextView)findViewById(R.id.userName);
        tvUId = (TextView)findViewById(R.id.userid);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Class.forName(driverName); // jdk版本6.0以上可以省略这句话
                            con= DriverManager.getConnection(dbURL,userName,userPwd);
                            String sql="select * from [user]"; // 直接写select * from user有误，得在表名处加中括号;
                            Statement st=con.createStatement();
                            ResultSet rs=st.executeQuery(sql);
                            while(rs.next()){
                                tvUName.setText(rs.getString("name"));
                                tvUId.setText(String.valueOf(rs.getInt("userID")));
                                Log.i("MainActivity",rs.getString("name"));
                                Log.i("MainActivity",String.valueOf(rs.getInt("userID")));
                            }
                            rs.close();
                            st.close();
                            con.close();
                            System.out.println("连接数据库成功");
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
        }
    }
}