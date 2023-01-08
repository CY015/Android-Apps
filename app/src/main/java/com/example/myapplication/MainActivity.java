package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn;
    private TextView tvUName;
    private Button btn2;
    private EditText etUID;
    private EditText etUName;
    private Button btn3;
    private EditText etDelUID;
    private EditText etDelUName;
    private Button btn4;
    private EditText etUpdUID;
    private EditText etUpdUName;

    private Button btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    private void init(){
        tvUName = findViewById(R.id.userName);
        btn = findViewById(R.id.button_search);

        etUID = findViewById(R.id.etID);
        etUName = findViewById(R.id.etName);
        btn2 = findViewById(R.id.button_insert);

        etDelUID = findViewById(R.id.etDelUID);
        etDelUName = findViewById(R.id.etDelUName);
        btn3 = findViewById(R.id.button_delete);

        etUpdUID = findViewById(R.id.etUpdUID);
        etUpdUName = findViewById(R.id.etUpdUName);
        btn4 = findViewById(R.id.button_update);

        btn5 = findViewById(R.id.button_test);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_test:
                Toast.makeText(MainActivity.this, "test.", Toast.LENGTH_LONG).show();
                break;

            case R.id.button_search:
                showInfo();
                break;

            case R.id.button_insert:
                try{
                    Integer inUID = Integer.parseInt(etUID.getText().toString());
                    String inUName = etUName.getText().toString();

                    String inUID_Pattern = "^d{1,}$";
                    Pattern p = Pattern.compile(inUID_Pattern);
                    Matcher m = p.matcher(inUID+"");

                    insertInfo(inUID, inUName);
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Please check the id which must be a num.",Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.button_delete:
//                try{
                    Integer delUID = Integer.parseInt(etDelUID.getText().toString());
                    String delUName = etDelUName.getText().toString();
//
//                    String delUID_Pattern = "^d{1,}$";
//                    Pattern p = Pattern.compile(delUID_Pattern);
//                    Matcher m = p.matcher(delUID+"");

                    deleteInfo(delUID, delUName);
//                }
//                catch (Exception e){
//                    Toast.makeText(MainActivity.this, "Please check the id which must be a num.",Toast.LENGTH_LONG).show();
//                }

            case R.id.button_update:
                try{
                    Integer updUID = Integer.parseInt(etUpdUID.getText().toString());
                    String updUName = etUpdUName.getText().toString();

                    String updUID_Pattern = "^d{1,}$";
                    Pattern p = Pattern.compile(updUID_Pattern);
                    Matcher m = p.matcher(updUID+"");

                    updateInfo(updUID, updUName);
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Please check the id which must be a num.",Toast.LENGTH_LONG).show();
                }

        }
    }

    // TODO 只能使用一次对数据库的操作，用过一次之后使用别的功能会失败
    // 查询结果
    private void showInfo(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                String res = DBUtil2.QuerySQL();
                tvUName.setText(res);
            }
        };
        new Thread(r).start();
    }

    // 插入数据
    private void insertInfo(int uid, String uName){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                Integer i = DBUtil2.insertIntoData(uid, uName);
                if(i == 0) {
                    // 不能直接在子线程中弹出Toast，得调用Looper.prepare()
                    Looper.prepare();
                    Toast.makeText(MainActivity.this, "Fail to insert. Please check the id/name whether is too long.", Toast.LENGTH_LONG).show();
                    Looper.loop();
                }
                else{
                    Looper.prepare();
                    Toast.makeText(MainActivity.this, "Succeed.",Toast.LENGTH_LONG).show();
                    Looper.loop();
                }
            }
        };
        new Thread(r1).start();
    }

    // 删除数据
    private void  deleteInfo(int uid, String uName){
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                Integer i = 0;
                try{
                    i = DBUtil2.delete(uid, uName);
                } catch (Exception e){
                    Looper.prepare();
                    Toast.makeText(MainActivity.this, "SQL的问题",Toast.LENGTH_LONG).show();
                    Looper.loop();
                }

                if(i == 0) {
                    // 不能直接在子线程中弹出Toast，得调用Looper.prepare()
                    Looper.prepare();
                    Toast.makeText(MainActivity.this, "Fail to delete. Please check the id/name whether is too long.", Toast.LENGTH_LONG).show();
                    Looper.loop();
                }
                else{
                    Looper.prepare();
                    Toast.makeText(MainActivity.this, "Succeed.",Toast.LENGTH_LONG).show();
                    Looper.loop();
                }
            }
        };
        new Thread(r2).start();
    }

    // 更新数据
    private void  updateInfo(int uid, String uName){
        Runnable r3 = new Runnable() {
            @Override
            public void run() {
                Integer i = DBUtil2.update(uid, uName);
                if(i == 0) {
                    // 不能直接在子线程中弹出Toast，得调用Looper.prepare()
                    Looper.prepare();
                    Toast.makeText(MainActivity.this, "Fail to update. Please check the id/name whether is too long.", Toast.LENGTH_LONG).show();
                    Looper.loop();
                }
                else{
                    Looper.prepare();
                    Toast.makeText(MainActivity.this, "Succeed.",Toast.LENGTH_LONG).show();
                    Looper.loop();
                }
            }
        };
        new Thread(r3).start();
    }

}