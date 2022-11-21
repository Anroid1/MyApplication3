package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.adapter.CartAdapter;
import com.example.myapplication.entity.Goods;
import com.example.myapplication.gouwuche.CgouwucheFragment;
import com.example.myapplication.gouwuche.ComfireActivity;
import com.example.myapplication.gouwuche.ShangpingjmActivity;
import com.example.myapplication.util.CartDBService;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 购物车
 */
public class GouwucheFragment extends Fragment implements CartAdapter.JiPriceInterface {
    List<Goods> items;
    private float totalCount;// 购买的商品总价
    TextView zprice;
    ImageView jisuan;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_gouwuche, container, false);
        //从视图中获得控件
        ListView myList=view.findViewById(R.id.list1);
        TextView bianji=view.findViewById(R.id.bianji);
        CheckBox b2=view.findViewById(R.id.mygouwuchecheckBox);
        zprice=view.findViewById(R.id.zprice);
        jisuan=view.findViewById(R.id.jiesuan);
        //查询数据库
        CartDBService cartDBService=new CartDBService(getContext());
        cartDBService.getAllCart();
        items=cartDBService.getAllCart();
        //实例化适配器
        CartAdapter cartAdapter=new CartAdapter(getContext(),items);
        myList.setAdapter(cartAdapter);
        cartAdapter.setJiPriceInterface(this);

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
                priceControl(map);
                cartAdapter.setPichOnMap(map);
                cartAdapter.notifyDataSetChanged();
            }
        });
        jisuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalCount<=0){
                    Toast.makeText(getActivity(),"请选择需要付款的商品",Toast.LENGTH_LONG).show();

                }else{
                    Intent intent=new Intent(getActivity(), ComfireActivity.class);
                    //被选中的项目
                    HashMap<Object,Integer> map=cartAdapter.getPichOnMap();
                    //int id[]=new int[items.size()];
                    int a=0;
                    for (int i=0;i<items.size();i++){

                        if (map.get(items.get(i).getId())==1){
                            intent.putExtra(a+"",items.get(i));
                            //id[a]=i;
                            a++;
                        }
                    }
                    intent.putExtra("id",a);
                    startActivity(intent);
                }
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

    @Override
    public void jiPrice(HashMap<Object, Integer> pitchOnMap) {
        priceControl(pitchOnMap);
    }
    public void  priceControl(HashMap<Object,Integer> pitchOnMap){
        totalCount=0;
         //控制总价的显示
        for (int i=0;i< items.size();i++){
        if (pitchOnMap.get(items.get(i).getId())==1){
        totalCount=totalCount+items.get(i).getPrice()*items.get(i).getNum();
        }
        }
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        zprice.setText(decimalFormat.format(totalCount));


        }
}