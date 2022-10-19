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
import com.example.myapplication.gouwuche.CgouwucheFragment;
import com.example.myapplication.gouwuche.ShangpingjmActivity;

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
        //设置ListView
        int [] imgs={R.drawable.caomei,R.drawable.chengzi,R.drawable.shucai};
        String [] names={"丹东99草莓新鲜水果个大且甜现摘现在现发货应季水果包邮送到家 ...",
                "爱媛38号果冻橙新鲜橙子当季水果水果柑橘蜜桔子整箱大果橙包邮 ... ","当季蔬菜套餐包括西兰花生菜包菜" +
                "大西芹青瓜小南瓜等新鲜的蔬菜 ..."};
        String [] descs={"中大果，5斤","65mm-70mm,2斤","新鲜蔬菜，10斤"};
        String [] prices={"￥19.80","￥10.80","￥26.00"};
        String [] numbers={"1","2","3"};
        //定义数据
        List<Map<String,Object>> date=new ArrayList<Map<String,Object>>();
        for (int i=0;i< names.length;i++){
            Map<String,Object> items=new HashMap<String,Object>();
            items.put("name",names[i]);
            items.put("img",imgs[i]);
            items.put("desc",descs[i]);
            items.put("price",prices[i]);
            items.put("number",numbers[i]);
            date.add(items);
        }
        //实例化适配器(当前容器，数据，单项布局文件，map中的键名称，单项布局文件中控件的id)
        SimpleAdapter simpleAdapter=new SimpleAdapter(getActivity(),date,R.layout.gouwuche_items,new String[]{"name","img","desc","price","number"},
                new int[]{R.id.tv_name,R.id.iv_img,R.id.tv_desc,R.id.jiage,R.id.tv_shuliang});
        //将适配器添加到ListView
        myList.setAdapter(simpleAdapter);
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