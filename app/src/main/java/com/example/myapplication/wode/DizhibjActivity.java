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
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.DizhiAdapter;
import com.example.myapplication.adapter.DizhibjAdapter;
import com.example.myapplication.entity.Dizhi;
import com.example.myapplication.util.DizhiDbService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DizhibjActivity extends AppCompatActivity implements  DizhibjAdapter.RefreshInterface{
    Button dzb;
    ListView list1;
    ImageView dzI,qubian,shangchu;
    CheckBox rb_all;
    List<Dizhi> items;
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
         items=dizhiDbService.getAllDizhi();

        //实例化适配器
        DizhibjAdapter dizhibjAdapter=new DizhibjAdapter(this,items);
        list1.setAdapter(dizhibjAdapter);
        //
        dizhibjAdapter.setRefreshInterface(this);
        qubian=findViewById(R.id.qubian);
        shangchu=findViewById(R.id.shangchu);
        rb_all=findViewById(R.id.rb_all);
        //实现全选
       rb_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<Object,Integer> map=dizhibjAdapter.getPichOnMap();
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
                anControl(map);
                dizhibjAdapter.setPichOnMap(map);
                dizhibjAdapter.notifyDataSetChanged();
            }
        });
        qubian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DizhibjActivity.this,DizhiActivity.class);
                startActivity(intent);
            }
        });
        shangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(DizhibjActivity.this,DizhibjActivity.class);

                //被选中的
                HashMap<Object,Integer>map=dizhibjAdapter.getPichOnMap();
                int[] id = new int[items.size()];

                for (int i=0;i<items.size();i++){
                    if(map.get(items.get(i).getId())==1){
                        int id1=items.get(i).getId();
                        DizhiDbService dizhiDbService=new DizhiDbService(DizhibjActivity.this);
                        dizhiDbService.deleteDizhi(id1);
                        Toast.makeText(DizhibjActivity.this,"地址删除成功",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(DizhibjActivity.this,DizhiActivity.class);
                        startActivity(intent);
                    }
                }

            }
        });
    }
    @Override
    public void refresh(HashMap<Object, Integer> pitchOnMap) {
                anControl(pitchOnMap);
    }
    public void anControl(Map<Object,Integer> pitchOnMap){
        for (int i=0;i<items.size();i++){
            if (pitchOnMap.get(items.get(i).getId())==1){
                int id1=items.get(i).getId();
                Toast.makeText(DizhibjActivity.this,"选择成功",Toast.LENGTH_SHORT).show();
            }
        }
    }
}