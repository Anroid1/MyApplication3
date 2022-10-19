package com.example.myapplication.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.entity.Goods;

//购物车的增删改查
public class CartDBService {
    public  static final String TABLE_NAME="cart_db";
    private DBOpenHelper dbOpenHelper;
    public CartDBService(Context context){
        dbOpenHelper=new DBOpenHelper(context);
    }
    //添加商品到购物车
    public void addCart(Goods goods){
        //打开数据库
        SQLiteDatabase sqLiteDatabase=dbOpenHelper.getWritableDatabase();
        //将对象封装为键值对
        ContentValues values=new ContentValues();
        values.put("name",goods.getName());
        values.put("price",goods.getPrice());
        values.put("num",goods.getNum());
        values.put("img",goods.getImg());
        //存入数据
        sqLiteDatabase.insert(TABLE_NAME,null,values);
        //关闭数据库
        sqLiteDatabase.close();
    }
}
