package com.example.myapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.myapplication.R;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.wode.DingdanActivity;
import com.example.myapplication.wode.DizhiActivity;
import com.example.myapplication.wode.GerenxinxiActivity;
import com.example.myapplication.wode.XiugaimimaActivity;

/**
 * 我的
 */
public class WodeFragment extends Fragment {
    public static int RESULT_CODE=1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_wode,container,false);
        //从视图中获得控件
        ImageView myTitle =view.findViewById(R.id.touxiang);
        TextView textView=view.findViewById(R.id.nicheng);
        RelativeLayout rl2=view.findViewById(R.id.rl2);
        RelativeLayout li4=view.findViewById(R.id.l4);
        RelativeLayout li5=view.findViewById(R.id.l5);
        RelativeLayout li6=view.findViewById(R.id.l6);
        //若没有登录，跳转登录页面
        myTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getActivity(), DingdanActivity.class);
                startActivity(intent1);
            }
        });

        li4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent4=new Intent(getActivity(), GerenxinxiActivity.class);
                startActivity(intent4);
            }
        });
        li5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5=new Intent(getActivity(), DizhiActivity.class);
                startActivity(intent5);
            }
        });
        li6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6=new Intent(getActivity(), XiugaimimaActivity.class);
                startActivity(intent6);
            }
        });

        return view;


    }
}