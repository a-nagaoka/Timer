package com.example.droidanime;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

/**
 * 描画用のクラス
 * Created by rika on 2016/05/31.
 */
public class DroidAnimeView extends View {
    private Paint myPaint = new Paint();
    private Bitmap myBitmap1, myBitmap2;
    private Bitmap myBitmap3, myBitmap4;
    private int displayWidth, displayHeight;
    private int imageWidth, imageHeight;
    private int x, y;
    private int dx, dy;
    private int count;
    private boolean isOffBalance = false;
    private boolean isAttached;

    private static final long DELAY_MILLIS = 30;

    /**
     * コンストラクタ
     * @param c
     */
    public DroidAnimeView(Context c) {
        super(c);

        // フォーカスの有効化
        setFocusable(true);

        // 画像ロード
        Resources res = this.getContext().getResources();
        myBitmap1 = BitmapFactory.decodeResource(res, R.drawable.droid1);
        myBitmap2 = BitmapFactory.decodeResource(res, R.drawable.droid2);
        myBitmap3 = BitmapFactory.decodeResource(res, R.drawable.droid3);
        myBitmap4 = BitmapFactory.decodeResource(res, R.drawable.droid4);

        // 画像サイズ取得
        imageWidth = myBitmap1.getWidth();
        imageHeight = myBitmap1.getHeight();

        // 座標と差分の初期化
        x = 0;
        y = 0;
        dx = 2;
        dy = imageHeight;

        //  慌てる回数の初期化
        count = 0;
    }

    /**
     * 描画処理
     * @param canvas
     */
    protected void onDraw(Canvas canvas) {
        Bitmap myBitmap;

        // 背景を白
        canvas.drawColor(Color.WHITE);

        // 移動方向のチェック
        if (dx < 0) {
            // 左向きにする
            myBitmap = myBitmap1;
        } else {
            // 右向きにする
            myBitmap = myBitmap2;
        }

        // 不安定な姿勢かどうかのチェック
        if (isOffBalance) {
            count++;

            // 3回に1回交互に画像を入れ替える
            if (count/3 - count/6*2 == 0) {
                myBitmap = myBitmap3;
            } else {
                myBitmap = myBitmap4;
            }

            if (count > 18) {
                // 不安定な姿勢の終了
                count = 0;
                isOffBalance = false;
            }
        }
        // 画像の描画
        canvas.drawBitmap(myBitmap, x, y, myPaint);
    }

    /**
     * サイズが変更されたときのイベント
     * （端末を回転させたとき）
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // 画面の縦と横のサイズを取得
        displayWidth = w;
        displayHeight = h;
    }

    /**
     * タッチイベント
     * @param event
     * @return
     */
    public boolean onTouchEvent(MotionEvent event) {
        // タッチされた座標の取得
        int x1 = (int)event.getX();
        int y1 = (int)event.getY();

        if (x < x1 && x1 < x + imageWidth) {
            if (y < y1 && y1 < y + imageHeight) {
                // アイコン内にあれば不安定姿勢にする
                isOffBalance = true;
            }
        }
        return true;
    }

    /**
     * 移動処理
     */
    private void move() {
        // X方向のチェック
        if (x < 0 || x + imageWidth > displayWidth) {
            // 端まで移動したら方向を反転して移動
            dx = -dx;
            y += dy;
        }
        // Y方向のチェック
        if (y < 0 || y + imageHeight > displayHeight) {
            // 端まで移動したら方向を反転して移動
            dy = -dy;
            y += dy*2;
        }

        //x座標の移動
        x+=dx;
    }

    /**
     * タイマーハンドラー
     */
    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (isAttached) {
                // 移動
                move();
            }
            // 再描画
            invalidate();
            sendEmptyMessageDelayed(0, DELAY_MILLIS);
        }
    };

    /**
     * WindowにAttachされた時の処理
     */
    protected void onAttachedToWindow() {
        isAttached = true;
        myHandler.sendEmptyMessageDelayed(0, DELAY_MILLIS);
        super.onAttachedToWindow();
    }

    /**
     * WindowsからDetachされた時の処理
     */
    protected void onDetachedFromWindow() {
        isAttached = false;
        super.onDetachedFromWindow();
    }
}
