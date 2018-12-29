package com.vish.imagetotext.ocr.sample;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.croppersample.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class saveActivity extends Activity {
    Button main_bt;
    Button addmem_bt;
    ListView lv;
    SQLController dbcon;
    TextView iID_tv, iName_tv,iExpiration_tv,iNum_tv;
    String data;
    long now;
    public String getSaveResult(){
        return data;
    }
    public void setSaveResult(String data){
        this.data = data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_activity);
        dbcon = new SQLController(this);
        dbcon.open();
        addmem_bt = (Button) findViewById(R.id.addi_bt_id);
        lv = (ListView) findViewById(R.id.itemList_id);
        main_bt=(Button)findViewById(R.id.main);
        Intent intent = getIntent();
        data = intent.getStringExtra("value");
        setSaveResult(data);

        // onClickListiner for addmember Button
        addmem_bt.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("tag",getDateTimeString());
                Intent add_mem = new Intent(saveActivity.this, main.class);
                add_mem.putExtra("data",data);
                startActivity(add_mem);
            }
        });

        // Attach The Data From DataBase Into ListView Using Crusor Adapter
        Cursor cursor = dbcon.readData();
        String[] from = new String[] { DBhelper.ITEM_ID, DBhelper.ITEM_NAME, DBhelper.ITEM_EXPIRATION, DBhelper.ITEM_NUM };
        int[] to = new int[] { R.id.item_id, R.id.item_name,R.id.item_expiration,R.id.item_num};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                saveActivity.this, R.layout.view_member_entry, cursor, from, to,0);

        adapter.notifyDataSetChanged();

        lv.setAdapter(adapter);

        // OnCLickListiner For List Items
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                iID_tv = (TextView) view.findViewById(R.id.item_id);
                iName_tv = (TextView) view.findViewById(R.id.item_name);
                iExpiration_tv=(TextView)view.findViewById(R.id.item_expiration);
                iNum_tv = (TextView)view.findViewById(R.id.item_num);
                String itemID_val = iID_tv.getText().toString();
                String itemName_val = iName_tv.getText().toString();
                String itemExpiration_val=iExpiration_tv.getText().toString();
                String itemNum_val = iNum_tv.getText().toString();
                Intent modify_intent = new Intent(getApplicationContext(),
                        Modify_item.class);
                modify_intent.putExtra("name", itemName_val);

                modify_intent.putExtra("expiration", itemExpiration_val);

                modify_intent.putExtra("num", itemNum_val);
                modify_intent.putExtra("_id", itemID_val);
                startActivity(modify_intent);
            }
        });
        main_bt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(saveActivity.this,Intro.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
            }
        });
        //  SimpleCursorAdapter adapter = new SimpleCursorAdapter(
        //        saveActivity.this, R.layout.view_member_entry, cursor, from, to,0);

        /*if(getDateTimeString() == "07:01"){
            Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_LONG).show();
            Toast.makeText(this, getDateString(), Toast.LENGTH_LONG).show();
            while(cursor.moveToNext()) {
                if (cursor.getString(2) == getDateString()) {
                    Intent check_intent = new Intent(saveActivity.this, ShoppingActivity.class);
                    check_intent.putExtra("checkdate", cursor.getString(1));
                }
            }
        }*/
    } // create method end


    public String getDateString(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String str_date = df.format(new Date(now));
        return str_date;
    }
    public String getDateTimeString(){
        now  = System.currentTimeMillis();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        String str_time = df.format(new Date(now));
        return str_time;
    }

}// class end