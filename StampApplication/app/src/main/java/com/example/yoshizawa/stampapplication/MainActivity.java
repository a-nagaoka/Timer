package com.example.yoshizawa.stampapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * 　メインクラス
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * スタンプボタン押下
     * @param view
     */
    public void onStampButtonTapped(View view){
        Intent intent = new Intent(this,StampActivity.class);
        EditText editText = (EditText) findViewById(R.id.inputText);
        String _inputId = editText.getText().toString();
        if(!_inputId.isEmpty()) {
            long id = Long.valueOf(editText.getText().toString());
            intent.putExtra("ID", id);
        }
        startActivity(intent);
    }
}
