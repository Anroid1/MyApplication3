package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.fragment.FenleiFragment;
import com.example.myapplication.fragment.GouwucheFragment;
import com.example.myapplication.fragment.ShouyeFragment;
import com.example.myapplication.fragment.WodeFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_shouye,iv_fenlei,iv_gouwuche,iv_wode;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_shouye=findViewById(R.id.na_shouye);
        iv_fenlei=findViewById(R.id.na_fenlei);
        iv_gouwuche=findViewById(R.id.na_gouwuche);
        iv_wode=findViewById(R.id.na_wode);
        //将ShouyeFragment添加到当前页面
        //实例化碎片管理器
        fragmentManager=getSupportFragmentManager();
        //开启事务 注意：每一个FragmentTransaction只能够commit（）一次。每次点击事件都能重新获取FragmentTransaction
        fragmentTransaction=fragmentManager.beginTransaction();
        //实例化碎片
        ShouyeFragment shouyeFragment=new ShouyeFragment();
        //设置碎片到页面
        fragmentTransaction.add(R.id.frame_main,shouyeFragment);
        //提交事务
        fragmentTransaction.commit();

        //添加监听器
        iv_shouye.setOnClickListener(this);
        iv_fenlei.setOnClickListener(this);
        iv_gouwuche.setOnClickListener(this);
        iv_wode.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        //实例化碎片管理器
        fragmentManager=getSupportFragmentManager();
        //开启事务
        fragmentTransaction=fragmentManager.beginTransaction();
        switch (view.getId()){
            case R.id.na_shouye:
                //实例化碎片
                ShouyeFragment shouyeFragment=new ShouyeFragment();
                //设置碎片到页面
                fragmentTransaction.replace(R.id.frame_main,shouyeFragment);
                //提交事务
                fragmentTransaction.commit();
                break;
            case R.id.na_fenlei:
                //实例化碎片
                FenleiFragment fenleiFragment=new FenleiFragment();
                //设置碎片到页面
                fragmentTransaction.replace(R.id.frame_main,fenleiFragment);
                //提交事务
                fragmentTransaction.commit();
                break;
            case R.id.na_gouwuche:
                //实例化碎片
                GouwucheFragment gouwucheFragment=new GouwucheFragment();
                //设置碎片到页面
                fragmentTransaction.replace(R.id.frame_main,gouwucheFragment);
                //提交事务
                fragmentTransaction.commit();
                break;
            case R.id.na_wode:
                //实例化碎片
                WodeFragment wodeFragment=new WodeFragment();
                //设置碎片到页面
                fragmentTransaction.replace(R.id.frame_main,wodeFragment);
                //提交事务
                fragmentTransaction.commit();
                break;

        }

    }
}