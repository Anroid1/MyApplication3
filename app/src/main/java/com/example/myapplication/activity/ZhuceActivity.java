package com.example.myapplication.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;

public class ZhuceActivity extends AppCompatActivity {
    EditText editText1,editText2,editText3;
    ImageView imgzhuce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);

        //初始化控件
        editText1 =findViewById(R.id.sjh);
        editText2 =findViewById(R.id.mm);
        editText3 =findViewById(R.id.shuruyanzhengma);
        imgzhuce =findViewById(R.id.bu_zhuce);

        imgzhuce.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //获取文本框中的数据
                String s = editText1.getText().toString();
                //弹出一个消息框
                Toast.makeText(getApplicationContext(), s + "请微信授权", Toast.LENGTH_SHORT).show();
            }
        });


        imgzhuce.setOnClickListener(new View.OnClickListener() {
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
                Intent intent1 = new Intent(ZhuceActivity.this, WeixindengluActivity.class);
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