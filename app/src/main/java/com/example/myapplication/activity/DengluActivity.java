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
        zhuce =findViewById(R.id.bu_zhuce);
        editText1 =findViewById(R.id.sjh);
        editText2 =findViewById(R.id.mm);
        imgdenglu =findViewById(R.id.denglu);
        imgweixin =findViewById(R.id.tu1);
        imgqq =findViewById(R.id.tu2);

        //登录后弹出欢迎
        imgdenglu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //获取文本框中的数据
                String s = editText1.getText().toString();
                //弹出一个消息框
                Toast.makeText(getApplicationContext(), s + "欢迎您!", Toast.LENGTH_SHORT).show();
            }
        });

        //注册文字跳转到注册页面
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(DengluActivity.this,ZhuceActivity.class);
                startActivityForResult(intent1,1);
            }
        });
        imgweixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(DengluActivity.this,WeixindengluActivity.class);
                startActivityForResult(intent1,1);
            }
        });
        //密码登录时判断输入框是否为空，不为空跳转到主页面
        imgdenglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int len = editText1.length();
                if(editText1.length() == 0){
                    Toast.makeText(getApplicationContext(), "手机号为空", Toast.LENGTH_SHORT).show();

                }
                int len1 = editText2.length();
                if(editText2.length() == 0){
                    Toast.makeText(getApplicationContext(), "密码为空", Toast.LENGTH_SHORT).show();
                }
                Intent intent1 = new Intent(DengluActivity.this, ShouyeFragment.class);
                startActivityForResult(intent1, 1);
            }
        });
    }

    @Override
    public void onActivityResult ( int requestCode, int resultCode,@Nullable Intent data){
        super.onActivityResult(requestCode,resultCode, data);
        if(requestCode==1&&resultCode==3) {
            String s = data.getStringExtra("phone");
            String s1 = data.getStringExtra("password");
            editText1.setText(s);
            editText2.setText(s1);
        }
    }
}