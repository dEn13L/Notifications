package com.den13l.notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  public static final int NOTIFICATION_ID = 1;

  private int notificationsCounter = 0;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    getSupportActionBar().setTitle(R.string.activity_main);
  }

  public void postNotification(View view) {
    NotificationManager notificationManager =
        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    NotificationCompat.Builder builder =
        new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_mail_outline_black_24px);

    builder.setAutoCancel(true).setDefaults(NotificationCompat.DEFAULT_ALL);

    PendingIntent pendingIntent;
    int notificationNumber = ++notificationsCounter;
    if (notificationNumber == 1) {
      String contentTitle = "Notification #" + notificationNumber + " title";
      String contentText = "Notification #" + notificationNumber + " text";
      builder.setContentTitle(contentTitle).setContentText(contentText);

      Intent intent = new Intent(this, SingleNotificationActivity.class);
      TaskStackBuilder stackBuilder =
          TaskStackBuilder.create(this).addParentStack(MainActivity.class).addNextIntent(intent);
      pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    } else {
      NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

      String contentTitle = "Notifications";
      String title = notificationNumber + " notifications";
      String summaryText = "summary text";
      inboxStyle.setBigContentTitle(title);
      inboxStyle.setSummaryText(summaryText);

      for (int i = 1; i <= notificationNumber; i++) {
        String line = "Notification #" + i;
        inboxStyle.addLine(line);
      }

      builder.setStyle(inboxStyle)
          .setNumber(notificationNumber)
          .setContentTitle(contentTitle)
          .setContentText(title);

      Intent intent = new Intent(this, MultipleNotificationsActivity.class);
      pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
    builder.setContentIntent(pendingIntent);
    notificationManager.notify(NOTIFICATION_ID, builder.build());
  }

  public void removeNotifications(View view) {
    NotificationManager notificationManager =
        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.cancel(NOTIFICATION_ID);
    notificationsCounter = 0;
  }
}
