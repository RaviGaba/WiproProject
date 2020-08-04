package com.wipro.harman.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import com.wipro.harman.R;
import com.wipro.harman.broadcast.RepeatAlarm;

import java.util.Calendar;

public class Alarm {

    private Utility mUtility;
    private NotificationUtil mNotificationUtil;


    public Alarm(){
        mUtility = new Utility();
        mNotificationUtil = new NotificationUtil();
    }

    public void setRepeatAlarm(Context context) {
        Toast.makeText(context, "Alarm set again", Toast.LENGTH_SHORT).show();
        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent receiverIntent = new Intent(context, RepeatAlarm.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, receiverIntent, 0);
        Calendar alarmCalendarTime = Calendar.getInstance(); //Convert to a Calendar instance to be able to get the time in milliseconds to trigger the alarm
        // we can set the time to this calendar as we want to ring the alarm
        alarmCalendarTime.add(Calendar.MINUTE, 01);
        alarmCalendarTime.add(Calendar.SECOND,00);
        alarmCalendarTime.add(Calendar.HOUR_OF_DAY, 00);

        if (Build.VERSION.SDK_INT >= 23) {
            alarmMgr.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmCalendarTime.getTimeInMillis(), alarmIntent);
        } else if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 23) {
            alarmMgr.setExact(AlarmManager.RTC_WAKEUP, alarmCalendarTime.getTimeInMillis(), alarmIntent);
        } else {
            alarmMgr.set(AlarmManager.RTC_WAKEUP, alarmCalendarTime.getTimeInMillis(), alarmIntent);
        }

        // Generate notification while app is in background
        if (mUtility.isAppIsInBackground(context)){
            // Generate the notification on every alarm receiver....
            mNotificationUtil.generateNotification(context, context.getString(R.string.notification_message), context.getString(R.string.notification_title), context.getResources().getInteger(R.integer.notification_id));
        }
    }
}
