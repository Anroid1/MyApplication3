package com.example.myapplication.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;

public class ZhuceActivity extends AppCompatActivity {
    GbActivity recevier;
    EditText sjh,editText2,editText3;
    ImageView yanzhengma,imgzhuce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);

        //初始化控件
        sjh =findViewById(R.id.sjh);
        editText2 =findViewById(R.id.mm);
        editText3 =findViewById(R.id.shuruyanzhengma);
        imgzhuce =findViewById(R.id.bu_zhuce);
        yanzhengma=findViewById(R.id.yanzhengma);
        imgzhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textip=sjh.getText().toString().trim();
                String t=editText2.getText().toString().trim();
                if(textip.length()==0 || t.length()==0)                              //判断输入框是否为空
                {
                    Toast.makeText(ZhuceActivity.this, "手机号码或密码不为空", Toast.LENGTH_LONG).show();
                }else{
                    //回传的数据
                    String phone=sjh.getText().toString();
                    Intent intent=new Intent();
                    intent.putExtra("123",phone);
                    ZhuceActivity.this.setResult(3,intent);//
                    ZhuceActivity.this.finish();
                }
            }
        });


       /*imgzhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textip=sjh.getText().toString().trim();
                String t=editText2.getText().toString().trim();
                if(textip.length()==0 || t.length()==0)                              //判断输入框是否为空
                {
                    Toast.makeText(ZhuceActivity.this, "手机号码或密码不为空", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"欢迎进入",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(ZhuceActivity.this,DengluActivity.class));
                }

            }
        });*/
        yanzhengma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recevier=new GbActivity();
                IntentFilter intentFilter=new IntentFilter();
                intentFilter.addAction("help");
                //注册广播
                registerReceiver(recevier,intentFilter);
                Intent intent=new Intent();
                intent.setAction("help");
                intent.putExtra("message","YTSH");
                sendBroadcast(intent);
            }
        });
    }

}