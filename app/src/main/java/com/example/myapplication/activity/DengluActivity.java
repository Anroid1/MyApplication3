package com.example.myapplication.activity;

import static com.example.myapplication.util.UserAdd.TABLE_NAME;
import static java.security.AccessController.getContext;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import com.example.myapplication.entity.User;
import com.example.myapplication.util.CartDBService;
import com.example.myapplication.util.DBOpenHelper;
import com.example.myapplication.util.UserAdd;
import com.example.myapplication.wode.DizhiActivity;
import com.example.myapplication.wode.GerenxinxiActivity;
import com.example.myapplication.wode.XiugaiDizhiActivity;
import com.example.myapplication.wode.XiugaimimaActivity;

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
        SharedPreferences sharedPreferences=getSharedPreferences("editText1",MODE_PRIVATE);
        editText1.setText(sharedPreferences.getString("editText1",""));
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
                if (!(phone.length()==0) || !(password.length() ==0)) {
                    UserAdd userAdd = new UserAdd(DengluActivity.this);
                    boolean flag = userAdd.login(phone,password);//账号和密码正确的话，flag为true
                    if(flag){
                        SharedPreferences.Editor editor=getSharedPreferences("editText1",MODE_PRIVATE).edit();
                        SharedPreferences.Editor user_editor=sharedPreferences.edit();
                        user_editor.putString("name",editText1.getText().toString());
                        //提交
                        user_editor.commit();
                        Toast.makeText(DengluActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DengluActivity.this,MainActivity.class));
                        editor.apply();

                    }else{
                        Log.v("AAAAAAAAAAA","phone="+phone);
                        Log.v("AAAAAAAAAAA","pass="+password);
                        Toast.makeText(DengluActivity.this, "密码或者账号错误，请重新登录", Toast.LENGTH_LONG).show();
                    }
                }

                /*Cursor cursor=dbOpenHelper.getWritableDatabase().rawQuery("select * from user_db where phone='"+editText1.getText().toString()+"'",null);
                if (cursor.moveToNext()){
                    if (cursor.getString(1).equals(editText2.getText().toString())){
                        SharedPreferences.Editor editor=getSharedPreferences("editText1",MODE_PRIVATE).edit();
                        SharedPreferences.Editor user_editor=sharedPreferences.edit();
                        user_editor.putString("name",editText1.getText().toString());
                        //提交
                        user_editor.commit();
                        Toast.makeText(DengluActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DengluActivity.this,MainActivity.class));
                        editor.apply();
                    }else {
                        Toast.makeText(DengluActivity.this,"登录不成功",Toast.LENGTH_SHORT).show();

                    }
                }*/
                /*UserAdd userAdd=new UserAdd(DengluActivity.this);
                List<User> userList=userAdd.getUser(phone);
                if (userList.get(0)!=null){
                    String dbpassword=userList.get(0).getPassword();
                    String dbphone=userList.get(0).getPhone();
                    if (password.equals(dbpassword)){
                        //
                        SharedPreferences.Editor editor=getSharedPreferences("editText1",MODE_PRIVATE).edit();
                        SharedPreferences.Editor user_editor=sharedPreferences.edit();
                        user_editor.putString("name",editText1.getText().toString());
                        //提交
                        user_editor.commit();
                        Toast.makeText(DengluActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DengluActivity.this,MainActivity.class));
                        editor.apply();
                        return;
                    }else if (phone!=dbphone){
                        Toast.makeText(DengluActivity.this,"用户不存在",Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        Toast.makeText(DengluActivity.this,"密码不正确",Toast.LENGTH_SHORT).show();
                        return;
                    }

            }*/
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