package com.example.apptimer;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

/**
 * Created by a-nagaoka on 2016/05/28.
 */
public class TimerIntentService extends IntentService {

    public TimerIntentService(String name) {
        super(name);
    }

    public TimerIntentService() {
        super("TimerIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        SystemClock.sleep(5000);

        Intent timerFinishedIntent = new Intent();
        timerFinishedIntent.setAction("TIMER_FINISHED");
        sendBroadcast(timerFinishedIntent);
    }
}
