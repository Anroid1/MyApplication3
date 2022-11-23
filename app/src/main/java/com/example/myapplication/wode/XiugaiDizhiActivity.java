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
import com.example.myapplication.entity.Dizhi;
import com.example.myapplication.util.DizhiDbService;

public class XiugaiDizhiActivity extends AppCompatActivity {
    ImageView fanhui;
    EditText xingming,phone,diqu,xdizhi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiugai_dizhi);
        xingming=findViewById(R.id.xingming);
        phone=findViewById(R.id.phone);
        diqu=findViewById(R.id.diqu);
        xdizhi=findViewById(R.id.xdizhi);
        fanhui=findViewById(R.id.fanhui);
        Button xiugai=findViewById(R.id.xiugai);
        Intent intent = getIntent();
        int id1=intent.getIntExtra("id",0);
             xingming.setText(intent.getStringExtra("name"));
           phone.setText(intent.getStringExtra("phone"));
               diqu.setText(intent.getStringExtra("diqu"));
                xdizhi.setText(intent.getStringExtra("xdizhi"));

        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        xiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dizhi dizhi=new Dizhi();
                dizhi.setId(id1);
                dizhi.setName(xingming.getText().toString());
                dizhi.setPhone(phone.getText().toString());
                dizhi.setDiqu(diqu.getText().toString());
                dizhi.setXdizhi(xdizhi.getText().toString());
                //将数据存入SQLite数据表
                DizhiDbService dizhiDbService=new DizhiDbService(XiugaiDizhiActivity.this);
                dizhiDbService.update(dizhi);
                Toast.makeText(XiugaiDizhiActivity.this,"地址修改成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(XiugaiDizhiActivity.this,DizhiActivity.class);
                startActivity(intent);
            }
        });
    }
}