package com.example.myapplication.util;
/*
操作数据订单信息
 */

import android.content.Context;

import com.example.myapplication.entity.Goods;

import java.util.ArrayList;

public class OrderDbService {
    private DBOpenHelp dbOpenHelp;

    OrderDbService(Context context){
        dbOpenHelp=new DBOpenHelp((context));
    }
    //下单

}
