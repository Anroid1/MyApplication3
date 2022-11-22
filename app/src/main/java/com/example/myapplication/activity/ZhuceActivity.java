package com.example.myapplication.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.entity.User;
import com.example.myapplication.util.CartDBService;
import com.example.myapplication.util.DBOpenHelper;
import com.example.myapplication.util.UserAdd;

public class ZhuceActivity extends AppCompatActivity {
    EditText editText1,editText2;
    ImageView imgzhuce;
    private String  phone;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);

        //初始化控件
        editText1 =findViewById(R.id.sjh);
        editText2 =findViewById(R.id.mm);
        imgzhuce =findViewById(R.id.bu_zhuce);

        imgzhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user=new User();
                user.setPhone(editText1.getText().toString().trim());
                user.setPassword(editText2.getText().toString().trim());
                if(!TextUtils.isEmpty(phone)&&!TextUtils.isEmpty(password)){
                    Toast.makeText(ZhuceActivity.this,"信息不完备，注册失败",Toast.LENGTH_SHORT).show();
                }else {
                    //将数据存入SQLite数据表
                    UserAdd userAdd=new UserAdd(ZhuceActivity.this);
                    userAdd.addUser(user);
                    Toast.makeText(ZhuceActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(ZhuceActivity.this,DengluActivity.class);
                    startActivity(intent);
                    }
            }
        });
    }
}