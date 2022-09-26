package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.wode.DingdanActivity;

/**
 * 我的
 */
public class WodeFragment extends Fragment {
    ImageView touxiang;
    TextView nicheng;
    TextView textwo;
    ImageView dingdan;
    ImageView daifu;
    ImageView daishou;
    LinearLayout v1;
    LinearLayout v2;
    LinearLayout v3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_wode,container,false);
        //从视图中获得控件
        ImageView myTitle =view.findViewById(R.id.touxiang);
        TextView textView=view.findViewById(R.id.nicheng);
        RelativeLayout relativeLayout=view.findViewById(R.id.r1);
        //判断是否登录
        //若没有登录，跳转登录页面
        myTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), DingdanActivity.class);
                startActivity(intent);
            }
        });
        //设置我的功能列表
        String []names={"个人信息"};

        return view;


    }
}