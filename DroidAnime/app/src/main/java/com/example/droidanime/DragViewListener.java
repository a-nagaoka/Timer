package com.example.droidanime;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by rika on 2016/06/04.
 */
public class DragViewListener implements View.OnTouchListener {
    // 配置する場所
    private ImageView baseView;
    // ドラッグ対象のビュー
    private ImageView dragView;
    // ドラッグ中の移動量
    private int preX;
    private int preY;

    /**
     * コンストラクタ
     * @param dragView
     */
    public DragViewListener(ImageView baseView, ImageView dragView) {
        this.baseView = baseView;
        this.dragView = dragView;
    }

    /**
     * タッチイベント
     * @param view
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        // タッチしている位置取得
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                // 今回イベントでのView移動先の位置
                int left = dragView.getLeft() + (x - preX);
                int top = dragView.getTop() + (y - preY);

                // Viewを移動する
                dragView.layout(left, top, left + dragView.getWidth(), top
                        + dragView.getHeight());
                break;
        }

        // 今回のタッチ位置を保存
        preX = x;
        preY = y;

        return true;
    }
}
