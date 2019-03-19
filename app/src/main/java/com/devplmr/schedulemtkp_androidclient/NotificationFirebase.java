package com.devplmr.schedulemtkp_androidclient;

import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class NotificationFirebase
{
    public static void displayNotification(Context context, String title, String body)
    {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context, NotificationInfoChannel.CHANGES_ID)
                        .setSmallIcon(R.drawable.notification_changes)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat mNotificationMgr = NotificationManagerCompat.from(context);
        mNotificationMgr.notify(1, mBuilder.build());

    }
}
