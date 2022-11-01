package com.example.myapplication.wode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.DizhiAdapter;
import com.example.myapplication.entity.Dizhi;
import com.example.myapplication.util.DizhiDbService;

import java.util.List;

public class DizhiActivity extends AppCompatActivity {
    Button dzb;
    ListView list1;
    ImageView dzI,xiugaidz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dizhi);
        dzb=findViewById(R.id.dzb);
        list1=findViewById(R.id.list1);
        dzI=findViewById(R.id.dzI);
        xiugaidz=findViewById(R.id.xiugaidz);
        //查询数据库
        DizhiDbService dizhiDbService=new DizhiDbService(this);
        List<Dizhi> items=dizhiDbService.getAllDizhi();
        //实例化适配器
        DizhiAdapter dizhiAdapter=new DizhiAdapter(this,items);
        list1.setAdapter(dizhiAdapter);
        dzb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DizhiActivity.this,AdddizhiActivity.class);
                startActivity(intent);
            }
        });
        dzI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(DizhiActivity.this,XiugaiDizhiActivity.class);
                startActivity(intent);
            }
        });

    }
}