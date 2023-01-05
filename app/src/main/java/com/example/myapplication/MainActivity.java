package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private TextView tvUName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvUName = findViewById(R.id.userName);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(getClickEvent());
    }

//    public void showFirst(View v){
//        String result = DBUtil.search();
//        Toast.makeText(this,""+result,Toast.LENGTH_SHORT).show();
//    }

    private View.OnClickListener getClickEvent(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvUName.setText("name");
                if(v==btn){
                    test();
                }
            }
        };
    }

    private void test(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                String res = DBUtil2.QuerySQL();
                Message msg = new Message();
                msg.what = 1001;
                Bundle data = new Bundle();
                data.putString("result", res);
                msg.setData(data);
                mHandler.sendMessage(msg);
            }
        };
        new Thread(r).start();
    }

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler(){
        @SuppressLint("HandlerLeak")
        public void handleMessage(android.os.Message msg){
            switch (msg.what){
                case 1001:
                    String str = msg.getData().getString("result");
                    tvUName.setText(str);
                    break;

                default:
                    break;
            }
        }
    };


//    public void showFirst(View view) {
//        Runnable run = new Runnable() {
//            @Override
//            public void run() {
//                String result = DBUtil.search();
//                DBUtil.closeConnect();
//                System.out.println(result);
//            }
//        };
//        new Thread(run).start();
//    }
}