package com.shishamo.shishamotimer.meal;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.shishamo.shishamotimer.R;

public class FinishMealActivity extends AppCompatActivity {
    // 結果
    int resultMealId;

    // 効果音の再生
    SoundPool soundPool;
    int[] soundResId = new int[2];

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_meal);
    }

    /**
     * アクティビティが画面に表示されたときのイベント処理
     */
    @Override
    protected void onResume() {
        super.onResume();
        // 効果音を読み込んでおく
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        soundResId[0] = soundPool.load(this, R.raw.muci_hono_04, 1);
        soundResId[1] = soundPool.load(this, R.raw.muci_hono_01, 1);

        // 前画面からの結果をラベルに表示する。
        Intent intent = getIntent();
        resultMealId = intent.getIntExtra("MESSAGE",0);

        TextView message = (TextView)findViewById(R.id.resultLabel);
        if (message != null) {
            message.setText(getResources().getString(resultMealId));
        }

        // 結果にあわせたアニメーションを表示
        ImageView img = (ImageView)findViewById(R.id.imageView);
        int soundId = 0;
        if (resultMealId == R.string.message_succeed) {
            // 成功の場合
            // AnimationDrawableのXMLリソースを指定
            img.setBackgroundResource(R.drawable.succeed_finish_animation);
            soundId = soundResId[0];
        }
        else {
            // 失敗した場合
            img.setBackgroundResource(R.drawable.fail_finish_animation);
            soundId = soundResId[1];
        }
        // AnimationDrawableを取得
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
        // アニメーションの開始
        frameAnimation.start();
        // 効果音の再生
        soundPool.play(soundId, 1.0f, 1.0f, 0, 1, 1);
    }
}
