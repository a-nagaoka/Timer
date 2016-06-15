package com.example.apptimer;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.text.Editable;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // メインアクティビティ
    private MainActivity self = this;
    // タイマー
    private AppCountDownTimer mTimer;
    // スタートボタン
    private View mStartButton;
    // ストップボタン
    private View mStopButton;
    // ステータス
    private TextView mStatusMessage;
    // 時間ピッカー
    private NumberPicker mTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // アニメーション表示のサンプル
        // ((LinearLayout) findViewById(R.id.container)).addView(new MyView(this));
    }

    @Override
    protected void onResume() {
        super.onResume();

        // ボタン・テキストの初期化
        initialize();
    }

    @Override
    public void finish() {
        super.finish();

        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    /**
     * 初期化します。
     */
    private void initialize() {
        mStartButton = findViewById(R.id.startButton);
        mStopButton = findViewById(R.id.stopButton);
        mStatusMessage = (TextView) findViewById(R.id.appTimerStatus);
        mTimePicker = (NumberPicker) findViewById(R.id.numberPicker);

        // 初期化
        setNumberPicker(mTimePicker, 5, 60, 5, R.string.timeFormat);
        // 初期値は20分に設定
        mTimePicker.setValue(3);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 入力時間取得
                int seconds = (mTimePicker.getValue() + 1) * 5 * 60;

                // デバッグ用
                // 10秒で通知するよう設定
                CheckBox checkBox = (CheckBox) findViewById(R.id.debugCheckBox);
                if (checkBox.isChecked()) {
                    seconds = 10;
                }

                // タイマー開始
                mTimer = new AppCountDownTimer(seconds * 1000, 1000);
                mTimer.start();

                // メニュー画面に戻す
                moveTaskToBack(true);

                // トースト表示
                String message = getString(R.string.messageStarted, seconds / 60);
                Toast.makeText(self, message, Toast.LENGTH_LONG).show();

                // ボタン更新
                mStartButton.setEnabled(false);
                mStopButton.setEnabled(true);
                mTimePicker.setEnabled(false);

                // メッセージ更新
                mStatusMessage.setText(getResources().getString(R.string.statusStarted, seconds));
            }
        });

        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // タイマー停止
                mTimer.cancel();

                // タイマーの状態を更新
                stopTimer();
            }
        });
    }

    /**
     * 数字ピッカーを初期化します。
     * @param picker
     * @param min
     * @param max
     * @param step
     * @param format
     */
    private void setNumberPicker(NumberPicker picker, int min, int max, int step, int format) {
        picker.setMinValue(min / step - 1);
        picker.setMaxValue((max / step) - 1);
        String[] valueSet = new String[max / min];
        for (int i = min; i <= max; i += step) {
            valueSet[(i / step) - 1] = getString(format, i);
        }
        picker.setDisplayedValues(valueSet);
    }

    /**
     * タイマーの状態を更新します。
     */
    private void stopTimer() {
        // タイマーが動いている場合はキャンセル
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }

        // ボタン更新
        mStartButton.setEnabled(true);
        mStopButton.setEnabled(false);

        // 時間選択ピッカーを有効に設定
        mTimePicker.setEnabled(true);

        // メッセージ更新
        mStatusMessage.setText(getResources().getString(R.string.statusStopped));
    }

    /**
     * カウントダウンタイマー
     */
    public class AppCountDownTimer extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public AppCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long time = millisUntilFinished / (60 * 1000);
            // メッセージ更新
            mStatusMessage.setText(getResources().getString(R.string.messageStarted, time));
        }

        @Override
        public void onFinish() {
            // タイマーの状態を更新
            mTimer = null;
            stopTimer();

            // 終了のお知らせ画面に遷移
            Intent alertIntent = new Intent(self, NoticeActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(self, 0, alertIntent, 0);

            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        }
    }

    private class MyView extends View {

        ValueAnimator anim;

        public MyView(Context context) {
            super(context);

            anim = ValueAnimator.ofFloat(0.f, 100.f);
            // アニメーションの時間(3秒)を設定する
            anim.setDuration(3000);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    invalidate();
                }
            });
            anim.start();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // 赤い背景
            canvas.drawColor(Color.WHITE);

            // だんだん大きくなる緑の円を書くよ
            float radius = (Float) (anim.getAnimatedValue());
            Paint p = new Paint();
            p.setColor(Color.GREEN);
            canvas.drawCircle(getWidth()/2, getHeight()/2, radius, p);
        }
    }
}
