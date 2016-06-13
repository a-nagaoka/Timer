package com.example.droidanime;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.ImageView;

/**
 * Created by rika on 2016/06/11.
 */
public class EatingCountDownTimer extends CountDownTimer {
    // タイマー処理で使う画像
    private Context context;

    public EatingCountDownTimer(long millisInFuture, long countDownInterval, Context context) {
        super(millisInFuture, countDownInterval);
        this.context = context;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        ImageView img = ((MainActivity)context).getNextFoods();
        // 順番に画像をフェードアウトしていく
        ObjectAnimator alpha = ObjectAnimator.ofFloat(img, "alpha", 1f, 0f);
        alpha.setDuration(3000);
        alpha.start();
    }

    @Override
    public void onFinish() {
        // 次画面へ遷移
        Intent intent = new Intent(context, FinishActivity.class);
        intent.putExtra("MESSAGE", context.getResources().getString(R.string.message_failed));
        context.startActivity(intent);
    }
}
