package com.vish.imagetotext.ocr.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.view.View.OnClickListener;
import com.example.croppersample.R;

/**
 * Created by yejina on 2017-05-26.
 */

public class delete_shopping extends Activity implements OnClickListener {
    Button delete_bt;

    long shopping_id;

    SQLControllerShopping dbconsh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_shopping);

        dbconsh = new SQLControllerShopping(this);
        dbconsh.open();

        delete_bt = (Button)findViewById(R.id.delete_bt_id);
        Intent i = getIntent();
        String shID = i.getStringExtra("_id");
        String shName = i.getStringExtra("name");

        shopping_id = Long.parseLong(shID);
        delete_bt.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete_bt_id:
                dbconsh.deleteData(shopping_id);
                this.returnHome();
                break;


        }
    }
    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(),
                ShoppingActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
