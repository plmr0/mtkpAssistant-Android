package com.devplmr.mtkpAssistant;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;


public class MyFirebaseMessagingService extends FirebaseMessagingService
{
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage)
    {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage != null)
        {
            Map<String, String> data = remoteMessage.getData();

            String channel = data.get("channel_id");
            String type = data.get("type");
            String title = data.get("title");
            String body = data.get("body");

            NotificationFirebase.displayNotification(getApplicationContext(), channel, Integer.parseInt(type), title, body
                            .replace("[", "")
                            .replace("]", "")
                            .replace("\"", "")
                            .split(",")
                    );
        }
    }

}
