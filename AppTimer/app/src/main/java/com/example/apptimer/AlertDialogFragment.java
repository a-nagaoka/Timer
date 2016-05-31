package com.example.apptimer;


import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by a-nagaoka on 2016/05/28.
 */
public class AlertDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("お知らせ");
        builder.setMessage("時間だよ。");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onOkButtonClick(dialog, which);
            }
        });

        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    public void onOkButtonClick(DialogInterface dialog, int which) {
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().finish();
    }
}
