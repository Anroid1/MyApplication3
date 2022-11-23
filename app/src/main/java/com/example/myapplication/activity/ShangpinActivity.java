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
import com.example.myapplication.adapter.CartAdapter;
import com.example.myapplication.entity.Goods;
import com.example.myapplication.gouwuche.ComfireActivity;
import com.example.myapplication.gouwuche.LijigoumaiActivity;
import com.example.myapplication.util.CartDBService;

import java.util.HashMap;

public class ShangpinActivity extends AppCompatActivity {
    private int amount = 1;
    private HashMap<Object,Integer> pichOnMap;//记录子项选择状态

    public HashMap<Object, Integer> getPichOnMap() {
        return pichOnMap;
    }

    public void setPichOnMap(HashMap<Object, Integer> pichOnMap) {
        this.pichOnMap = pichOnMap;
    }
    int imgs[] = {R.drawable.lizi, R.drawable.juzi, R.drawable.xiyou, R.drawable.shouye3,
            R.drawable.fanqie, R.drawable.caomei};
    String goods[] = {"丹东99草莓新鲜水果个大且甜现摘现在现发货应季水果包邮送到家 ...", "爱媛38号果冻橙新鲜橙子当季水果水果柑橘蜜桔子整箱大果橙包邮 ... ",
            "当季蔬菜套餐包括西兰花生菜包菜大西芹青瓜小南瓜等新鲜的蔬菜 ...", "豌豆荚", "番茄", "丹东99草莓新鲜水果个大且甜现摘现在现发货应季水果包邮送到家 ..."};

    String info[] = {"清肺润心/家中必备", "清肺润心/家中必备", "清肺润心/家中必备", "清肺润心/家中必备"
            , "清肺润心/家中必备", "清肺润心/家中必备"};
    String price[] = {"￥13.20", "￥5.00", "￥4.00", "￥4.50", "￥2.00", "￥3.30", "￥5.20", "￥5.00"};
    String shuliang[] = {"数量", "数量", "数量", "数量", "数量", "数量"};
    String fuwu[] = {"规格", "规格", "规格", "规格", "规格", "规格"};
    int num[] = {amount, amount, amount, amount, amount, amount};
    String shijian[] = {"中大果", "60mm-70mm", "新鲜蔬菜", "中大果", "中大果", "中大果"};
    String xiangqing[] = {"商品详情", "商品详情", "商品详情", "商品详情", "商品详情", "商品详情"};
    int imgs1[] = {R.drawable.lizi, R.drawable.juzi, R.drawable.xiyou, R.drawable.shouye3, R.drawable.fanqie, R.drawable.caomei};
    int imgs2[] = {R.drawable.lizi, R.drawable.juzi, R.drawable.xiyou, R.drawable.shouye3, R.drawable.fanqie, R.drawable.caomei};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangpin);
        //获取上一个页面传递的数据
        Intent intent = getIntent();
        int i = intent.getIntExtra("gid", 0);
        //获取页面上的控件
        ImageView iv_info = findViewById(R.id.iv_info);
        TextView tv_info = findViewById(R.id.tv_info);
        TextView tv_price = findViewById(R.id.tv_price);
        TextView tv_number = findViewById(R.id.tv_number);
        TextView tv_desc = findViewById(R.id.tv_desc);
        TextView spt6 = findViewById(R.id.spt6);
        TextView spt7 = findViewById(R.id.spt7);
        TextView spt8 = findViewById(R.id.spt8);
        ImageView spI1 = findViewById(R.id.spI1);
        ImageView spI2 = findViewById(R.id.spI2);
        TextView tv_addcart = findViewById(R.id.tv_addcart);
        TextView tv_buy = findViewById(R.id.tv_buy);
        Button reduce = findViewById(R.id.btnDecrease);
        Button add = findViewById(R.id.btnIncrease);
        //将数据添加到控件
        iv_info.setImageResource(imgs[i]);
        tv_info.setText(goods[i]);
        tv_price.setText(price[i]);
        tv_number.setText(num[i] + "");
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
                int w[] = {Integer.parseInt(text), Integer.parseInt(text), Integer.parseInt(text), Integer.parseInt(text)
                        , Integer.parseInt(text), Integer.parseInt(text)};
                //获取页面的数据
                Goods good1 = new Goods();
                good1.setName(goods[i]);
                good1.setGuige(shijian[i]);
                String s = price[i].substring(1);
                good1.setPrice((Float.parseFloat(s)));
                good1.setNum(w[i]);
                good1.setImg(imgs[i]);
                //将数据存入SQLite数据表
                CartDBService cartDBService = new CartDBService(ShangpinActivity.this);
                cartDBService.addCart(good1);
                Toast.makeText(ShangpinActivity.this, "商品已添加", Toast.LENGTH_SHORT).show();

            }
        });
        //立即购买
        tv_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tv_number.getText().toString();
                int w[] = {Integer.parseInt(text), Integer.parseInt(text), Integer.parseInt(text), Integer.parseInt(text)
                        , Integer.parseInt(text), Integer.parseInt(text)};
                //获取页面的数据
                Goods good2 = new Goods();
                good2.setName(goods[i]);
                good2.setGuige(shijian[i]);
                String s = price[i].substring(1);
                good2.setPrice((Float.parseFloat(s)));
                good2.setNum(w[i]);
                good2.setImg(imgs[i]);
                //将数据存入SQLite数据表
                CartDBService cartDBService = new CartDBService(ShangpinActivity.this);
                cartDBService.addCartA(good2);
                Intent intent1=new Intent(ShangpinActivity.this, LijigoumaiActivity.class);
                startActivity(intent1);


            }
        });


    }
}


