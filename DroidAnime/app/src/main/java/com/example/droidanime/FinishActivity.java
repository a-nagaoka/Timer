package com.example.droidanime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        // 前画面からの結果をラベルに表示する。
        Intent intent = getIntent();
        TextView message = (TextView)findViewById(R.id.resultLabel);
        if (message != null) {
            message.setText(intent.getStringExtra("MESSAGE"));
        }
    }

    /**
     * 戻るボタンタップ時の処理
     * @param view
     */
    public void onBackButtonTapped(View view) {
        finish();
    }
}
