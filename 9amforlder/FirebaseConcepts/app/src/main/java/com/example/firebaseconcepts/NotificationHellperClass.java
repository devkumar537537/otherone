package com.example.firebaseconcepts;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class NotificationHellperClass {
    private static final String CHANNEL_ID = "technicalGuyes";
   private static final CharSequence name = "my_channel";
   private static final String Description = "This is my channel";
    private final int NOTIFICATION_ID = 201;
    static void addNotification(Context context,String title,String body) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {


            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);

            mChannel.setDescription(Description);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);

            notificationManager.createNotificationChannel(mChannel);
        }
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context,CHANNEL_ID)
                        .setSmallIcon(android.R.drawable.ic_lock_idle_charging) //set icon for notification
                        .setContentTitle(title) //set title of notification
                        .setContentText(body)//this is notification message
                        .setAutoCancel(true) // makes auto cancel of notification
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT); //set priority of notification


        Intent notificationIntent = new Intent(context, NotificationActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //notification message will get at NotificationView
        notificationIntent.putExtra("message", body);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 23, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        // Add as notification

        notificationManager.notify(0, builder.build());
    }
}
