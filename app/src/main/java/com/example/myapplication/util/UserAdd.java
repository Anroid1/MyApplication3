package com.example.myapplication.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdd {
    public  static final String TABLE_NAME="user_db";
    private DBOpenHelper dbOpenHelper;
    public UserAdd(Context context){
        dbOpenHelper=new DBOpenHelper(context);
    }


    //添加用户
    public void addUser(User user){
        //打开数据库
        SQLiteDatabase sqLiteDatabase=dbOpenHelper.getWritableDatabase();
        //将对象封装为键值对
        ContentValues values=new ContentValues();
        values.put("phone",user.getPhone());
        values.put("password",user.getPassword());
        //存入数据
        sqLiteDatabase.insert(TABLE_NAME,null,values);
        //关闭数据库
        sqLiteDatabase.close();
    }
    public List<User> getUser(String phone){
        //打开数据库
        SQLiteDatabase sqLiteDatabase=dbOpenHelper.getWritableDatabase();
        String arg[]=new String[1];
        arg[0]=phone;
        Cursor cursor=sqLiteDatabase.query(TABLE_NAME,null,"phone=?",arg,null,null,null);
        List<User> userItem=new ArrayList<User>();
        while (cursor.moveToNext()){
            User user=new User();
            user.setId(cursor.getInt(0));
            user.setPhone(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setSex(cursor.getString(3));
            user.setImg(cursor.getInt(4));
            user.setName(cursor.getString(5));
            userItem.add(user);
        }
        return userItem;
    }
    //修改个人信息
    public void update1(User user) {
        //打开数据库
        SQLiteDatabase sqLiteDatabase = dbOpenHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",user.getName());
        values.put("phone",user.getPhone());
        values.put("password",user.getPassword());
        int a=sqLiteDatabase.update("user_db",values, "phone=?", new String[]{user.getPhone()+""});
        Log.v("AAAAAAAAAAA","a="+a);
        Log.v("AAAAAAAAAAAA","Name="+user.getName());
        Log.v("AAAAAAAAAAAA","phone="+user.getPhone());
        Log.v("AAAAAAAAAAAA","ps="+user.getPassword());

        //关闭数据库
        sqLiteDatabase.close();
    }
    //修改密码
    public void updatemima(User user) {
        //打开数据库
        SQLiteDatabase sqLiteDatabase = dbOpenHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("phone",user.getPhone());
        values.put("password",user.getPassword());
        int a=sqLiteDatabase.update("user_db",values, "phone=?", new String[]{user.getPhone()+""});
        Log.v("AAAAAAAAAAA","a="+a);
        Log.v("AAAAAAAAAAAA","phone="+user.getPhone());
        Log.v("AAAAAAAAAAAA","password="+user.getPassword());
        //关闭数据库
        sqLiteDatabase.close();
    }
    public boolean login(String phone,String password){
        SQLiteDatabase sdb=dbOpenHelper.getReadableDatabase();
        String sql="select * from user_db where phone=? and password=?";
        Cursor cursor=sdb.rawQuery(sql,new String[]{phone,password});
        //Log.v("AAAAAAAAAAA","phone="+phone);
       // Log.v("AAAAAAAAAAA","pass="+password);
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }



}
