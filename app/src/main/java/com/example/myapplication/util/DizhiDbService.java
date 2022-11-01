package com.example.myapplication.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.entity.Dizhi;
import com.example.myapplication.entity.Goods;

import java.util.ArrayList;
import java.util.List;

//地址的增删改查
public class DizhiDbService {
    public  static final String TABLE_NAME="dizhi_db";
    private DBOpenHelp dbOpenHelper;
    public DizhiDbService(Context context){
        dbOpenHelper=new DBOpenHelp(context);
    }
//添加地址
    public void addDizhi(Dizhi dizhi){
        //打开数据库
        SQLiteDatabase sqLiteDatabase=dbOpenHelper.getWritableDatabase();
        //将对象封装为键值对
        ContentValues values=new ContentValues();
        values.put("name",dizhi.getName());
        values.put("phone",dizhi.getPhone());
        values.put("diqu",dizhi.getDiqu());
        values.put("xdizhi",dizhi.getXdizhi());
        //存入数据
        sqLiteDatabase.insert(TABLE_NAME,null,values);
        //关闭数据库
        sqLiteDatabase.close();

    }
    //查询所有商品
    public List<Dizhi> getAllDizhi(){
        //打开数据库
        SQLiteDatabase sqLiteDatabase=dbOpenHelper.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.query(TABLE_NAME,null,null,null,null,null,null);
        List<Dizhi> dizhiItem=new ArrayList<Dizhi>();
        while (cursor.moveToNext()){
            Dizhi dizhi=new Dizhi();
            dizhi.setId(cursor.getInt(0));
            dizhi.setName(cursor.getString(1));
            dizhi.setPhone(cursor.getString(2));
            dizhi.setDiqu(cursor.getString(3));
            dizhi.setXdizhi(cursor.getString(4));
            dizhiItem.add(dizhi);

        }
        return dizhiItem;
    }
}
