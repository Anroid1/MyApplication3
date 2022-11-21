package com.example.myapplication.gouwuche;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CartAdapter;
import com.example.myapplication.entity.Goods;
import com.example.myapplication.fragment.GouwucheFragment;
import com.example.myapplication.fragment.ShouyeFragment;
import com.example.myapplication.util.CartDBService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CgouwucheFragment extends Fragment implements CartAdapter.JiPriceInterface{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cgouwuche, container, false);
        //从视图中获得控件
        ListView myList = view.findViewById(R.id.list1);
        ImageView qubian = view.findViewById(R.id.qubian);
        CheckBox b2 = view.findViewById(R.id.mygouwuchecheckBox);
        ImageView shangchu=view.findViewById(R.id.shangchu);


        //查询数据库
        CartDBService cartDBService = new CartDBService(getContext());
        cartDBService.getAllCart();
        List<Goods> items = cartDBService.getAllCart();
        //实例化适配器
        CartAdapter cartAdapter = new CartAdapter(getContext(), items);
        myList.setAdapter(cartAdapter);
        cartAdapter.setJiPriceInterface(this);
        //实现全选
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<Object, Integer> map = cartAdapter.getPichOnMap();
                boolean isCheck = false;
                boolean isUnCheck = false;
                Iterator iterator = map.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    if (Integer.valueOf(entry.getValue().toString()) == 1) {
                        isCheck = true;
                    } else {
                        isUnCheck = true;
                    }
                }
                if (isCheck == true && isUnCheck == false) {
                    //已经全选，做反选
                    for (int i = 0; i < items.size(); i++) {
                        map.put(items.get(i).getId(), 0);
                    }
                    b2.setChecked(false);
                    //做全选
                } else if (isCheck == false && isUnCheck == true) {
                    for (int i = 0; i < items.size(); i++) {
                        map.put(items.get(i).getId(), 1);
                    }
                    b2.setChecked(true);

                } else if (isCheck == true && isUnCheck == true) {
                    //部分选中
                    for (int i = 0; i < items.size(); i++) {
                        map.put(items.get(i).getId(), 1);

                    }
                    b2.setChecked(true);
                }
                cartAdapter.setPichOnMap(map);
                cartAdapter.notifyDataSetChanged();
            }
        });
        //实现删除
        shangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //被选中的
                HashMap<Object,Integer>map=cartAdapter.getPichOnMap();
                int[] id=new int[items.size()];
                for (int i=0;i< items.size();i++){
                    if (map.get(items.get(i).getId())==1){
                        int id1=items.get(i).getId();
                        cartDBService.deleteCart(id1);
                        Toast.makeText(getActivity(),"商品删除成功",Toast.LENGTH_SHORT).show();
                        FragmentManager fragmentManager=getFragmentManager();
                        //开启事务
                        fragmentManager.beginTransaction()
                                //替换frament
                                .replace(R.id.gouwuche, new CgouwucheFragment())
                                //提交事务
                                .addToBackStack(null)
                                .commit();

                    }
                }
                cartAdapter.notifyDataSetChanged();



            }
        });
        qubian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相应控件的监听里面实现跳转
                FragmentManager fragmentManager = getFragmentManager();
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

    @Override
    public void jiPrice(HashMap<Object, Integer> pitchOnMap) {

    }
}
