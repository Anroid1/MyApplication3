package com.example.myapplication.wode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.R;

public class AdddizhiActivity extends AppCompatActivity {
ImageView adI1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddizhi);
        adI1=findViewById(R.id.adI1);
        adI1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdddizhiActivity.this,DizhiActivity.class);
                startActivity(intent);
            }
        });
    }
}