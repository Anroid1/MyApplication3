package com.example.myapplication.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CartAdapter;
import com.example.myapplication.entity.Goods;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

public class LiJiAdapter extends BaseAdapter {
    private Context context;//上下文
    private List<Goods> items;//数据源
    //构造方法
    public LiJiAdapter(Context context,List<Goods> items){
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=View.inflate(context, R.layout.comfire_items,null);
        //实例化子项控件
        ImageView iv_items;
        TextView tv_name_items,tv_price_items,tv_price_num,tv_price_guige,tv_price_fuhao;

        iv_items=view.findViewById(R.id.iv_img);
        tv_name_items=view.findViewById(R.id.tv_name);
        tv_price_items=view.findViewById(R.id.jiage);
        tv_price_num=view.findViewById(R.id.tv_shuliang);
        tv_price_guige=view.findViewById(R.id.tv_desc);

        //数据装载到控件
        Goods goods=items.get(i);
        iv_items.setImageResource(goods.getImg());
        tv_name_items.setText(goods.getName());
        DecimalFormat decimalFormat=new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        tv_price_items.setText(decimalFormat.format(goods.getPrice()));//format 返回的是字符串format()用于格式化方法，即用来控制字符串和变量的显示效果
        tv_price_num.setText(goods.getNum()+"");
        tv_price_guige.setText(goods.getGuige());
        return view;
    }
}
