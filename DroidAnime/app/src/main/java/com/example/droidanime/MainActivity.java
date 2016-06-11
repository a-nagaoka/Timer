package com.example.droidanime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // 使用する画像
    private List<ImageView> foods = new ArrayList<ImageView>();
    private int counter;
    // タイマー
    private EatingCountDownTimer eatingTimer;

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
        foods.add((ImageView) findViewById(R.id.rice));
        foods.add((ImageView) findViewById(R.id.miso_soup));
        foods.add((ImageView) findViewById(R.id.friedchicken));
        foods.add((ImageView) findViewById(R.id.gomaae));
        foods.add((ImageView) findViewById(R.id.potate_salada));
        setTouchListener();

        Button btnE = (Button)this.findViewById(R.id.btnEnd);
        btnE.setEnabled(false);

        // タイマー生成
        eatingTimer = new EatingCountDownTimer(60000, (60000-3000)/foods.size(), this);
    }

    /**
     * ビューにタッチイベントのリスナーを登録します。
     *
     */
    private void setTouchListener() {
        ImageView table = (ImageView) findViewById((R.id.table));
        for (ImageView dragView : foods) {
            DragViewListener listener = new DragViewListener(table, dragView);
            dragView.setOnTouchListener(listener);
        }
    }

    /**
     * いただきますボタンタップ時のイベント処理
     * @param view
     */
    public void onStartButtonTapped(View view) {
        // いただきますタップ禁止
        Button btnS = (Button)this.findViewById(R.id.btnStart);
        btnS.setEnabled(false);
        // ごちそうさまタップ可能
        Button btnE = (Button)this.findViewById(R.id.btnEnd);
        btnE.setEnabled(true);

        // タイマー開始
        eatingTimer.start();
        counter = -1;
    }

    /**
     * ごちそうさまボタンタップ時のイベント処理
     * @param view
     */
    public void onEndButtonTapped(View view) {
        // タイマーをとめる
        eatingTimer.cancel();

        // 次画面へ遷移
        Intent intent = new Intent(this, FinishActivity.class);
        intent.putExtra("MESSAGE",getResources().getString(R.string.message_successed));
        startActivity(intent);
    }

    public ImageView getNextFoods() {
        counter++;
        return this.foods.get(counter);

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