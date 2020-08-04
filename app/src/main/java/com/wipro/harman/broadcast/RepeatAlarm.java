package com.wipro.harman.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.wipro.harman.utils.Alarm;

public class RepeatAlarm extends BroadcastReceiver {
    private Context mContext;
    private Alarm mAlarm;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.mContext = context;
        mAlarm = new Alarm();
        mAlarm.setRepeatAlarm(mContext); // actually alarm is set for once but when it comes to broadcast we again set alarm for next 1 minute.
    }
}
