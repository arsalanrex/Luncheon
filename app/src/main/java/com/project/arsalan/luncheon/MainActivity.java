package com.project.arsalan.luncheon;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    int food;
    int resident;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //alarmService


        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY,07);
        calendar.set(Calendar.MINUTE,00);
        calendar.set(Calendar.SECOND,00);


        Intent intent = new Intent(getApplicationContext(), Notification_reciever.class);
        intent.setAction("DEVELOPER_ARSALAN");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);


        //menu

        TextView textView = (TextView) findViewById(R.id.menutextview);

        GregorianCalendar cal = new GregorianCalendar();

        System.out.println(cal.get(Calendar.DAY_OF_WEEK));


        if (cal.get(Calendar.DAY_OF_WEEK) - 1 == 1) { //Is it Monday?
            System.out.println("Today is monday.");
            textView.setText("mon menu");
        }
        if (cal.get(Calendar.DAY_OF_WEEK) - 1 == 2) { //Is it Tuesday?
            System.out.println("Today is monday.");
            textView.setText("tue menu");
        }
        if (cal.get(Calendar.DAY_OF_WEEK) - 1 == 3) { //Is it Wednesday?
            System.out.println("Today is monday.");
            textView.setText("wed menu");
        }
        if (cal.get(Calendar.DAY_OF_WEEK) - 1 == 4) { //Is it Thursday?
            System.out.println("Today is monday.");
            textView.setText("Veg: \n Non-veg:");
        }
        if (cal.get(Calendar.DAY_OF_WEEK) - 1 == 5) { //Is it Friday?
            System.out.println("Today is monday.");
            textView.setText("fri menu");
        }
        if (cal.get(Calendar.DAY_OF_WEEK) - 1 == 6) { //Is it Saturday?
            System.out.println("Today is monday.");
            textView.setText("sat menu");
        }
        if (cal.get(Calendar.DAY_OF_WEEK) - 1 == 7) { //Is it Sunday?
            System.out.println("Today is monday.");
            textView.setText("sun menu");
        }
    }


    public void onRadioButtonFood(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_veg:;
                if (checked)
                    // veg classification
                    food=0;

                break;
            case R.id.radio_nveg:
                if (checked)
                    // non-veg classification
                    food=1;

                break;
        }
    }

    public void onRadioButtonResident(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_hosteller:;
                if (checked)
                    // hosteller classification
                    resident=0;

                break;
            case R.id.radio_dayscholar:
                if (checked)
                    // dayscholar classification
                    resident=1;

                break;
        }
    }


    Runnable runnableToUpdateVegDb = new Runnable() {
        @Override
        public void run() {
            Log.d("DEBUG","RUNNING VEG RUNNABLE");
            try {
                URL url = new URL("http://192.168.43.242/luncheon/updateveg.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String response = bufferedReader.readLine();
                Log.d("DEBUG", response);
                httpURLConnection.disconnect();
            }
            catch (Exception e)
            {
                Log.d("DEBUG",e.toString());
            }
        }
    };

    Runnable runnableToUpdateNonVegDb = new Runnable() {
        @Override
        public void run() {
            Log.d("DEBUG","RUNNING NON VEG RUNNABLE");
            try {
                URL url = new URL("http://192.168.43.242/luncheon/updatenonveg.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String response = bufferedReader.readLine();
                Log.d("DEBUG", response);
                httpURLConnection.disconnect();
            }
            catch (Exception e)
            {
                Log.d("DEBUG",e.toString());
            }
        }
    };

    Runnable runnableToUpdateHostellerDb = new Runnable() {
        @Override
        public void run() {
            Log.d("DEBUG","RUNNING HOSTELLER RUNNABLE");
            try {
                URL url = new URL("http://192.168.43.242/luncheon/updatehosteller.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String response = bufferedReader.readLine();
                Log.d("DEBUG", response);
                httpURLConnection.disconnect();
            }
            catch (Exception e)
            {
                Log.d("DEBUG",e.toString());
            }
        }
    };

    Runnable runnableToUpdateDayscholarDb = new Runnable() {
        @Override
        public void run() {
            Log.d("DEBUG","RUNNING DAYSCHOLAR RUNNABLE");
            try {
                URL url = new URL("http://192.168.43.242/luncheon/updatedayscholar.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String response = bufferedReader.readLine();
                Log.d("DEBUG", response);
                httpURLConnection.disconnect();
            }
            catch (Exception e)
            {
                Log.d("DEBUG",e.toString());
            }
        }
    };

    public void submit(View view) {
        //add data to database
        Toast.makeText(this, "Thank you for your response.", Toast.LENGTH_SHORT).show();
        {if (food==0) {
            //increment value of veg in database
            Thread threadToUpdateVegDb = new Thread(runnableToUpdateVegDb);
            threadToUpdateVegDb.start();
        }else
        {
            //increment value of non-veg in database
            Thread threadToUpdateNonVegDb = new Thread(runnableToUpdateNonVegDb);
            threadToUpdateNonVegDb.start();
        }}

        {if (resident==0)
        {
            //increment value of hosteller in database
            Thread threadToUpdateHostellerDb = new Thread(runnableToUpdateHostellerDb);
            threadToUpdateHostellerDb.start();
        }else
        {
            //increment value of dayscholar in database
            Thread threadToUpdateDayScholarDb = new Thread(runnableToUpdateDayscholarDb);
            threadToUpdateDayScholarDb.start();
        }}



    }

    public void decline(View view) {
        //do nothing
        Toast.makeText(this, "Thank you for your response.", Toast.LENGTH_SHORT).show();

    }




}
