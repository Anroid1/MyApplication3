package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.adapter.CartAdapter;
import com.example.myapplication.entity.Goods;
import com.example.myapplication.gouwuche.CgouwucheFragment;
import com.example.myapplication.gouwuche.ShangpingjmActivity;
import com.example.myapplication.util.CartDBService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车
 */
public class GouwucheFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_gouwuche, container, false);
        //从视图中获得控件
        ListView myList=view.findViewById(R.id.list1);
        TextView bianji=view.findViewById(R.id.bianji);
        //查询数据库
        CartDBService cartDBService=new CartDBService(getContext());
        cartDBService.getAllCart();
        List<Goods> items=cartDBService.getAllCart();
        //实例化适配器
        CartAdapter cartAdapter=new CartAdapter(getContext(),items);
        myList.setAdapter(cartAdapter);

        //点击编辑标签跳转页面
        bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相应控件的监听里面实现跳转
                FragmentManager fragmentManager=getFragmentManager();
                //开启事务
                fragmentManager.beginTransaction()
                //替换frament
                .replace(R.id.gouwuche, new CgouwucheFragment())
                //提交事务
                .addToBackStack(null)
                .commit();
            }
        });
        //list跳转商品详情页
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 第二步：通过Intent跳转至新的页面
                Intent intent = new Intent(getActivity(), ShangpingjmActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}