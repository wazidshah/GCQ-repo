package com.example.amigcq.model;

import android.provider.BaseColumns;

public class DatabaseContract {

    private DatabaseContract(){}

    public static class DatabaseTable implements BaseColumns{
        public static final String TABLE_NAME = "notifications";
        public static final String COLUMN_NAME_KEY_ID = "id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_BODY = "body";
    }
}
