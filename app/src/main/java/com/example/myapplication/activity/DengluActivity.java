package com.example.myapplication.activity;

import static java.security.AccessController.getContext;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CartAdapter;
import com.example.myapplication.entity.Goods;
import com.example.myapplication.entity.User;
import com.example.myapplication.util.CartDBService;
import com.example.myapplication.util.DBOpenHelper;
import com.example.myapplication.util.UserAdd;

import java.util.ArrayList;
import java.util.List;

public class DengluActivity extends AppCompatActivity {
    DBOpenHelper dbOpenHelper;
    TextView zhuce;
    EditText editText1,editText2;
    ImageView imgdenglu,imgweixin,imgqq;
    private String phone,password;
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

//                SQLiteDatabase sqLiteDatabase=dbOpenHelper.getReadableDatabase();
//                Cursor cursor=sqLiteDatabase.query("user_db",new String[]{"phone","password"},"phone=?",new String[]{phone},null,null,null,null);

                //获取文本框中的数据

                String phone=editText1.getText().toString().trim();
                String password=editText2.getText().toString().trim();
                if ((phone.length()==0) ||(password.length() ==0)){
                    Toast.makeText(DengluActivity.this,"手机号或密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                UserAdd userAdd=new UserAdd(DengluActivity.this);
                List<User> userList=userAdd.getUser(phone);
                if (userList.get(0)!=null){
                    String dbpassword=userList.get(0).getPassword();
                    String dbphone=userList.get(0).getPhone();
                    if (password.equals(dbpassword)){
                        Intent intent=new Intent(DengluActivity.this,MainActivity.class);
                        startActivity(intent);
                        return;
                    }else if (phone!=dbphone){
                        Toast.makeText(DengluActivity.this,"用户不存在",Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        Toast.makeText(DengluActivity.this,"密码不正确",Toast.LENGTH_SHORT).show();
                        return;
                    }


            }
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
    }

}