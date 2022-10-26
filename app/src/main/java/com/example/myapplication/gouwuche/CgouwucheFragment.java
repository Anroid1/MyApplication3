package com.example.myapplication.gouwuche;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.myapplication.R;
import com.example.myapplication.fragment.GouwucheFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CgouwucheFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_cgouwuche, container, false);
        //从视图中获得控件
        ListView myList=view.findViewById(R.id.list1);
        ImageView qubian=view.findViewById(R.id.qubian);
        qubian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相应控件的监听里面实现跳转
                FragmentManager fragmentManager=getFragmentManager();
                //开启事务
                fragmentManager.beginTransaction()
                        //替换frament
                        .replace(R.id.cgouwuche, new GouwucheFragment())
                        //每次替换新的fragment,旧的fragment都被添加到回退栈中，当按返回键时，将之前的fragment恢复。
                        .addToBackStack(null)
                        //提交事务
                        .commit();
            }
        });
        return view;
    }
}