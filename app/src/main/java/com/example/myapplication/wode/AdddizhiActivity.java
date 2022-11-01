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

public class AdddizhiActivity extends AppCompatActivity {
    final int CODE=0x11;
    EditText xingming,phone,diqu,xdizhi;
ImageView adI1;
    Button baocun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddizhi);

        xingming=findViewById(R.id.xingming);
        phone=findViewById(R.id.phone);
        diqu=findViewById(R.id.diqu);
        xdizhi=findViewById(R.id.xdizhi);
        adI1=findViewById(R.id.adI1);
        baocun=findViewById(R.id.baocun);

        adI1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //添加地址
        baocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Bundle bundle=new Bundle();
                bundle.putCharSequence("xingming", xingming.getText().toString());   //将两个编辑框的内容读取出来保存到bundle中
                bundle.putCharSequence("phone", phone.getText().toString());
                bundle.putCharSequence("dizhi",diqu.getText().toString());
                bundle.putCharSequence("xdizhi",xdizhi.getText().toString());*/
                Dizhi dizhi=new Dizhi();
                dizhi.setName(xingming.getText().toString());
                dizhi.setPhone(phone.getText().toString());
                dizhi.setDiqu(diqu.getText().toString());
                dizhi.setXdizhi(xdizhi.getText().toString());

                //将数据存入SQLite数据表
                DizhiDbService dizhiDbService=new DizhiDbService(AdddizhiActivity.this);
                dizhiDbService.addDizhi(dizhi);
                Toast.makeText(AdddizhiActivity.this,"地址添加成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AdddizhiActivity.this,DizhiActivity.class);
                startActivity(intent);
            }
        });
    }
}