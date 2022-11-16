package com.example.myapplication.wode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.adapter.DizhiAdapter;
import com.example.myapplication.entity.Dizhi;
import com.example.myapplication.fragment.WodeFragment;
import com.example.myapplication.util.DizhiDbService;

import java.util.List;

public class DizhiActivity extends AppCompatActivity {

    Button dzb;
    ListView list1;
    ImageView dzI,xiugaidz;
    TextView bianji;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dizhi);
        dzb=findViewById(R.id.dzb);
        list1=findViewById(R.id.list1);
        dzI=findViewById(R.id.dzI);
        xiugaidz=findViewById(R.id.xiugaidz);
        bianji=findViewById(R.id.bianji);
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
                Intent intent2=new Intent(DizhiActivity.this, MainActivity.class);
                startActivity(intent2);
            }
        });
        bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DizhiActivity.this,DizhibjActivity.class);
                startActivity(intent);
            }
        });
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dizhi dizhi= (Dizhi) parent.getItemAtPosition(position);
                Intent intent1=null;
                intent1=new Intent(DizhiActivity.this,XiugaiDizhiActivity.class);
                int id1=dizhi.getId();
                String name=dizhi.getName();
                String phone=dizhi.getPhone();
                String diqu=dizhi.getDiqu();
                String xdizhi=dizhi.getXdizhi();
                 intent1.putExtra("id",id1);
                 intent1.putExtra("name",name);
                 intent1.putExtra("phone",phone);
                 intent1.putExtra("diqu",diqu);
                 intent1.putExtra("xdizhi",xdizhi);
                 startActivity(intent1);

            }
        });
    }

}