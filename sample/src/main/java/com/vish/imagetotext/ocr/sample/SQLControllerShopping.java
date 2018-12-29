package com.vish.imagetotext.ocr.sample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by yejina on 2017-05-26.
 */

public class SQLControllerShopping {
    private DBhelperShopping dBhelperShopping;
    private Context context;
    private SQLiteDatabase db;

    public SQLControllerShopping(Context c){context = c;}

    public SQLControllerShopping open() throws SQLException{
        dBhelperShopping=new DBhelperShopping(context);
        db=dBhelperShopping.getWritableDatabase();
        return this;
    }
    public void close(){ dBhelperShopping.close();}

    public Cursor readData(){
        String[] allColumns = new String[] {DBhelperShopping.SHOPPING_ID,DBhelperShopping.SHOPPING_NAME};
        Cursor c = db.query(DBhelperShopping.TABLE_SHOPPING,allColumns,null,
                null,null,null,null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
    public void deleteData(long shoppingID){
        db.delete(DBhelperShopping.TABLE_SHOPPING,DBhelperShopping.SHOPPING_ID + "="+shoppingID,null);
    }
    public void insertData(String name){
        ContentValues cv = new ContentValues();
        cv.put(DBhelperShopping.SHOPPING_NAME,name);
        db.insert(DBhelperShopping.TABLE_SHOPPING,null,cv);
    }
}
/*
 //Inserting Data into table
    public void insertData(String name, String expiration, String num) {
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.ITEM_NAME, name);
        cv.put(DBhelper.ITEM_EXPIRATION,expiration);
        cv.put(DBhelper.ITEM_NUM,num);
        database.insert(DBhelper.TABLE_ITEM, null, cv);
    }
*/