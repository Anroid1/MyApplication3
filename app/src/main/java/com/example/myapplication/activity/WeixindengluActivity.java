package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;

public class WeixindengluActivity extends AppCompatActivity {
    ImageView imgweixindenglu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixindenglu);

        //初始化控件
        imgweixindenglu =findViewById(R.id.weixindenglu);


        imgweixindenglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(WeixindengluActivity.this,WeixinshouquanyeActivity.class);
                startActivityForResult(intent1,1);
            }
        });


    }
}