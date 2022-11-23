package com.example.myapplication.util;
/*
操作数据订单信息
 */

import static com.example.myapplication.util.CartDBService.TABLE_NAMEA;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.entity.Goods;

import java.util.ArrayList;
import java.util.Date;

public class OrderDbService {

    private DBOpenHelper dbOpenHelper;
private static final String TABLE_NAME="cart_db";
int order_id;//订单编号
    public OrderDbService(Context context){
        dbOpenHelper=new DBOpenHelper((context));
        order_id=0;
    }
    //下单
    public void addCart(ArrayList<Goods> goodsList,float totalPrice){
        //打开数据库
        SQLiteDatabase sqLiteDatabase=dbOpenHelper.getWritableDatabase();
        //存入订单表
        ContentValues values=new ContentValues();
        values.put("otime",new Date()+"");
        values.put("totalPrice",totalPrice);
        values.put("onum",goodsList.size());
        //存入数据
        sqLiteDatabase.insert("order_db",null,values);
        order_id++;
        //存入订单详情
for(int i=0;i<goodsList.size();i++){
    ContentValues values1=new ContentValues();
    values.put("oid",order_id);
    values.put("name",goodsList.get(i).getName());
    values.put("price",goodsList.get(i).getPrice());
    values.put("img",goodsList.get(i).getImg());
    values.put("num",goodsList.get(i).getNum());
    sqLiteDatabase.insert("orderDetail_db",null,values1);

}
        //关闭数据库
        sqLiteDatabase.close();
    }

}
