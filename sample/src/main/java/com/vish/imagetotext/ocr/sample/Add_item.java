package com.vish.imagetotext.ocr.sample;

/**
 * Created by yejina on 2017-05-25.
 */

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.croppersample.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Add_item extends Activity implements OnClickListener {
    EditText etname,etnum;
    TextView tvExpiration;
    Button add_bt, read_bt;
    SQLController dbcon;
    String result;
    String[] date;
    String new_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
        etname = (EditText) findViewById(R.id.item_et_name);
        etnum =(EditText)findViewById(R.id.item_et_num);
        tvExpiration = (TextView)findViewById(R.id.item_tv_expiration);
        add_bt = (Button) findViewById(R.id.add_bt_item);
        Intent intent = getIntent();
        result = intent.getStringExtra("value");

        new_data = "";
        if(result.contains("/")) {
            date = result.split("/");
            if(date[0].length() < 4) new_data += "20";

            for (int i = 0; i < date.length; i++) {
                new_data += date[i];
                if(i != date.length -1) new_data += "-";
            }

        } else if(result.contains(".")){
//            date = result.split(".");
//            if(date[0].length() < 4) new_data += "20";
//
//            for (int i = 0; i < date.length; i++) {
//                new_data += date[i];
//                if(i != date.length -1) new_data += "-";
//            }
            new_data = result.substring(0,4);
            new_data += "-";
            new_data += result.substring(5,7);
            new_data += "-";
            new_data += result.substring(8,10);
        } else if(result.contains("-")) {
            date = result.split("-");
            if(date[0].length() < 4) new_data += "20";

            for (int i = 0; i < date.length; i++) {
                new_data += date[i];
                if(i != date.length -1) new_data += "-";
            }

        } else {
            if(result.length() == 8){
                new_data = result.substring(0,4);
                new_data += "-";
                new_data += result.substring(4,6);
                new_data += "-";
                new_data += result.substring(6,8);

            } else if(result.length() == 6) {
                new_data = "20";
                new_data += result.substring(0,2);
                new_data += "-";
                new_data += result.substring(2,4);
                new_data += "-";
                new_data += result.substring(4,6);
            }
        }
        tvExpiration.setText(new_data); //
        //tvExpiration.setText(result);
        dbcon = new SQLController(this);
        dbcon.open();
        tvExpiration.setOnClickListener(this);
        add_bt.setOnClickListener(this);

       /* DatePickerDialog dialog = new DatePickerDialog(this, listener, 2013, 10, 22);
        dialog.show();*/
    }

    /*private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Toast.makeText(getApplicationContext(), year + "년" + monthOfYear + "월" + dayOfMonth +"일", Toast.LENGTH_SHORT).show();
        }
    };*/

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.add_bt_item:
                setAlarm();
                String name = etname.getText().toString();
                String expiration = tvExpiration.getText().toString();
                String num = etnum.getText().toString();
                dbcon.insertData(name,expiration,num);
                Intent main = new Intent(Add_item.this, saveActivity.class);
                //  .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);

                break;
            default:
                break;
        }
    }


    public static Date toDate(String dateString)
    {
        Calendar calendar = Calendar.getInstance();


        calendar.set(Calendar.YEAR, Integer.parseInt(dateString.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(dateString.substring(5, 7)) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateString.substring(8, 10)));
        calendar.set(Calendar.HOUR_OF_DAY, 24);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);


        return calendar.getTime();
    }
    public static long toLong(String dateString)
    {
        return toDate(dateString).getTime();
    }

    public void setAlarm(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try{
            date1 = simpleDateFormat.parse(new_data);
        }catch (ParseException e ){
            e.printStackTrace();
        }
        AlarmManager alarm = (AlarmManager) this
                .getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(Add_item.this, AlarmReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(Add_item.this, 0, intent, 0);
      /*  int numInt = Integer.parseInt(new_data.substring(0,4));
        int numInt2 = Integer.parseInt(new_data.substring(5,6));
        int numInt3 = Integer.parseInt(new_data.substring(7,9));*/


        Calendar calendar = Calendar.getInstance();
        long lon = toLong(new_data);
        calendar.setTime(date1);

        //alarm.set(AlarmManager.RTC, System.currentTimeMillis()+5000, sender);
        alarm.set(AlarmManager.RTC, lon, sender);
      //  alarm.set(AlarmManager.RTC, lon, sender);
      // alarm.set(AlarmManager.RTC, ((System.currentTimeMillis()-(Calendar.HOUR_OF_DAY+Calendar.MINUTE))+((lon+Calendar.HOUR_OF_DAY+Calendar.MINUTE)-System.currentTimeMillis())+66300000) ,sender);
        Log.v("시간확인", String.valueOf((lon+Calendar.HOUR_OF_DAY+Calendar.MINUTE)-System.currentTimeMillis()));
      // Toast.makeText(Add_item.this,"시간설정:"+ Integer.toString(calendar.get(calendar.YEAR))+Integer.toString(calendar.get(calendar.MONTH))+Integer.toString(calendar.get(calendar.DATE))+Integer.toString(calendar.get(calendar.HOUR_OF_DAY))+Integer.toString(calendar.get(calendar.MINUTE)), Toast.LENGTH_LONG).show();
    }


}
