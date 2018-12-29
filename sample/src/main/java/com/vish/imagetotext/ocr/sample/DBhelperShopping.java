package com.vish.imagetotext.ocr.sample;

/**
 * Created by yejina on 2017-05-25.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelperShopping extends SQLiteOpenHelper {

    // TABLE INFORMATTION
    public static final String TABLE_SHOPPING = "item";
    public static final String SHOPPING_ID = "_id";
    public static final String SHOPPING_NAME = "name";

    // DATABASE INFORMATION
    static final String DB_NAME = "Shopping.DB";
    static final int DB_VERSION = 1;

    // TABLE CREATION STATEMENT
    private static final String CREATE_TABLE = "create table "
            + TABLE_SHOPPING + "(" + SHOPPING_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SHOPPING_NAME + " TEXT NOT NULL);";

    public DBhelperShopping(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPPING);
        onCreate(db);
    }
}