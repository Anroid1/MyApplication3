package com.example.myapplication.wode;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import com.example.myapplication.R;
public class GerenxinxiActivity extends AppCompatActivity {
    EditText editText1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenxinxi);



        Intent intent=getIntent();
        String phone=intent.getStringExtra("phone");
        editText1=findViewById(R.id.phone1);
        editText1.setText(phone);
    }
}