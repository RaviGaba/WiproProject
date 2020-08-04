package com.wipro.harman.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.wipro.harman.utils.Alarm;

/*
* DeviceSwitchedOffReceiver is broadcast receiver when the device get switched on it will restart out alarm for notification
* */

public class DeviceSwitchedOffReceiver extends BroadcastReceiver {

    private Context mContext;
    private Alarm mAlarm;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.mContext = context;
        mAlarm = new Alarm();
        mAlarm.setRepeatAlarm(mContext);
    }
}
