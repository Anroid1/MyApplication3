package com.example.myapplication.wode;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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
import com.example.myapplication.util.DBOpenHelper;
import com.example.myapplication.util.DizhiDbService;
import com.example.myapplication.util.UserAdd;

import java.util.List;

public class GerenxinxiActivity extends AppCompatActivity {
    EditText phone1,nicheng,sex,mim;
    ImageView fanhui,touxiang;
    Button xuigaib;
    public static int RESULT_CODE=1;
    private DBOpenHelper dbOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenxinxi);
        phone1=findViewById(R.id.phone);
        fanhui=findViewById(R.id.fanhui);
        nicheng=findViewById(R.id.name);
        touxiang=findViewById(R.id.touxiang);
        xuigaib=findViewById(R.id.xuigaib);
        mim=findViewById(R.id.mim);
        SharedPreferences sharedPreferences=getSharedPreferences("editText1", Context.MODE_PRIVATE);
        String getStringGenDer=sharedPreferences.getString("name",null);
        phone1.setText(getStringGenDer);
        //获取文本框中的数据
        String phone=phone1.getText().toString().trim();

        //
        UserAdd userAdd=new UserAdd(GerenxinxiActivity.this);
        List<User> userList=userAdd.getUser(phone);
        if (userList.get(0)!=null){
            String dbnicheng=userList.get(0).getName();
            String t1=userList.get(0).getSex();
            String pho=userList.get(0).getPhone();
            String pas=userList.get(0).getPassword();
            String dbsex=userList.get(0).getSex();
            Log.v("AAAAAAAAAAA","nic="+dbnicheng);
            Log.v("AAAAAAAAAAA","nicheng="+t1);
            Log.v("AAAAAAAAAAA","phone="+pho);
            Log.v("AAAAAAAAAAA","pas="+pas);
            Log.v("AAAAAAAAAAA","sex="+dbsex);
            mim.setText(dbsex);
            nicheng.setText(pho);
        }


        //
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GerenxinxiActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        xuigaib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=new User();
                user.setName(nicheng.getText().toString());
                user.setPhone(phone1.getText().toString());
                user.setPassword(mim.getText().toString());
                //将数据存入SQLite数据表
                UserAdd userAdd=new UserAdd(GerenxinxiActivity.this);
                userAdd.update1(user);
                Toast.makeText(GerenxinxiActivity.this,"个人信息修改成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(GerenxinxiActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });

    }
}