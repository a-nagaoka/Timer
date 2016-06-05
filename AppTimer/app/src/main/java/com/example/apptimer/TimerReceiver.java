package com.example.apptimer;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by a-nagaoka on 2016/05/28.
 */
public class TimerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Intent alertIntent = new Intent(context, AlertDialogActivity.class);
        Intent alertIntent = new Intent(context, NoticeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, alertIntent, 0);

        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }
}
