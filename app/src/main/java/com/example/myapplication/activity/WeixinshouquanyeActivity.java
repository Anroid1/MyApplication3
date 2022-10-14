package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.R;

public class WeixinshouquanyeActivity extends AppCompatActivity {
    ImageView imgweixinshouquanye;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixinshouquanye);

        //初始化控件
        imgweixinshouquanye =findViewById(R.id.weixindenglu);


        imgweixinshouquanye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(WeixinshouquanyeActivity.this,MainActivity.class);
                startActivityForResult(intent1,1);
            }
        });




    }
}