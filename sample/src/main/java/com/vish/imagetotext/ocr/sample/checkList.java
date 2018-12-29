package com.vish.imagetotext.ocr.sample;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.croppersample.R;

/**
 * Created by yejina on 2017-05-27.
 */

public class checkList extends Activity{
    Button bt;
    ListView lv;
    SQLController dbcon;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_list);

        dbcon = new SQLController(this);
        dbcon.open();
        lv = (ListView) findViewById(R.id.check_list);
        bt = (Button)findViewById(R.id.main);

        cursor = dbcon.readData();
        String[] from = new String[] { DBhelper.ITEM_ID, DBhelper.ITEM_NAME, DBhelper.ITEM_EXPIRATION, DBhelper.ITEM_NUM };
        int[] to = new int[] { R.id.item_id, R.id.item_name,R.id.item_expiration,R.id.item_num};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                checkList.this, R.layout.view_member_entry, cursor, from, to,0);
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(checkList.this,Intro.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
            }
        });
    }
}