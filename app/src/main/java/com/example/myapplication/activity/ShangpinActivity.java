package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.entity.Goods;
import com.example.myapplication.util.CartDBService;

public class ShangpinActivity extends AppCompatActivity {
    int imgs[] = {R.drawable.lizi, R.drawable.juzi,R.drawable.xiyou,R.drawable.shouye3,
            R.drawable.fanqie,R.drawable.caomei};
    String goods[]={"梨子","桔子","西柚","豌豆荚","番茄","草莓"};

    String info[]={"清肺润心/家中必备","清肺润心/家中必备","清肺润心/家中必备","清肺润心/家中必备"
            ,"清肺润心/家中必备","清肺润心/家中必备"};
    String price[]={"￥3","￥5","￥4","￥4.5","￥2","￥3","￥5","￥5"};
    String shuliang[]={"销量","销量","销量","销量","销量","销量"};
    int num[]={1000,1050,3000,2450,500,2000};
    String fuwu[]={"服务","服务","服务","服务","服务","服务"};
    String shijian[]={"48小时发货","48小时发货","48小时发货","48小时发货","48小时发货","48小时发货"};
    String xiangqing[]={"商品详情","商品详情","商品详情","商品详情","商品详情","商品详情"};
    int imgs1[] = {R.drawable.lizi, R.drawable.juzi,R.drawable.xiyou,R.drawable.shouye3,R.drawable.fanqie,R.drawable.caomei};
    int imgs2[] = {R.drawable.lizi, R.drawable.juzi,R.drawable.xiyou,R.drawable.shouye3,R.drawable.fanqie,R.drawable.caomei};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangpin);
        //获取上一个页面传递的数据
        Intent intent=getIntent();
        int i=intent.getIntExtra("gid",0);
        //获取页面上的控件
        ImageView iv_info=findViewById(R.id.iv_info);
        TextView tv_info=findViewById(R.id.tv_info);
        TextView tv_price=findViewById(R.id.tv_price);
        TextView tv_number=findViewById(R.id.tv_number);
        TextView tv_desc=findViewById(R.id.tv_desc);
        TextView spt6=findViewById(R.id.spt6);
        TextView spt7=findViewById(R.id.spt7);
        TextView spt8=findViewById(R.id.spt8);
        ImageView spI1=findViewById(R.id.spI1);
        ImageView spI2=findViewById(R.id.spI2);
        TextView tv_addcart=findViewById(R.id.tv_addcart);
        TextView tv_buy=findViewById(R.id.tv_buy);
        //将数据添加到控件
        iv_info.setImageResource(imgs[i]);
        tv_info.setText(goods[i]);
        tv_price.setText(price[i]);
        tv_number.setText(num[i]+"");
        tv_desc.setText(info[i]);
        spt6.setText(fuwu[i]);
        spt7.setText(shijian[i]);
        spt8.setText(xiangqing[i]);
        spI1.setImageResource(imgs1[i]);
        spI2.setImageResource(imgs2[i]);
        //加入购入车功能
        tv_addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取页面的数据
                Goods good1=new Goods();
                good1.setName(goods[i]);
                String s=price[i].substring(1);
                good1.setPrice((Float.parseFloat(s)));
                good1.setNum(1);
                good1.setImg(imgs[i]);
                //将数据存入SQLite数据表
                CartDBService cartDBService=new CartDBService(ShangpinActivity.this);
                cartDBService.addCart(good1);
                Toast.makeText(ShangpinActivity.this,"商品已添加",Toast.LENGTH_SHORT).show();

            }
        });

    }

}
