package com.example.myapplication.wode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

public class DizhiActivity extends AppCompatActivity {
    Button dzb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dizhi);
        dzb=findViewById(R.id.dzb);
        dzb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DizhiActivity.this,AdddizhiActivity.class);
                startActivity(intent);
            }
        });


    }
}