package com.mgplo.ringostart;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.mgplo.ringostart.ui.MainActivity;

import java.util.zip.CheckedOutputStream;

public class MyReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "001";

    @Override
    public void onReceive(Context context, Intent intent) {
        //you might want to check what's inside the Intent
        if (intent.getStringExtra("myAction") != null &&
                intent.getStringExtra("myAction").equals("mDoNotify")) {
            Intent i = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent =
                    PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_ONE_SHOT);

//            NotificationManager manager =
//                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("Notificación de prueba")
                    .setContentText("Esto es una notificación de prueba")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);


            // example for blinking LED
            builder.setLights(0xFFb71c1c, 1000, 2000);
            builder.setContentIntent(pendingIntent);
            notificationManager.notify(12345, builder.build());
        }

    }


}
