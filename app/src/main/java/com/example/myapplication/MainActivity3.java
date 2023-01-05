package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener{

    private Button search;
    private TextView tv1;
    private TextView tv2;

    String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=JDBCDemo";
    String dbURL="jdbc:jtds:sqlserver://192.168.0.104:1433;DatabaseName=testApp";// 数据库连接url
    String userName="sa";// 数据库用户名
    String userPwd="cheyi";// 数据库密码
    Connection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        init();

        search.setOnClickListener(this);
    }

    private void init(){
        search = findViewById(R.id.search);
        tv1 = findViewById(R.id.textView);
        tv2 = findViewById(R.id.textView2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search:
                test();
                break;
        }
    }

    private void test(){
        Runnable r3 = new Runnable() {
            @Override
            public void run() {
                try{
                    Class.forName(driverName); // jdk版本6.0以上可以省略这句话
                    con = DriverManager.getConnection(dbURL,userName,userPwd);
                    String sql="select * from [user]"; // 直接写select * from user有误，得在表名处加中括号;
                    Statement st=con.createStatement();
                    ResultSet rs=st.executeQuery(sql);
                    while(rs.next()){
                        tv1.setText(rs.getString("name"));
                        tv2.setText(String.valueOf(rs.getInt("userID")));
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
        };
        new Thread(r3).start();
    }
}