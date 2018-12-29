package com.vish.imagetotext.ocr.sample;

/**
 * Created by SooBin on 2017-05-25.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLController {

    private DBhelper dbhelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    public SQLController(Context c) {
        ourcontext = c;
    }

    public SQLController open() throws SQLException {
        dbhelper = new DBhelper(ourcontext);
        database = dbhelper.getWritableDatabase();
        return this;

    }

    public void close() {
        dbhelper.close();
    }

    //Inserting Data into table
    public void insertData(String name, String expiration, String num) {
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.ITEM_NAME, name);
        cv.put(DBhelper.ITEM_EXPIRATION,expiration);
        cv.put(DBhelper.ITEM_NUM,num);
        database.insert(DBhelper.TABLE_ITEM, null, cv);
    }

    //Getting Cursor to read data from table
    public Cursor readData() {
        String[] allColumns = new String[] { DBhelper.ITEM_ID,
                DBhelper.ITEM_NAME , DBhelper.ITEM_EXPIRATION, DBhelper.ITEM_NUM };
        Cursor c = database.query(DBhelper.TABLE_ITEM, allColumns, null,
                null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    //Updating record data into table by id
    public int updateData(long itemID, String itemName,String itemExpiration,String itemNum) {
        ContentValues cvUpdate = new ContentValues();
        cvUpdate.put(DBhelper.ITEM_NAME, itemName);
        cvUpdate.put(DBhelper.ITEM_EXPIRATION,itemExpiration);
        cvUpdate.put(DBhelper.ITEM_NUM,itemNum);
        int i = database.update(DBhelper.TABLE_ITEM, cvUpdate,
                DBhelper.ITEM_ID + " = " + itemID , null);
        return i;
    }

    // Deleting record data from table by id
    public void deleteData(long memberID) {
        database.delete(DBhelper.TABLE_ITEM, DBhelper.ITEM_ID + "="
                + memberID, null);
    }

}