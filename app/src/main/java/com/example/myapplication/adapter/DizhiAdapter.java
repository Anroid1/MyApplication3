package com.example.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.entity.Dizhi;

import java.util.List;

/*
地址适配器
 */
public class DizhiAdapter extends BaseAdapter {
    private Context context;//上下文
    private List<Dizhi> items;//数据源
    //构造方法
    public DizhiAdapter(Context context,List<Dizhi> items){
        this.context=context;
        this.items=items;
    }
    @Override
    public int getCount() {
        //子项的个数
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        //根据子项布局文件过的每一项的视图
        view=View.inflate(context, R.layout.dizhi_item,null);
        //实例化子项的控件
        TextView name;
        TextView phone;
        TextView diqu;
        TextView xdizhi;
        name=view.findViewById(R.id.name);
        phone=view.findViewById(R.id.phone);
        diqu=view.findViewById(R.id.diqu);
        xdizhi=view.findViewById(R.id.xdizhi);
        //数据装载到控件
        Dizhi dizhi=items.get(i);
        name.setText(dizhi.getName());
        phone.setText(dizhi.getPhone());
        diqu.setText(dizhi.getDiqu());
        xdizhi.setText(dizhi.getXdizhi());
        return view;
    }
}
