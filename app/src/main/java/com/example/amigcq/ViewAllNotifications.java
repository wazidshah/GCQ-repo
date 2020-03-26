package com.example.amigcq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.amigcq.model.DatabaseHelperNotifications;
import com.example.amigcq.model.NotificationModel;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class ViewAllNotifications extends AppCompatActivity {

    private List<NotificationModel> notifications;
    private RecyclerView recyclerView;
    private CustomAdaptor adaptor;
    private DatabaseHelperNotifications database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_notifications);
        recyclerView = (RecyclerView) findViewById(R.id.all_notifications_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database = new DatabaseHelperNotifications(this);
        notifications = database.getAllNotifications();
        System.out.println(Arrays.toString(notifications.toArray()));
        adaptor = new CustomAdaptor(ViewAllNotifications.this,notifications);
        recyclerView.setAdapter(adaptor);
    }
}
