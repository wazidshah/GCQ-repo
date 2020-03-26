package com.example.amigcq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class a_stu_notify extends AppCompatActivity {
    Button btn1;
    private ListView listView;
    private DatabaseReference mdata;
    private ArrayList<String> arrayList=new ArrayList<>();
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.admin_stu);

        mdata= FirebaseDatabase.getInstance().getReference().child("student").child("stud");

        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, arrayList);

        btn1=(Button)findViewById(R.id.sendnotificationpage);
        listView=(ListView)findViewById(R.id.database_list_view) ;

        listView.setAdapter(adapter);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(a_stu_notify.this, a_stu_notification.class);
                startActivity(intent);
            }
        });

        mdata.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String string =dataSnapshot.getValue(String.class);
                //Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                //Log.d(TAG, "Value is: " + map);
                /*String myString = ds.child("food").getValue(String.class));*/
                arrayList.add(string);
                adapter.notifyDataSetChanged();
                /*for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    String myString = ds.child("student").getValue(String.class);
                    arrayList.add(myString);
                    adapter.notifyDataSetChanged();

                    // print myString or add to list..
                }*/


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                String string =dataSnapshot.getValue(String.class);
                arrayList.remove(string);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
