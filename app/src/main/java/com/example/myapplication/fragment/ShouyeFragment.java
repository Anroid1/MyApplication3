package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.myapplication.R;
import com.example.myapplication.activity.ShangpinActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * shouye
 */
public class ShouyeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_shouye, container, false);
        //商品
        //定义数据
        int imgs[] = {R.drawable.lizi, R.drawable.juzi,R.drawable.xiyou,R.drawable.shouye3,R.drawable.fanqie,R.drawable.caomei};
        String goods[]={"梨子","桔子","西柚","豌豆荚","番茄","草莓"};
        int num[]={1000,1050,3000,2450,500,2000};
        String info[]={"清肺润心/家中必备","清肺润心/家中必备","清肺润心/家中必备","清肺润心/家中必备"
                ,"清肺润心/家中必备","清肺润心/家中必备"};
        String price[]={"￥13.20","￥5.00","￥4.00","￥4.50","￥2.00","￥3.30","￥5.20","￥5.00"};
        List<Map<String,Object>> items2=new ArrayList<Map<String,Object>>();
        for (int i=0;i<goods.length;i++){
            Map<String,Object> data2=new HashMap<String,Object>();
            data2.put("img",imgs[i]);
            data2.put("good",goods[i]);
            data2.put("price",price[i]);
            data2.put("info",info[i]);
            data2.put("num",num[i]+"");
            items2.add(data2);
        }
        //定义适配器
        SimpleAdapter simpleAdapter2=new SimpleAdapter(getActivity(),items2,R.layout.shouyeshangpin_item,
                new String[]{"good","img","price","info","num"},new int[]{R.id.tv_goods,R.id.iv_goods,R.id.tv_price,R.id.tv_info,R.id.tv_num});
        GridView gv_goods=view.findViewById(R.id.gv_goods);
        gv_goods.setAdapter(simpleAdapter2);
        //事件处理
        gv_goods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(),ShangpinActivity.class);
                intent.putExtra("gid",i);
                startActivity(intent);
            }
        });
        return view;
    }
}

