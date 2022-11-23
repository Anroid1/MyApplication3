package com.example.myapplication.gouwuche;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.activity.MainActivity;

public class JSchenggongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jschenggong);
        Button fanhuishou = findViewById(R.id.fanhuishou);
        fanhuishou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(JSchenggongActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}