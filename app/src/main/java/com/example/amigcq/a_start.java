package com.example.amigcq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class a_start extends AppCompatActivity {
    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.admin_start);

        btn1=(Button)findViewById(R.id.ad_stu_start_btn);
        btn2=(Button)findViewById(R.id.ad_teach_start_btn);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(a_start.this, a_stu_notify.class);
                startActivity(intent);

            }
        });



    }
}