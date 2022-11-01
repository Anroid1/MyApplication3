package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.content.BroadcastReceiver;
import com.example.myapplication.R;

public class GbActivity extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg=intent.getStringExtra("message");
        Toast.makeText(context,"验证码为"+msg,Toast.LENGTH_SHORT).show();
    }
}