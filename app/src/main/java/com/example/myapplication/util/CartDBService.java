package com.example.myapplication.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.entity.Goods;

import java.util.ArrayList;
import java.util.List;

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
        values.put("guige",goods.getGuige());
        //存入数据
        sqLiteDatabase.insert(TABLE_NAME,null,values);
        //关闭数据库
        sqLiteDatabase.close();
    }
    //查询所有的商品
    public List<Goods> getAllCart(){
        //打开数据库
        SQLiteDatabase sqLiteDatabase=dbOpenHelper.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.query(TABLE_NAME,null,null,null,null,null,null);
        List<Goods> goodsItem=new ArrayList<Goods>();
        while (cursor.moveToNext()){
            Goods goods=new Goods();
            goods.setId(cursor.getInt(0));
            goods.setName(cursor.getString(1));
            goods.setGuige(cursor.getString(2));
            goods.setPrice(cursor.getFloat(3));
            goods.setNum(cursor.getInt(4));
            goods.setImg(cursor.getInt(5));
            goodsItem.add(goods);

        }
       return goodsItem;
    }
    //删除选中商品
    public void deleteCart(int id){
        SQLiteDatabase sqLiteDatabase=dbOpenHelper.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,"_id=?",new String[]{String.valueOf(id)});
    }

}
