package com.example.myapplication.gouwuche;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.activity.ShangpinActivity;
import com.example.myapplication.entity.Goods;
import com.example.myapplication.fragment.ShouyeFragment;
import com.example.myapplication.util.CartDBService;

public class ShangpingjmActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private int amount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangpingjm);

        TextView tv_info = findViewById(R.id.tv_info);
        TextView tv_price = findViewById(R.id.tv_price);
        TextView tv_number = findViewById(R.id.tv_number);
        ImageView iv_info = findViewById(R.id.iv_info);
        ImageView fuhao = findViewById(R.id.tv_fh);
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

        Bundle b = getIntent().getExtras();

//获取Bundle的信息

        String name = b.getString("name");
        int num = b.getInt("num");
        Float price = b.getFloat("price");
        int img = b.getInt("img");
        String guige = b.getString("guige");
        int img1 = b.getInt("img");
        int img2 = b.getInt("img");

        tv_info.setText(name);
        tv_number.setText(num + "");
        tv_price.setText(price + "");
        iv_info.setImageResource(img);
        spt7.setText(guige);
        spI1.setImageResource(img1);
        spI2.setImageResource(img2);
        //加减控件实现

        fuhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(ShangpingjmActivity.this, MainActivity.class);
                intent1.putExtra("id",2);
                startActivity(intent1);
            }
        });

        reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount > 1)
                    amount--;
                else
                    Toast.makeText(ShangpingjmActivity.this, "已是最小数量", Toast.LENGTH_SHORT).show();

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
                int w = Integer.parseInt(text);
                //获取页面的数据
                Goods good1 = new Goods();
                good1.setName(name);
                good1.setGuige(guige);
                good1.setPrice(price);
                good1.setNum(w);
                good1.setImg(img);
                //将数据存入SQLite数据表
                CartDBService cartDBService = new CartDBService(ShangpingjmActivity.this);
                cartDBService.addCart(good1);
                Toast.makeText(ShangpingjmActivity.this, "商品已添加", Toast.LENGTH_SHORT).show();

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
                good2.setName(name);
                good2.setGuige(guige);
                good2.setPrice(price);
                good2.setNum(num);
                good2.setImg(img);
                //将数据存入SQLite数据表
                CartDBService cartDBService = new CartDBService(ShangpingjmActivity.this);
                cartDBService.addCartA(good2);
                Intent intent1=new Intent(ShangpingjmActivity.this, LijigoumaiActivity.class);
                startActivity(intent1);


            }
        });

    }
}
