package com.devplmr.mtkpAssistant;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class NotificationFirebase
{
    public void displayChangesNotification(Context context, Map<String, String> dayData) throws IOException, ClassNotFoundException
    {
        String channel = "changes";
        String title = dayData.get("date") + " (" + dayData.get("groupName") + ") " + ((Boolean) ObjectSerialization.fromString(dayData.get("isLefortovo")) ? "(Лефортово)" : "(Осн. корпус)");
        String week = ((Boolean) ObjectSerialization.fromString(dayData.get("isTopWeek"))) ? "ВЕРХНЯЯ" : "НИЖНЯЯ";
        String status = ((Boolean) ObjectSerialization.fromString(dayData.get("isChangesAvailable"))) ? "Есть замены." : "Замен нет.";

        int notificationId = new Random().nextInt();

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channel)
                .setSmallIcon(R.drawable.notification_changes)
                .setWhen(0)
                .setContentTitle(title)
                .setContentText(status)
                .setColor(Color.RED)
                .setLights(Color.RED, 0, 1)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setChannelId(channel)
                .setOngoing(false);

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        // Sets a title for the Inbox in expanded layout
        inboxStyle.setBigContentTitle(title);
        inboxStyle.setSummaryText(week);

        // Moves events into the expanded layout
        for (String subject : dayData.get("subjects").split(","))
        {
            inboxStyle.addLine(subject);
        }
        // Moves the expanded layout object into the notification object.
        mBuilder.setStyle(inboxStyle);

        NotificationManagerCompat mNotificationMgr = NotificationManagerCompat.from(context);
        mNotificationMgr.notify(notificationId, mBuilder.build());
    }

    public void displayScheduleNotification(Context context, List<String> scheduleData)
    {
        String channel = "schedule";
        String title = "Внимание";
        String body = "Доступно новое расписание.";

        int notificationId = new Random().nextInt();

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channel)
                .setSmallIcon(R.drawable.notification_schedule)
                .setWhen(0)
                .setContentTitle(title)
                .setContentText(body)
                .setColor(Color.RED)
                .setLights(Color.RED, 0, 1)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setChannelId(channel)
                .setOngoing(false);

        NotificationManagerCompat mNotificationMgr = NotificationManagerCompat.from(context);
        mNotificationMgr.notify(notificationId, mBuilder.build());
    }
}
