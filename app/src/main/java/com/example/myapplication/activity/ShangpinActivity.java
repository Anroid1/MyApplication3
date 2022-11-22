package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.entity.Goods;
import com.example.myapplication.util.CartDBService;

public class ShangpinActivity extends AppCompatActivity {
    private int amount = 1;
    int imgs[] = {R.drawable.lizi, R.drawable.juzi,R.drawable.mangguo1,R.drawable.wandou1,
            R.drawable.fanqie3,R.drawable.caomei};
    String goods[]={"牧疆山新疆库尔勒大红香酥梨1斤新鲜水果当季香梨整箱青雪酥梨应季特产梨子包邮",
            "四川爱媛38号果冻橙手剥橙子水果新鲜柑橘子当季1斤甜橙整箱现摘",
            "攀枝花凯特芒果新鲜1斤四川应当季整箱水果礼盒特大忙果包邮芒果",
            "豌豆新鲜云南高原带壳青豆角脆甜水果豌豆荚现摘现发蔬菜净重1斤",
            "普罗旺斯西红柿新鲜自然熟山东海阳柿子生吃沙瓤1水果大番茄",
            "新鲜草莓牛奶草莓非丹东99草莓奶油红颜当季礼盒孕妇水果现摘现发"};
    String info[]={"清肺润心/家中必备","四川爱媛/口味爆甜","攀枝花凯特芒","颗颗圆满/软软糯糯"
            ,"清甜爽口/汁水丰富","新鲜草莓/营养丰富"};
    String price[]={"￥13.20","￥5.00","￥4.00","￥4.50","￥2.00","￥3.30","￥5.20","￥5.00"};
    String shuliang[]={"数量","数量","数量","数量","数量","数量"};
    String fuwu[]={"规格","规格","规格","规格","规格","规格"};
    int num[]={amount,amount,amount,amount,amount,amount};
    String shijian[]={"中大果","60mm-70mm","新鲜蔬菜","中大果","中大果","中大果"};
    String xiangqing[]={"商品详情","商品详情","商品详情","商品详情","商品详情","商品详情"};
    int imgs1[] = {R.drawable.lizi1, R.drawable.chengzi1,R.drawable.mangguo2,R.drawable.wandou2,R.drawable.fanqie1,R.drawable.caomei1};
    int imgs2[] = {R.drawable.lizi2, R.drawable.chengzi2,R.drawable.mangguo3,R.drawable.wandou3,R.drawable.fanqie2,R.drawable.caomei2};

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
        Button reduce=findViewById(R.id.btnDecrease);
        Button add=findViewById(R.id.btnIncrease);
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
        //加减控件实现

            reduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (amount > 1)
                        amount--;
                    else
                        Toast.makeText(ShangpinActivity.this, "已是最小数量", Toast.LENGTH_SHORT).show();

                    tv_number.setText(String.valueOf(amount));

                }
            });
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    amount++;
                    tv_number.setText(amount + "");

                }

            });
        //加入购入车功能
        tv_addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tv_number.getText().toString();
                int w[] ={Integer.parseInt(text),Integer.parseInt(text),Integer.parseInt(text),Integer.parseInt(text)
                        ,Integer.parseInt(text),Integer.parseInt(text)};
                //获取页面的数据
                Goods good1=new Goods();
                good1.setName(goods[i]);
                good1.setGuige(shijian[i]);
                String s=price[i].substring(1);
                good1.setPrice((Float.parseFloat(s)));
                good1.setNum(w[i]);
                good1.setImg(imgs[i]);
                //将数据存入SQLite数据表
                CartDBService cartDBService=new CartDBService(ShangpinActivity.this);
                cartDBService.addCart(good1);
                Toast.makeText(ShangpinActivity.this,"商品已添加",Toast.LENGTH_SHORT).show();

            }
        });

    }

}
