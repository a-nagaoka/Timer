package com.example.apptimer;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.format.Formatter;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MainActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.startButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // ボタン更新
                findViewById(R.id.startButton).setEnabled(false);
                findViewById(R.id.stopButton).setEnabled(true);
                // 入力時間
                String text = ((EditText)findViewById(R.id.editText)).getText().toString();
                int time = Integer.parseInt(text);
                // タイマー開始
                Intent intent = new Intent(self, TimerIntentService.class);
                intent.putExtra("WAIT_TIME", time);
                startService(intent);

                moveTaskToBack(true);

                // TODO トースト表示
                String message = getString(R.string.messageStarted, time);
                Toast.makeText(self, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
