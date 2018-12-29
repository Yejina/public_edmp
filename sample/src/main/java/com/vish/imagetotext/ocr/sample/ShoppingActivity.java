package com.vish.imagetotext.ocr.sample;


        import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.croppersample.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yejina on 2017-05-26.
 */

public class ShoppingActivity extends Activity {

    final Context context = this;
    ListView lv;
    SQLControllerShopping dbconsh;
    TextView shopID_tv, shopName_tv;
    Button bt,main_bt;
    String checkDate;
    String shopID_val;
    long shopping_id;
    SimpleCursorAdapter adapter;
    Cursor cursorShop;
    SQLController dbcon;
    Cursor cursor;
    String a;
    long now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_list);
        dbcon = new SQLController(this);
        dbcon.open();
        dbconsh = new SQLControllerShopping(this);
        dbconsh.open();
        lv = (ListView) findViewById(R.id.shopping_list);
        bt = (Button)findViewById(R.id.shopping_button);

        main_bt = (Button)findViewById(R.id.main);
        cursorShop = dbconsh.readData();
        String[] from = new String[]{DBhelperShopping.SHOPPING_ID, DBhelperShopping.SHOPPING_NAME};
        int[] to = new int[]{R.id.shopping_id, R.id.shopping_name};

        adapter = new SimpleCursorAdapter(
                ShoppingActivity.this, R.layout.shopping_view, cursorShop, from, to, 0);
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                shopID_tv = (TextView) view.findViewById(R.id.shopping_id);
                shopName_tv = (TextView) view.findViewById(R.id.shopping_name);
                shopID_val = shopID_tv.getText().toString();
                String shopName_val = shopName_tv.getText().toString();

                shopping_id = Long.parseLong(shopID_val);/*
                Intent delete_intent = new Intent(getApplicationContext(),
                        delete_shopping.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                delete_intent.putExtra("name", shopName_val);
                delete_intent.putExtra("_id", shopID_val);*/

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("삭제하시겠습니까?").setCancelable(false)
                        .setPositiveButton("삭제.", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // 프로그램을 종료한다
                                //AlertDialogActivity.this.finish();

                                dbconsh.deleteData(shopping_id);
                                Intent home_intent = new Intent(getApplicationContext(),
                                        ShoppingActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(home_intent);
                            }
                        })
                        .setNegativeButton("취소.",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        // 다이얼로그를 취소한다
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                // 다이얼로그 보여주기
                alertDialog.show();
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(getDateTimeString().equals("06:01")) {
                cursor = dbcon.readData();
                cursorShop = dbconsh.readData();
                int num = cursorShop.getCount();

                cursor.moveToFirst();
                do {
                    if (cursor.getString(2).equals(getDateString())) {
                        String data="";
                        cursorShop.moveToFirst();
                        if (num == 0) {
                            //while(!(cursorShop.moveToNext())){
                            a = cursor.getString(1);
                            dbconsh.insertData(a);
                            Intent home_intent = new Intent(getApplicationContext(),
                                    ShoppingActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(home_intent);
                            data = "ok";
                            //break;
                        } else {
                            if ((cursorShop.getString(1).equals(cursor.getString(1)))) {
                                data = "ok";
                            }
                            while (cursorShop.moveToNext()) {
                                //while(num!=0){
                                if ((cursorShop.getString(1).equals(cursor.getString(1)))) {
                                    data = "ok";
                                    break;
                                }/*else{
                                data="";
                            }*/
                            }
                            if ((data.equals(""))) {
                                a = cursor.getString(1);
                                dbconsh.insertData(a);
                                Intent home_intent = new Intent(getApplicationContext(),
                                        ShoppingActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(home_intent);
                            }
                        }
                    }
                }while (cursor.moveToNext()) ;

                //}
            }
        });
        main_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(ShoppingActivity.this,Intro.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
            }
        });


    }
    public String getDateString(){
        now  = System.currentTimeMillis();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd",java.util.Locale.getDefault());

        String str_date = df.format(new Date(now));
        return str_date;
    }
    public String getDateTimeString(){
        now  = System.currentTimeMillis();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        String str_time = df.format(new Date(now));
        return str_time;
    }
}
