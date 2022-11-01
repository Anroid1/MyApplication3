package com.example.myapplication.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.fragment.ShouyeFragment;

public class DengluActivity extends AppCompatActivity {

    TextView zhuce;
    EditText editText1,editText2;
    ImageView imgdenglu,imgweixin,imgqq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu);

        //初始化控件
        zhuce = findViewById(R.id.bu_zhuce);
        editText1 = findViewById(R.id.sjh);
        editText2 = findViewById(R.id.mm);
        imgdenglu = findViewById(R.id.denglu);
        imgweixin = findViewById(R.id.tu1);
        imgqq = findViewById(R.id.tu2);

        //跳转
        imgdenglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textip=editText1.getText().toString().trim();
                String t=editText2.getText().toString().trim();
                if(textip.length()==0 || t.length()==0)                              //判断输入框是否为空
                {
                    Toast.makeText(DengluActivity.this, "手机号码或密码不为空", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"欢迎进入",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(DengluActivity.this, MainActivity.class));
                }

            }
        });


        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(DengluActivity.this, ZhuceActivity.class);
                startActivityForResult(intent1, 1);
            }
        });

        imgweixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DengluActivity.this, WeixindengluActivity.class);
               startActivity(intent1);
            }
        });
    }
    @Override
    protected void onActivityResult (int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode,requestCode,data);
        if(requestCode==1&&resultCode==3){
            String s=data.getStringExtra("123");
            editText1.setText(s);

        }
    }

    }


