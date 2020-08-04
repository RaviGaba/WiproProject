package com.wipro.harman.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.wipro.harman.R;
import com.wipro.harman.activity.MainActivity;

public class NotificationUtil {

    private NotificationHelper notificationHelper;

    public void generateNotification(Context context, String message, String title, int not_id) {
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);
        if (Build.VERSION.SDK_INT >= 26) {
            notificationHelper = new NotificationHelper(context);
            notificationHelper.getNotification1(title, message, pIntent);
        }else{
            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, context.getResources().getString(R.string.channel_id))
                    .setSmallIcon(R.mipmap.ic_launcher) // set the notification icon
                    .setContentTitle(title) // title of notification
                    .setContentText(message) // message of notification
                    .setContentIntent(pIntent) // pending intent is the foreign intent used to open app from outside app scope
                    .setStyle(new NotificationCompat.BigTextStyle() // expandable notification style if text is larger than one line
                            .bigText(message))
                    .setSound(alarmSound) // notification ringtone
                    .setVibrate(new long[] { 1000, 1000, 1000,}) // this is vibration style
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(not_id, mBuilder.build());
        }
    }
}
