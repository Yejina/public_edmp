package com.vish.imagetotext.ocr.sample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by YEJINA1202 on 2017-05-06.
 */

public class DBhelper extends SQLiteOpenHelper {

    // TABLE INFORMATTION
    public static final String TABLE_ITEM = "item";
    public static final String ITEM_ID = "_id";
    public static final String ITEM_NAME = "name";
    public static final String ITEM_EXPIRATION = "expiration";
    public static final String ITEM_NUM = "num";


    // DATABASE INFORMATION
    static final String DB_NAME = "MEMBER.DB";
    static final int DB_VERSION = 1;

    // TABLE CREATION STATEMENT
    private static final String CREATE_TABLE = "create table "
            + TABLE_ITEM + "(" + ITEM_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ITEM_NAME + " TEXT," + ITEM_EXPIRATION + " TEXT, "+ITEM_NUM + " TEXT NOT NULL);";

    public DBhelper(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
        onCreate(db);
    }
}