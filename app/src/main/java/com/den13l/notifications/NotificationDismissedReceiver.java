package com.den13l.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotificationDismissedReceiver extends BroadcastReceiver {

  @Override public void onReceive(Context context, Intent intent) {
    Log.d("DismissedReceiver", "onReceive");
  }
}
