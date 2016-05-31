package com.example.apptimer;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

/**
 * Created by a-nagaoka on 2016/05/28.
 */
public class ShowMainIntentService extends IntentService {

    public ShowMainIntentService(String name) {
        super(name);
    }

    public ShowMainIntentService() {
        super("ShowMainIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Intent it = new Intent(this, MainActivity.class);
        it.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(it);//Activityを上にしてやる

    }
}
