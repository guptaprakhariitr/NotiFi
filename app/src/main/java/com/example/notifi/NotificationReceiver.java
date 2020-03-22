package com.example.notifi;

import android.app.Notification;
import android.content.Intent;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

public class NotificationReceiver extends NotificationListenerService {
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);String s,title;
        if(sbn.getNotification().extras.getCharSequence(Notification.EXTRA_TEXT)!=null){
     s=sbn.getNotification().extras.getCharSequence(Notification.EXTRA_TEXT).toString();
         title = sbn.getNotification().extras.getCharSequence(Notification.EXTRA_TITLE).toString()+"\n"+s;}
        else {s=""; title="";}
        Intent intent = new  Intent("/home/prakhar/AndroidStudioProjects/NotiFi/app/src/main/java/com/example/notifi/NotificationReceiver.java");
        intent.putExtra("title",title);
        sendBroadcast(intent);
    }

}
