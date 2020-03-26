package com.example.amigcq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class a_login extends AppCompatActivity {
    Button btn;
    EditText adid,adpass;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.admin_login);

        adid=(EditText)findViewById(R.id.adusername);
        adpass=(EditText)findViewById(R.id.adpassowrd);


        btn=(Button)findViewById(R.id.btn_ad_login);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = adid.getText().toString().trim();
                String userp = adpass.getText().toString().trim();
                String a="sankalp_devil";
                String b="black2007";

                if(user.equals(a) && userp.equals(b)){


                    Intent intent = new Intent(a_login.this, a_start.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(a_login.this, "Admin User Id & password is not Correct", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}