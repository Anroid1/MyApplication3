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
                String phone=editText1.getText().toString().trim();
                String password=editText2.getText().toString().trim();
                if ((phone.length()==0) ||(password.length() ==0)){
                    Toast.makeText(ZhuceActivity.this,"手机号或密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    User user = new User();
                    user.setPhone(phone);
                    user.setPassword(password);
                    //将数据存入SQLite数据表
                    UserAdd userAdd = new UserAdd(ZhuceActivity.this);
                    userAdd.addUser(user);
                    Toast.makeText(ZhuceActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ZhuceActivity.this, DengluActivity.class);
                    intent.putExtra("phone",phone);
                    intent.putExtra("password",password);
                    startActivity(intent);
                }
            }
        });
    }
}
