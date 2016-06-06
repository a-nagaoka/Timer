package com.example.droidanime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        /*
        setContentView(R.layout.activity_main);
        doAnimation();
        */


        // ドラッグする画像にリスナー登録
        setContentView(R.layout.drag_activity);
        setTouchListener((ImageView) findViewById(R.id.rice));
        setTouchListener((ImageView) findViewById(R.id.miso_soup));
        setTouchListener((ImageView) findViewById(R.id.friedchicken));
    }

    /**
     * 指定のビューにタッチイベントのリスナーを登録します。
     *
     * @param dragView
     */
    private void setTouchListener(ImageView dragView) {
        DragViewListener listener = new DragViewListener(dragView);
        dragView.setOnTouchListener(listener);
    }

    /**
     * いただきますボタンタップ時のイベント処理
     * @param view
     */
    public void onClickStartButton(View view) {
        // タイマー開始
    }

    /** 以下はドロイドアニメ用の実装 **/
    /*
    private void doAnimation() {
        ImageView img = (ImageView) findViewById(R.id.droid1);

        // フェードインアニメ
        ObjectAnimator alpha = ObjectAnimator.ofFloat(img, "alpha", 0f, 1f);
        alpha.setDuration(3000);
        alpha.start();

        // X方向に移動するアニメ
        float toX =  getDisplayWidth() - img.getWidth()*2;
        ObjectAnimator translate = ObjectAnimator.ofFloat(img, "translationX", 0f, toX);
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
*/
}