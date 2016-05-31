package com.example.droidanime;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // AndroidアニメDroidAmineView編
        /*
        DroidAnimeView myView = new DroidAnimeView(getApplication());
        setContentView(myView);
        */

        // ObjectAnimator を使う編
        setContentView(R.layout.activity_main);
        doAnimation();
    }

    private void doAnimation() {
        ImageView img = (ImageView) findViewById(R.id.droid1);

        // フェードインアニメ
        ObjectAnimator alpha = ObjectAnimator.ofFloat(img, "alpha", 0f, 1f);
        alpha.setDuration(3000);
        alpha.start();

        // X方向に移動するアニメ
        float toX =  getDisplayWidth() - img.getWidth()*2;
        ObjectAnimator translate = ObjectAnimator.ofFloat(img, "translationX", 0f, 300f);
        translate.setDuration(5000);
        translate.start();

        // 回転するアニメ
        // 移動後に回転したいが移動後の座標がわからん。
        ObjectAnimator rotate = ObjectAnimator.ofFloat(img, "rotation", 0f, 360f);
        rotate.setDuration(3000);
        rotate.start();
    }

    private float getDisplayWidth() {
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point(0, 0);
        display.getSize(point);
        return point.x;
    }


    /**
     * 2秒かけて引数に与えた角度と距離の位置に回転させながらターゲットを移動させる
     *
     * @param target
     * @param degree
     * @param distance
     */
    private void animatePropertyValuesHolderSample( ImageView target, float degree, float distance ) {

        // 距離と角度から到達点となるX座標、Y座標を求めます
        float toX = (float) ( distance * Math.cos( Math.toRadians( degree ) ) );
        float toY = (float) ( distance * Math.sin( Math.toRadians( degree ) ) );

        // translationXプロパティを0fからtoXに変化させます
        PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat( "translationX", 0f, toX );
        // translationYプロパティを0fからtoYに変化させます
        PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat( "translationY", 0f, toY );
        // rotationプロパティを0fから360fに変化させます
        PropertyValuesHolder holderRotaion = PropertyValuesHolder.ofFloat( "rotation", 0f, 360f );

        // targetに対してholderX, holderY, holderRotationを同時に実行させます
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                target, holderX, holderY, holderRotaion );

        // 2秒かけて実行させます
        objectAnimator.setDuration( 2000 );

    }}
