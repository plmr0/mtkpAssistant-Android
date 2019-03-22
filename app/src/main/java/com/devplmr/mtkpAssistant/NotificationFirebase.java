package com.devplmr.mtkpAssistant;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class NotificationFirebase
{
    public static void displayNotification(Context context, String channel, int type, String title, String [] body)
    {
        String status = (type == 0) ? "Замен нет." : "Есть замены.";
        title = "Тут должно быть число"; // todo title

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channel)
                        .setSmallIcon(R.drawable.notification_changes)
                        .setWhen(0)
                        .setContentTitle(title)
                        .setContentText(status)
                        .setColor(Color.RED)
                        .setLights(Color.RED, 0, 1)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .addAction(0, "Принять", null); // todo action

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        // Sets a title for the Inbox in expanded layout
        inboxStyle.setBigContentTitle(title);

        // Moves events into the expanded layout
        for (int i = 0; i < body.length; i++)
        {
            inboxStyle.addLine(body[i]);
        }
        // Moves the expanded layout object into the notification object.
        mBuilder.setStyle(inboxStyle);

        NotificationManagerCompat mNotificationMgr = NotificationManagerCompat.from(context);
        mNotificationMgr.notify((int) (Math.random() * 1488), mBuilder.build());
    }
}
