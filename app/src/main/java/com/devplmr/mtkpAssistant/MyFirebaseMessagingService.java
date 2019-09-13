package com.devplmr.mtkpAssistant;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService
{
    NotificationFirebase notificationFirebase = new NotificationFirebase();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage)
    {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage != null)
        {
            Map<String, String> data = remoteMessage.getData();

            if (data.get("channel_id").equals("changes"))
            {
                String groupName = data.get("groupName");
                String date = data.get("date");
                String subjects = data.get("subjects");
                String serializedIsLefortovo = data.get("isLefortovo");
                String serializedIsTopWeek = data.get("isTopWeek");
                String serializedIsChangesAvailable = data.get("isChangesAvailable");

                Map<String, String> dayData = new HashMap<>();
                dayData.put("groupName", groupName);
                dayData.put("subjects", subjects);
                dayData.put("date", date);
                dayData.put("isLefortovo", serializedIsLefortovo);
                dayData.put("isTopWeek", serializedIsTopWeek);
                dayData.put("isChangesAvailable", serializedIsChangesAvailable);

                try
                {
                    notificationFirebase.displayChangesNotification(getApplicationContext(), dayData);
                }
                catch (IOException | ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
            }
            else if (data.get("channel_id").equals("schedule"))
            {
                String serializedGroups = data.get("groups");
                List<String> deserializedGroups = null;
                try
                {
                    deserializedGroups = (List<String>) ObjectSerialization.fromString(serializedGroups);
                }
                catch (IOException | ClassNotFoundException e)
                {
                    e.printStackTrace();
                }

                notificationFirebase.displayScheduleNotification(getApplicationContext(), deserializedGroups);
            }
        }
    }
}
