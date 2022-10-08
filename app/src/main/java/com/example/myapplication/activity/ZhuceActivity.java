package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;

public class ZhuceActivity extends AppCompatActivity {
    EditText editText1,editText2,editText3;
    ImageView imgzhuce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);

        //初始化控件
        editText1 =findViewById(R.id.sjh);
        editText2 =findViewById(R.id.mm);
        editText3 =findViewById(R.id.shuruyanzhengma);
        imgzhuce =findViewById(R.id.bu_zhuce);


        imgzhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone=editText1.getText().toString();
                String p =  editText2.getText().toString();
                String y =  editText3.getText().toString();

                if(editText1.length() == 0){
                    Toast.makeText(ZhuceActivity.this,"账号不能为空",Toast.LENGTH_SHORT).show();
                }
                else if(4 >=editText1.length()){
                    Toast.makeText(ZhuceActivity.this,"账号长度应在4-18字之间",Toast.LENGTH_SHORT).show();
                }
                //这里比较两个密码是否相同，不能用== 或者!=来判断string，string是引用类型的
                //不是基本数据类型，所以它们比较使用地址和值（相当于C的指针）
                //来比较的，所以 str == str2永远是false，那么就应该将Text取出比较
                //getText得到内容
                //toString以str形式
                //trim可以删去前后的空格
                //如果两部分相等则等于1，注册成功，关掉页面回到登陆页面

                Intent intent=new Intent();
                intent.putExtra("phone",phone);
                intent.putExtra("p",p);
                intent.putExtra("y",y);
                ZhuceActivity.this.setResult(3,intent);
                ZhuceActivity.this.finish();
            }
        });



    }
}