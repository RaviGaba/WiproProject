package com.wipro.harman.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.wipro.harman.R;

public class NotificationHelper extends ContextWrapper {
   private NotificationManager notificationManager;
   public static final String CHANNEL_ID = "com.wipro.harman";
   public static final String CHANNEL_NAME = "Wipro Assignment";

    //Create your notification channels//
   @RequiresApi(api = Build.VERSION_CODES.O)
   public NotificationHelper(Context base) {
       super(base);
       createChannels();
   }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannels() {
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, notificationManager.IMPORTANCE_HIGH);
        notificationChannel.enableLights(true);
        notificationChannel.setShowBadge(true);
        notificationChannel.setVibrationPattern(new long[] { 1000, 1000, 1000});
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notificationChannel.setSound(alarmSound,null);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        getManager().createNotificationChannel(notificationChannel);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getNotification1(String title, String body, PendingIntent intent) {
        NotificationCompat.BigTextStyle bigPictureStyle = new NotificationCompat.BigTextStyle();
        bigPictureStyle.setSummaryText(body);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification notification;
        notification = mBuilder.setSmallIcon(R.mipmap.ic_launcher).setTicker(title).setWhen(0)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentIntent(intent)
                .setSound(alarmSound)
                .setStyle(bigPictureStyle)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentText(body)
                .build();
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(R.integer.notification_id, notification);
    }


    private NotificationManager getManager() {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }

}