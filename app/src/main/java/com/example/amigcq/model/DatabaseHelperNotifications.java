package com.example.amigcq.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import com.example.amigcq.MyMessagingService;
import com.example.amigcq.model.DatabaseContract;

import java.util.ArrayList;

public class DatabaseHelperNotifications extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String KEY_ID = DatabaseContract.DatabaseTable.COLUMN_NAME_KEY_ID;
    private static final String TITLE = DatabaseContract.DatabaseTable.COLUMN_NAME_TITLE;
    private static final String BODY = DatabaseContract.DatabaseTable.COLUMN_NAME_BODY;

    private static final String CREATE_TABLE_NOTIFICATIONS = "CREATE TABLE "
            + DatabaseContract.DatabaseTable.TABLE_NAME + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            TITLE + " TEXT NOT NULL, "+
            BODY + " TEXT NOT NULL );";


    public DatabaseHelperNotifications(Context context) {
        super(context, DatabaseContract.DatabaseTable.TABLE_NAME, null, DATABASE_VERSION);
    }

    public long addNotification (String title, String body){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.DatabaseTable.COLUMN_NAME_TITLE,title);
        values.put(DatabaseContract.DatabaseTable.COLUMN_NAME_BODY,body);

        long insert = db.insert(DatabaseContract.DatabaseTable.TABLE_NAME,null,values);

        return insert;
    }

    public ArrayList<NotificationModel> getAllNotifications(){
        ArrayList<NotificationModel> notifications = new ArrayList<NotificationModel>();

        String selectQuery = "SELECT * FROM " + DatabaseContract.DatabaseTable.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(selectQuery,null);

        if(c.moveToFirst())
        {
            do{
               NotificationModel newNotification = new NotificationModel();
               newNotification.setTitle(c.getString(c.getColumnIndex(TITLE)));
               newNotification.setBody(c.getString(c.getColumnIndex(BODY)));

               notifications.add(newNotification);
            } while (c.moveToNext());
        }

        return notifications;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_NOTIFICATIONS);
        } catch (SQLException e){
            System.out.println("error");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + DatabaseContract.DatabaseTable.TABLE_NAME + "'");
        onCreate(db);
    }
}
