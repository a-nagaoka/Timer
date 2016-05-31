package com.example.apptimer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Created by a-nagaoka on 2016/05/28.
 */
public class AlertDialogActivity extends FragmentActivity {

    private AlertDialogActivity self = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialogFragment fragment = new AlertDialogFragment() {
            @Override
            public void onOkButtonClick(DialogInterface dialog, int which) {
                startService(new Intent(self, ShowMainIntentService.class));
            }
        };
        fragment.show(getSupportFragmentManager(), "alert_dialog");
    }
}
