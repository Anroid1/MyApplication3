package com.example.myapplication.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.entity.Goods;

import java.util.ArrayList;
import java.util.List;

//购物车的增删改查
public class CartDBService {
    public  static final String TABLE_NAME="cart_db";
    public  static final String TABLE_NAMEA="liji_db";
    private DBOpenHelper dbOpenHelper;
    public CartDBService(Context context){
        dbOpenHelper=new DBOpenHelper(context);
    }
    //添加商品到购物车
    public void addCart(Goods goods){
        //打开数据库
        SQLiteDatabase sqLiteDatabase=dbOpenHelper.getWritableDatabase();
            String provinceName = goods.getName();

            //把该姓名作为查找条件
            Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[]{"name"}, "name = ?", new String[]{provinceName}, null, null, null);
            if (cursor.getCount() == 0){
                Log.d("123","加入"+provinceName+"到数据库中");
                //插入数据
                ContentValues values=new ContentValues();
                values.put("name",goods.getName());
                values.put("price",goods.getPrice());
                values.put("num",goods.getNum());
                values.put("img",goods.getImg());
                values.put("guige",goods.getGuige());
                sqLiteDatabase.insert(TABLE_NAME,null,values);
                sqLiteDatabase.close();
            }else {
                String name = goods.getName();
                ContentValues values = new ContentValues();
                values.put("num",goods.getNum());
                /**
                 * 数据的更新
                 * 参数一：要更新的数据所在的表名
                 * 参数二：新的数据
                 * 参数三：要更新数据的查找条件
                 * 参数四：条件的参数
                 */
                sqLiteDatabase.update(TABLE_NAME,values,"name=?",new String[]{name});
                sqLiteDatabase.close();
                Log.d("123", "数据库中已存在" + goods.getNum());
            }
        }
        //关闭数据库
    //添加商品到立即购买
    public void addCartA(Goods goods){
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
        sqLiteDatabase.insert(TABLE_NAMEA,null,values);
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
    //查询最后的商品
    public List<Goods> getLastCart(){
        //打开数据库
        SQLiteDatabase sqLiteDatabase=dbOpenHelper.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.query(TABLE_NAMEA,null,null,null,null,null,null);
        List<Goods> goodsItem=new ArrayList<Goods>();
        cursor.moveToLast();
        Goods goods=new Goods();
        goods.setId(cursor.getInt(0));
        goods.setName(cursor.getString(1));
        goods.setGuige(cursor.getString(2));
        goods.setPrice(cursor.getFloat(3));
        goods.setNum(cursor.getInt(4));
        goods.setImg(cursor.getInt(5));
        goodsItem.add(goods);
        return goodsItem;
    }
    //根据id查询商品详情
    public void queryCart(int id){
        SQLiteDatabase sqLiteDatabase=dbOpenHelper.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.query(TABLE_NAMEA,null,"_id=?",new String[]{String.valueOf(id)},null,null,null);
    }


    //删除选中商品
    public void deleteCart(int id){
        SQLiteDatabase sqLiteDatabase=dbOpenHelper.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,"_id=?",new String[]{String.valueOf(id)});
    }

}
