package app.com.example.android.cloudpad_app.Classes;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import app.com.example.android.cloudpad_app.MainActivity;
import app.com.example.android.cloudpad_app.R;

/**
 * Created by Edwin on 11/7/2015.
 */
public class MyNotification {
    private final Context c;
    private final String title;
    private final String content;

    public MyNotification(Context c, String content) {
        this.c = c;
        this.title = c.getResources().getString(R.string.app_name);
        this.content = content;
    }

    public void show() {
        Intent intent = new Intent(c, MainActivity.class);
        // use System.currentTimeMillis() to have a unique ID for the pending intent
        PendingIntent pIntent = PendingIntent.getActivity(c, (int) System.currentTimeMillis(), intent, 0);

        // build notification
        // the addAction re-use the same intent to keep the example short
        android.app.Notification n = new android.app.Notification.Builder(c)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .build();


        NotificationManager notificationManager =
                (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, n);
    }
}
