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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.activity.ShangpinActivity;
import com.example.myapplication.adapter.CartAdapter;
import com.example.myapplication.entity.Goods;
import com.example.myapplication.util.CartDBService;
import com.example.myapplication.util.LiJiAdapter;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

public class LijigoumaiActivity extends AppCompatActivity {
    List<Goods> items;
    ListView list2;
    TextView tv_count;
    Button tv_comfire;
    CheckBox radio1,radio2,radio3;
    float totalprice=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lijigoumai);
        list2 = findViewById(R.id.list2);
        tv_count=findViewById(R.id.tv_acount);
        ImageView fuhao = findViewById(R.id.tv_fh);
        tv_comfire=findViewById(R.id.tv_comfire);
        radio1=findViewById(R.id.radio1);
        radio2=findViewById(R.id.radio2);
        radio3=findViewById(R.id.radio3);
        initView();

        fuhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(LijigoumaiActivity.this, ShangpinActivity.class);
                startActivity(intent1);

            }
        });
        //查询数据库
        CartDBService cartDBService=new CartDBService(this);
        cartDBService.getLastCart();
        items=cartDBService.getLastCart();
        //实例化适配器
        LiJiAdapter lijiAdapter=new LiJiAdapter(LijigoumaiActivity.this,items);
        list2.setAdapter(lijiAdapter);
        totalprice=totalprice+items.get(0).getPrice()* items.get(0).getNum();
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        tv_count.setText(totalprice+6+"");
        //处理方案：
        int totalHeight = 0;
        // 如果没有设置数据适配器，则ListView没有子项，返回。
        for (int index = 0, len = lijiAdapter.getCount(); index < len; index++) {
            View listViewItem = lijiAdapter.getView(index, null, list2);
            // 计算子项View 的宽高
            listViewItem.measure(0, 0);
            // 计算所有子项的高度和
            totalHeight += listViewItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = list2.getLayoutParams();
        // listView.getDividerHeight()获取子项间分隔符的高度
        // params.height设置ListView完全显示需要的高度
        params.height = totalHeight + (list2.getDividerHeight() * (lijiAdapter.getCount() - 1));
        list2.setLayoutParams(params);
        ScrollView scrollview = (ScrollView) findViewById(R.id.ll_info);
        scrollview.smoothScrollTo(0, 0);


        tv_comfire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radio1.isChecked() == false &&radio2.isChecked() == false&&radio3.isChecked() == false){
                    Toast.makeText(LijigoumaiActivity.this, "请选择支付方式", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(LijigoumaiActivity.this, JSchenggongActivity.class);
                    startActivity(intent);
                }


            }
        });
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