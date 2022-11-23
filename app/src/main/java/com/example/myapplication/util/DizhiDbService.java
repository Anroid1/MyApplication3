package com.example.myapplication.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.entity.Dizhi;
import com.example.myapplication.entity.Goods;
import com.example.myapplication.wode.AdddizhiActivity;

import java.util.ArrayList;
import java.util.List;

//地址的增删改查
public class DizhiDbService {
    final int CODE = 0x11;
    public static final String TABLE_NAME = "dizhi_db";
    private DBOpenHelper dbOpenHelper;

    public DizhiDbService(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
    }

    //添加地址
    public void addDizhi(Dizhi dizhi) {
        //打开数据库
        SQLiteDatabase sqLiteDatabase = dbOpenHelper.getWritableDatabase();
        //将对象封装为键值对
        ContentValues values = new ContentValues();
        values.put("name", dizhi.getName());
        values.put("phone", dizhi.getPhone());
        values.put("diqu", dizhi.getDiqu());
        values.put("xdizhi", dizhi.getXdizhi());
        //存入数据
        sqLiteDatabase.insert(TABLE_NAME, null, values);
        //关闭数据库
        sqLiteDatabase.close();
    }

    //查询所有商品
    public List<Dizhi> getAllDizhi() {
        //打开数据库
        SQLiteDatabase sqLiteDatabase = dbOpenHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null, null, null, null, null);
        List<Dizhi> dizhiItem = new ArrayList<Dizhi>();
        while (cursor.moveToNext()) {
            Dizhi dizhi = new Dizhi();
            dizhi.setId(cursor.getInt(0));
            dizhi.setName(cursor.getString(1));
            dizhi.setPhone(cursor.getString(2));
            dizhi.setDiqu(cursor.getString(3));
            dizhi.setXdizhi(cursor.getString(4));
            dizhiItem.add(dizhi);
        }
        return dizhiItem;
    }

    //修改地址
    public void update(Dizhi dizhi) {
        //打开数据库
        SQLiteDatabase sqLiteDatabase = dbOpenHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",dizhi.getName());
        values.put("phone",dizhi.getPhone());
        values.put("diqu",dizhi.getDiqu());
        values.put("xdizhi",dizhi.getXdizhi());
        int a=sqLiteDatabase.update("dizhi_db",values, "_id=?", new String[]{dizhi.getId()+""});
        Log.v("AAAAAAAAAAA","a="+a);
        Log.v("AAAAAAAAAAAA","id="+dizhi.getId());
        //关闭数据库
        sqLiteDatabase.close();
    }

    //删除地址
    public void deleteDizhi(int id) {
        SQLiteDatabase sqLiteDatabase = dbOpenHelper.getWritableDatabase();
        sqLiteDatabase.delete("dizhi_db","_id=?",new String[]{String.valueOf(id)});

    }

}
