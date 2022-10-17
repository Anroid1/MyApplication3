package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class ShangpinActivity extends AppCompatActivity {
    CarouselView carouselView;
    int[] images = {
            R.drawable.lizi,
            R.drawable.lizi,
            R.drawable.lizi
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangpin);
        carouselView = findViewById(R.id.carouselView);
        //设置轮播图的页面数量
        carouselView.setPageCount(images.length);
        carouselView.setImageListener(imageListener);
        //点击事件
        /*carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {

            }
        });*/

    }
    ImageListener imageListener =new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(images[position]);
        }
    };
}