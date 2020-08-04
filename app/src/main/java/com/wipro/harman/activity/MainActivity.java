package com.wipro.harman.activity;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wipro.harman.R;
import com.wipro.harman.broadcast.RepeatAlarm;
import com.wipro.harman.utils.PrefsHelper;

public class MainActivity extends AppCompatActivity {

    private PrefsHelper mPrefsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mPrefsHelper = new PrefsHelper(this);
    }

    // Button click listener
    public void startNotification(View view){

        if (!mPrefsHelper.isPrefExists(PrefsHelper.IS_ALARM_SET)||!mPrefsHelper.getPref(PrefsHelper.IS_ALARM_SET,false)){
            /*Repeat alarm class is where we set an alarm which occur on every 1 minute */
            Intent intent = new Intent(MainActivity.this, RepeatAlarm.class);
            sendBroadcast(intent); // send a broadcast to RepeatAlarm Class
            mPrefsHelper.savePref(PrefsHelper.IS_ALARM_SET,true);
        }else{
            Toast.makeText(this, "Notification Service is Already in Run State", Toast.LENGTH_SHORT).show();
        }

        
    }
}
