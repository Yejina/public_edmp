package com.vish.imagetotext.ocr.sample;

/**
 * Created by yejina on 2017-05-25.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.example.croppersample.R;
public class Modify_item extends Activity implements OnClickListener {

    final Context context = this;
    EditText etname,etExpiration,etNum;
    Button edit_bt, delete_bt;

    long member_id;

    SQLController dbcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_member);

        dbcon = new SQLController(this);
        dbcon.open();

        etname = (EditText) findViewById(R.id.edit_item_id);
        etExpiration=(EditText)findViewById(R.id.edit_item_expiration) ;

        etNum=(EditText)findViewById(R.id.edit_item_num) ;
        edit_bt = (Button) findViewById(R.id.update_bt_id);
        delete_bt = (Button) findViewById(R.id.delete_bt_id);

        Intent i = getIntent();
        String iID = i.getStringExtra("_id");
        String iName = i.getStringExtra("name");
        String iExpiration=i.getStringExtra("expiration");
        String iNum=i.getStringExtra("num");

        member_id = Long.parseLong(iID);

        etname.setText(iName);
        etExpiration.setText(iExpiration);
        etNum.setText(iNum);

        edit_bt.setOnClickListener(this);
        delete_bt.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.update_bt_id:
                String memName_upd = etname.getText().toString();
                String memExpiration_upd=etExpiration.getText().toString();
                String memNum_upd=etNum.getText().toString();
                dbcon.updateData(member_id, memName_upd, memExpiration_upd, memNum_upd);
                this.returnHome();
                break;

            case R.id.delete_bt_id:

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("정말 삭제하시겠습니까?").setCancelable(false)
                        .setPositiveButton("삭제.", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dbcon.deleteData(member_id);
                                Intent home_intent = new Intent(getApplicationContext(),
                                        saveActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                startActivity(home_intent);
                                //AlertDialogActivity.this.finish();
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

                break;
        }
    }

    public void returnHome() {

        Intent home_intent = new Intent(getApplicationContext(),
                saveActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(home_intent);
    }

}