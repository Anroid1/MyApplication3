package com.example.myapplication.wode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activity.DengluActivity;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.entity.Dizhi;
import com.example.myapplication.entity.User;
import com.example.myapplication.util.DizhiDbService;
import com.example.myapplication.util.UserAdd;

import java.util.List;

public class XiugaimimaActivity extends AppCompatActivity {
    EditText editText1,xmima,querenmm;
    Button xuigai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiugaimima);
        ImageView fanhui=findViewById(R.id.fanhui);
        editText1=findViewById(R.id.phone);
        xmima=findViewById(R.id.xmima);
        querenmm=findViewById(R.id.querenmm);
        xuigai=findViewById(R.id.xuigai);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        xuigai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取文本框中的数据
                String phone=editText1.getText().toString().trim();
                String password=xmima.getText().toString().trim();
                String xpassword=querenmm.getText().toString().trim();
                if ((phone.length()==0) ||(password.length() ==0)){
                    Toast.makeText(XiugaimimaActivity.this,"手机号或密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }else if((xpassword.length() ==0)){
                    Toast.makeText(XiugaimimaActivity.this,"新密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!(phone.length()==0) || !(password.length() ==0)) {
                    UserAdd userAdd = new UserAdd(XiugaimimaActivity.this);
                    boolean flag = userAdd.login(phone, password);//账号和密码正确的话，flag为true
                    if (flag) {
                        User user = new User();
                        user.setPhone(editText1.getText().toString());
                        user.setPassword(querenmm.getText().toString());
                        //将数据存入SQLite数据表
                        UserAdd userAdd1 = new UserAdd(XiugaimimaActivity.this);
                        userAdd1.updatemima(user);
                        Toast.makeText(XiugaimimaActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(XiugaimimaActivity.this, DengluActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(XiugaimimaActivity.this, "密码修改不成功", Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(XiugaimimaActivity.this, "密码修改不成功", Toast.LENGTH_SHORT).show();

                }
                /*UserAdd userAdd=new UserAdd(XiugaimimaActivity.this);
                List<User> userList=userAdd.getUser(phone1);
                if (userList.get(0)!=null) {
                    String dbpassword = userList.get(0).getPassword();
                    String dbphone = userList.get(0).getPhone();
                    if (password1.equals(dbpassword)) {
                        User user=new User();
                        user.setPhone(phone.getText().toString());
                        user.setPassword(querenmm.getText().toString());
                        //将数据存入SQLite数据表
                        UserAdd userAdd1=new UserAdd(XiugaimimaActivity.this);
                        userAdd1.updatemima(user);
                        Toast.makeText(XiugaimimaActivity.this,"密码修改成功",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(XiugaimimaActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(XiugaimimaActivity.this,"密码修改失败",Toast.LENGTH_SHORT).show();
                    }
                }*/
            }
        });

    }
}