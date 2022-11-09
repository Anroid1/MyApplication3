package com.example.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.entity.Goods;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

public class CartAdapter extends BaseAdapter {
    private Context context;//上下文
    private List<Goods> items;//数据源
    private HashMap<Object,Integer> pichOnMap;//记录子项选择状态

    public HashMap<Object, Integer> getPichOnMap() {
        return pichOnMap;
    }

    public void setPichOnMap(HashMap<Object, Integer> pichOnMap) {
        this.pichOnMap = pichOnMap;
    }

    //构造方法
    public CartAdapter(Context context,List<Goods> items){
        this.context=context;
        this.items=items;
        pichOnMap =new HashMap<Object,Integer>();
        //初始选择框状态为0
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=View.inflate(context, R.layout.gouwuche_items,null);
        //实例化子项控件
        CheckBox b1;
        ImageView iv_items;
        TextView tv_name_items,tv_price_items,tv_price_num,tv_price_guige,tv_price_fuhao;

        b1=view.findViewById(R.id.Mygouwuchexuanze);
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
        //监听选择器
        b1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (b1.isChecked()){
                    //选择框被选中，记录状态为1
                    pichOnMap.put(items.get(i).getId(),1);
                }else{
                    pichOnMap.put(items.get(i).getId(),0);
                }
                mjiPriceInterface.jiPrice(pichOnMap);

            }
        });
        //改变选择框的显示
        if(pichOnMap.get(items.get(i).getId())==0){
            b1.setChecked(false);
        }else {
            b1.setChecked(true);
        }
        return view;
    }
    //定义接口
    public interface JiPriceInterface{
        //把价格显示到总价上
        void jiPrice(HashMap<Object,Integer> pitchOnMap);
    }
    //定义一个接口对象
    private JiPriceInterface mjiPriceInterface;
    //向外部暴露一个方法——把价格显示到总价上
    public void setJiPriceInterface(JiPriceInterface jiPriceInterface){
        mjiPriceInterface=jiPriceInterface;
    }
}
