package com.example.apptimer;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.InputStream;

public class NoticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        // Gifアニメーション
        //InputStream stream = getResources().openRawResource(R.raw.notice_finish_1);
        //GifImageView view = new GifImageView(this, stream);
        //((LinearLayout) findViewById(R.id.notice_linear_layout)).addView(view);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        ImageView img = (ImageView)findViewById(R.id.imageView);
        // AnimationDrawableのXMLリソースを指定
        img.setBackgroundResource(R.drawable.notice_finish_animation);

        // AnimationDrawableを取得
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        // アニメーションの開始
        frameAnimation.start();
    }
}
