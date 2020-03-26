package com.example.amigcq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static android.R.layout.simple_list_item_1;

public class multiple_logins  extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayAdapter adp;
    private Spinner sp;
    TextView txt;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.multiple_logins);

        txt=(TextView)findViewById(R.id.adminln);
        txt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(multiple_logins.this,a_login.class);
                startActivity(intent);
                return false;
            }
        });




        sp=findViewById(R.id.optionspinner);
        sp.setOnItemSelectedListener(this);
        ArrayList<String> categories = new ArrayList<>();
        categories.add(0,"Choose Login");
        categories.add("Student");
        categories.add("Teacher");
        adp = new ArrayAdapter(this, simple_list_item_1,categories);
        adp.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp.setAdapter(adp);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getItemAtPosition(position).equals("Choose Login")){

        }
        else{
            String item =parent.getItemAtPosition(position).toString();

            Toast.makeText(parent.getContext(), "Selected", Toast.LENGTH_SHORT).show();

            if (parent.getItemAtPosition(position).equals("Student")){
                Intent intent = new Intent(multiple_logins.this,s_login.class);
                startActivity(intent);

            }
            if (parent.getItemAtPosition(position).equals("Teacher")){
                Intent intent = new Intent(multiple_logins.this,t_login.class);
                startActivity(intent);

            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
