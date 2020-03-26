package com.example.amigcq;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.amigcq.model.DatabaseHelperNotifications;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyMessagingService extends FirebaseMessagingService {

    DatabaseHelperNotifications databaseHelper;
    public long success;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        databaseHelper = new DatabaseHelperNotifications(this);
        success = databaseHelper.addNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());

        if(success == -1) {System.out.println("insert not completed");}
        else {System.out.println("Inserted at"+ success);}
    }

    public void showNotification(String title,String message){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"MyNotifications")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setAutoCancel(true)
                .setContentText(message);

        NotificationManagerCompat manager  = NotificationManagerCompat.from(this);
        manager.notify(999,builder.build());


    }
}
