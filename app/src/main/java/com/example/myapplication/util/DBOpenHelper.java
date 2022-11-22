package com.example.myapplication.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;

import androidx.annotation.Nullable;

import com.example.myapplication.entity.User;

import java.util.ArrayList;

//操作SQLite数据库基础类
public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="fruits.db";
    public DBOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //创建数据表
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS cart_db(_id integer primary key autoincrement," +
                "name text,guige text,price float,num integer,img long)");
        //创建登录注册的数据表
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS user_db(_id integer primary key autoincrement," +
                "name text,phone text,password text,sex text,img long)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}