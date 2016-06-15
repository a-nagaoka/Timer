package com.example.apptimer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.InputStream;

/**
 * TODO: document your custom view class.
 */
//public class GifImageView extends ImageView {
//
//    private Movie mMovie;
//
//    private long mMoviestart;
//
//    public GifImageView(Context context, InputStream stream) {
//        super(context);
//        mMovie = Movie.decodeStream(stream);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        canvas.drawColor(Color.TRANSPARENT);
//        super.onDraw(canvas);
//        final long now = SystemClock.uptimeMillis();
//
//        if (mMoviestart == 0) {
//            mMoviestart = now;
//        }
//
//        final int relTime = (int) ((now - mMoviestart) % mMovie.duration());
//        mMovie.setTime(relTime);
//        mMovie.draw(canvas, 10, 10);
//        this.invalidate();
//    }
//}
//public class GifImageView extends View
//{
//    private Movie mMovie;
//    private static final String TAG = "View";
//    private boolean roopPlay = true;
//    private int disp_co_x = 0;
//    private int disp_co_y = 0;
//
//    private long mMoviestart;
//
//    public GifImageView(Context context, InputStream stream) {
//        super(context);
//
//        mMovie = Movie.decodeStream(stream);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        canvas.drawColor(Color.TRANSPARENT);
//        super.onDraw(canvas);
//        final long now = SystemClock.uptimeMillis();
//
//        if (mMoviestart == 0) {
//            mMoviestart = now;
//        }
//
//        final int relTime;
//        if (roopPlay == true){ //ループする場合
//            relTime = (int)((now - mMoviestart) % mMovie.duration());
//        } else {
//            relTime = (int)(now - mMoviestart);
//        }
//        Log.d(TAG,canvas.getHeight() + " + " + mMovie.height());
//
//        final LinearLayout parent = (LinearLayout) getParent();
//        //中央に配置
//        disp_co_x = (parent.getWidth()- mMovie.width())/2;
//        disp_co_y = parent.getTop() + (parent.getHeight()- mMovie.height())/2;
//
//        mMovie.setTime(relTime);
//        mMovie.draw(canvas, disp_co_x, disp_co_y);
//        this.invalidate();
//    }
//}
public class GifImageView extends ImageView
{
    private boolean mIsPlayingGif = false;

    private GifDecoder mGifDecoder;

    private Bitmap mTmpBitmap;

    final Handler mHandler = new Handler();

    final Runnable mUpdateResults = new Runnable() {
        public void run() {
            if (mTmpBitmap != null && !mTmpBitmap.isRecycled()) {
                GifImageView.this.setImageBitmap(mTmpBitmap);
            }
        }
    };

    public GifImageView(Context context, InputStream stream) {
        super(context);
        playGif(stream);
    }

    private void playGif(InputStream stream) {
        mGifDecoder = new GifDecoder();
        mGifDecoder.read(stream);

        mIsPlayingGif = true;

        new Thread(new Runnable() {
            public void run() {
                final int n = mGifDecoder.getFrameCount();
                final int ntimes = mGifDecoder.getLoopCount();
                int repetitionCounter = 0;
                do {
                    for (int i = 0; i < n; i++) {
                        mTmpBitmap = mGifDecoder.getFrame(i);
                        int t = mGifDecoder.getDelay(i);
                        mHandler.post(mUpdateResults);
                        try {
                            Thread.sleep(t);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if(ntimes != 0) {
                        repetitionCounter ++;
                    }
                } while (mIsPlayingGif && (repetitionCounter <= ntimes));
            }
        }).start();
    }

    public void stopRendering() {
        mIsPlayingGif = true;
    }
}
