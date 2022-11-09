package com.example.myapplication.wode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.DizhiAdapter;
import com.example.myapplication.entity.Dizhi;
import com.example.myapplication.util.DizhiDbService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DizhibjActivity extends AppCompatActivity {
    Button dzb;
    ListView list1;
    ImageView dzI,qubian;
    CheckBox rb_all;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dizhibj);
        dzb=findViewById(R.id.dzb);
        list1=findViewById(R.id.list1);
        dzI=findViewById(R.id.dzI);
        qubian=findViewById(R.id.qubian);
        rb_all=findViewById(R.id.rb_all);
        //查询数据库
        DizhiDbService dizhiDbService=new DizhiDbService(this);
        List<Dizhi> items=dizhiDbService.getAllDizhi();

        //实例化适配器
        DizhiAdapter dizhiAdapter=new DizhiAdapter(this,items);
        list1.setAdapter(dizhiAdapter);
        qubian=findViewById(R.id.qubian);
        rb_all=findViewById(R.id.rb_all);
       rb_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<Object,Integer> map=dizhiAdapter.getPichOnMap();
                boolean isCheck=false;
                boolean isUncheck=false;
                Iterator iterator=map.entrySet().iterator();
                while (iterator.hasNext()){
                    Map.Entry entry=(Map.Entry) iterator.next();
                    if(Integer.valueOf(entry.getValue().toString())==1){
                        isCheck=true;
                    }else {
                        isUncheck=true;
                    }
                }
                if (isCheck==true && isUncheck==false){
                    //已经全选，做反选
                    for (int i=0;i<items.size();i++){
                        map.put(items.get(i).getId(),0);
                    }
                    rb_all.setChecked(false);
                }else if (isCheck==false && isUncheck==true){
                    //一个没选，做选中
                    for (int i=0;i<items.size();i++){
                        map.put(items.get(i).getId(),1);
                    }
                    rb_all.setChecked(true);
                }else if (isCheck==true && isUncheck==true){
                    //部分选中
                    for (int i=0;i<items.size();i++){
                        map.put(items.get(i).getId(),1);
                    }
                    rb_all.setChecked(true);
                }
            }
        });
        qubian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DizhibjActivity.this,DizhiActivity.class);
                startActivity(intent);
            }
        });



    }

}