package com.example.yoshizawa.stampapplication;


import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

;

/**
 * Created by yoshizawa on 2016/06/21.
 */
public class CustomDialog extends DialogFragment {

    private  String message;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity());
        // タイトル非表示
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // フルスクリーン
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.dialog_custom);
        // 背景を透明にする
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView titleView = (TextView) dialog.findViewById(R.id.title);
        titleView.setText("ドロイドくんより");

        ImageView imageView = (ImageView) dialog.findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.cake);

        TextView messageView = (TextView) dialog.findViewById(R.id.message);
        messageView.setText(this.message);

        // OK ボタンのリスナ
        dialog.findViewById(R.id.positive_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        // Close ボタンのリスナ
        dialog.findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

       return dialog;
    }

     public void setMessage(String msg){
        this.message = msg;

    }
}
