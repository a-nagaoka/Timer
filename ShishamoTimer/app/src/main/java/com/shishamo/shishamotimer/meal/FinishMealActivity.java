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
    private int mResultId;

    // 効果音の再生
    private SoundPool mSoundPool;
    private int mSoundResId = -1;
    private boolean mSoundReady = false;

    /**
     * アプリ起動イベント
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_meal);
    }

    /**
     * アプリ開始イベント
     */
    @Override
    protected void onStart() {
        super.onStart();

        // 効果音をプールする
        setSoundPool();
    }
    /**
     * 最前面表示のイベント
     */
    @Override
    protected void onResume() {
        super.onResume();
        setSoundPool();

        // 前画面からの結果を取得
        Intent intent = getIntent();
        mResultId = intent.getIntExtra("MESSAGE",0);

        // 結果テキストを表示
        TextView message = (TextView)findViewById(R.id.resultLabel);
        message.setText(getResources().getString(mResultId));

        // 結果にあわせたアニメーションを表示
        ImageView img = (ImageView)findViewById(R.id.imageView);
        int soundId = 0;
        if (mResultId == R.string.message_succeed) {
            // 成功の場合
            // AnimationDrawableのXMLリソースを指定
            img.setBackgroundResource(R.drawable.succeed_finish_animation);
        }
        else {
            // 失敗した場合
            img.setBackgroundResource(R.drawable.fail_finish_animation);
        }
        // AnimationDrawableを取得
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
        // アニメーションの開始
        frameAnimation.start();
        if (mSoundReady) {
            mSoundPool.play(mSoundResId, 1.0f, 1.0f, 0, 1, 1.0f);
        }
    }

    /**
     *
     */
    @Override
    protected void onPause() {
        super.onPause();
        unsetSoundPool();
    }

    /**
     * 効果音をロードする
     */
    private void setSoundPool() {
        if (mSoundPool != null) {
            return;
        }
        mSoundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                mSoundReady = true;
            }
        });
        mSoundResId = mSoundPool.load(getApplicationContext(), R.raw.muci_hono_04, 1);
    }
    private void unsetSoundPool() {
        if (mSoundPool == null) {
            return;
        }
        mSoundPool.unload(mSoundResId);
        mSoundPool.release();
        mSoundPool = null;
    }
}
