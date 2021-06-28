package com.example.myfiretfirebaseconcetpe;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseServices  extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMessaginServi";
    private  final String FCM_CHANNEL_ID = "FCM_CHANNEL_ID";
    String name = "my_channel";
    String description = "This is my channel";
    private final  int notification_id = 201;
    int count;
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getNotification() != null) {

            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();

            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel notificationChannel = new NotificationChannel(FCM_CHANNEL_ID,name,importance);
                notificationChannel.setDescription(description);
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(android.R.color.holo_green_dark);
                notificationChannel.setShowBadge(false);
                manager.createNotificationChannel(notificationChannel);
            }

            Intent intent = new Intent(this,AfterNotificationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            if(remoteMessage.getData().size() > 0)
            {
                for(String key: remoteMessage.getData().keySet())
                {
                    if(key.equals("title"))
                    {
                        intent.putExtra("message",remoteMessage.getData().get(key));
                    }

                }

                Log.d("servictag","onMessageRecieved: Data : "+remoteMessage.getData().toString());
            }

            NotificationCompat.Builder notification = new NotificationCompat.Builder(this,FCM_CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_lock_idle_charging)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setColor(Color.BLUE);

            PendingIntent pendingIntent = PendingIntent.getActivity(this,12,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            notification.setContentIntent(pendingIntent);

            manager.notify(12,notification.build());

        }
    }
}
