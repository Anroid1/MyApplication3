package com.example.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.entity.Dizhi;

import java.util.HashMap;
import java.util.List;

public class DizhibjAdapter extends BaseAdapter {
    private Context context;//上下文
    private List<Dizhi> items;//数据源
    private HashMap<Object,Integer> pichOnMap;//记录子项选择状态

    public HashMap<Object, Integer> getPichOnMap() {
        return pichOnMap;
    }

    public void setPichOnMap(HashMap<Object, Integer> pichOnMap) {
        this.pichOnMap = pichOnMap;
    }

    //构造方法
    public DizhibjAdapter(Context context,List<Dizhi> items){
        this.context=context;
        this.items=items;
        pichOnMap=new HashMap<Object,Integer>();
        //初始化选择框状态都为0
        for (int i=0;i<items.size();i++){
            pichOnMap.put(items.get(i).getId(),0);
        }
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
        view=View.inflate(context, R.layout.dizhibj_item,null);
        //实例化子项的控件
        TextView name;
        TextView phone;
        TextView diqu;
        TextView xdizhi;
        CheckBox rb_items;
        rb_items=view.findViewById(R.id.rb_items);
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
        //监听选择框
        rb_items.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rb_items.isChecked()){
                    //选择框被选中，将其状态记录为1
                    pichOnMap.put(items.get(i).getId(),1);

                }else {
                    pichOnMap.put(items.get(i).getId(),0);
                }
                 mrefreshInterface.refresh(pichOnMap);
            }
        });

        //改变选择框的显示
        if (pichOnMap.get(items.get(i).getId())==0){
            rb_items.setChecked(false);
        }else {
            rb_items.setChecked(true);
        }
        return view;
    }
    //定义接口
    public interface RefreshInterface{
        //把价格显示到总价上
        void refresh(HashMap<Object,Integer> pitchOnMap);
    }
    //定义一个接口对象
    private RefreshInterface mrefreshInterface;
    //
    public void setRefreshInterface(RefreshInterface refreshInterface){
        mrefreshInterface=refreshInterface;
    }
}
