package com.example.myapplication.gouwuche;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.adapter.CartAdapter;
import com.example.myapplication.entity.Goods;
import com.example.myapplication.wode.DizhiActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ComfireActivity extends AppCompatActivity {
    ListView list2;
    TextView tv_count;
    Button tv_comfire;
    CheckBox radio1,radio2,radio3;
    float totalprice=0;
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comfire);
            list2 = findViewById(R.id.list2);
            tv_count=findViewById(R.id.tv_acount);
            tv_comfire=findViewById(R.id.tv_comfire);
            radio1=findViewById(R.id.radio1);
            radio2=findViewById(R.id.radio2);
            radio3=findViewById(R.id.radio3);
            ImageView fuhao = findViewById(R.id.tv_fh);
            ImageView dizhi=findViewById(R.id.jinru);
            initView();

        fuhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(ComfireActivity.this, MainActivity.class);
                intent1.putExtra("id",2);
                startActivity(intent1);
            }
        });
        dizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(ComfireActivity.this, DizhiActivity.class);
                startActivity(intent2);

            }
        });


            tv_comfire.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (radio1.isChecked() == false &&radio2.isChecked() == false&&radio3.isChecked() == false){
                        Toast.makeText(ComfireActivity.this, "请选择支付方式", Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intent = new Intent(ComfireActivity.this, JSchenggongActivity.class);
                        startActivity(intent);
                    }


                }
            });


            //获取Intent数据，初始化页面
            Intent intent = getIntent();
            int a = intent.getIntExtra("id", 0);
            ArrayList<Goods> goodsList = new ArrayList<Goods>();
            for (int i = 0; i < a; i++) {
                Goods goods = new Goods();
                goods = (Goods) intent.getSerializableExtra(i + "");
                goodsList.add(goods);
            }
            //封装数据
            List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < goodsList.size(); i++) {
                Goods goods = goodsList.get(i);
                Map<String, Object> item = new HashMap<String, Object>();
                item.put("name", goods.getName());
                item.put("price", goods.getPrice());
                item.put("num", goods.getNum());
                item.put("img", goods.getImg());
                item.put("guige", goods.getGuige());
                totalprice=totalprice+goods.getPrice()* goods.getNum();
                lists.add(item);
                tv_count.setText(totalprice+6+"");

            }

            SimpleAdapter simpleAdapter = new SimpleAdapter(this, lists, R.layout.comfire_items, new String[]{"name", "price", "num", "img", "guige"},
                    new int[]{R.id.tv_name, R.id.jiage, R.id.tv_shuliang, R.id.iv_img, R.id.tv_desc});
            list2.setAdapter(simpleAdapter);
            //处理方案：
            int totalHeight = 0;
            // 如果没有设置数据适配器，则ListView没有子项，返回。
            for (int index = 0, len = simpleAdapter.getCount(); index < len; index++) {
                View listViewItem = simpleAdapter.getView(index, null, list2);
                // 计算子项View 的宽高
                listViewItem.measure(0, 0);
                // 计算所有子项的高度和
                totalHeight += listViewItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = list2.getLayoutParams();
            // listView.getDividerHeight()获取子项间分隔符的高度
            // params.height设置ListView完全显示需要的高度
            params.height = totalHeight + (list2.getDividerHeight() * (simpleAdapter.getCount() - 1));
            list2.setLayoutParams(params);
            ScrollView scrollview = (ScrollView) findViewById(R.id.ll_info);
            scrollview.smoothScrollTo(0, 0);

        }

    private void initView() {
        //监听选中取消事件
        radio1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    radio1.setChecked(true);
                    radio2.setChecked(false);
                    radio3.setChecked(false);
                }else {
                    radio1.setChecked(false);
                }
            }
        });
        radio2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    radio1.setChecked(false);
                    radio2.setChecked(true);
                    radio3.setChecked(false);
                }else {
                    radio2.setChecked(false);
                }
            }
        });
        radio3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    radio1.setChecked(false);
                    radio3.setChecked(true);
                    radio2.setChecked(false);
                }else {
                    radio3.setChecked(false);
                }
            }
        });
    }
}