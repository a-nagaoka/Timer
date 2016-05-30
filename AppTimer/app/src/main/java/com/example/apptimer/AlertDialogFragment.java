package com.example.apptimer;


import android.app.AlertDialog;
import android.app.Dialog;
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
        Dialog dialog = builder.setTitle("通知").setMessage("").create();
        dialog.setCanceledOnTouchOutside(true);

        return dialog;
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().finish();
    }
}
