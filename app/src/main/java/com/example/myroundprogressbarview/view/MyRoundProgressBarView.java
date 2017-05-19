package com.example.myroundprogressbarview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.myroundprogressbarview.R;

/**
 * Created by lenovo on 2017/5/19.
 */

public class MyRoundProgressBarView extends View {

    private final int max;
    private final Paint paint;
    private final int roundColor;
    private final int roundProgressColor;
    private final int textColor;
    private final float textSize;
    private final float roundWidth;
    private final boolean textShow;
    private float progress;


    public MyRoundProgressBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyRoundProgressBarView);
        max = typedArray.getInteger(R.styleable.MyRoundProgressBarView_max, 100);
        roundColor = typedArray.getColor(R.styleable.MyRoundProgressBarView_roundColor, Color.RED);
        roundProgressColor = typedArray.getColor(R.styleable.MyRoundProgressBarView_roundProgressColor, Color.BLUE);
        textColor = typedArray.getColor(R.styleable.MyRoundProgressBarView_textColor, Color.GREEN);
        textSize = typedArray.getDimension(R.styleable.MyRoundProgressBarView_textSize, 55);
        roundWidth = typedArray.getDimension(R.styleable.MyRoundProgressBarView_roundWidth, 10);
        textShow = typedArray.getBoolean(R.styleable.MyRoundProgressBarView_textShow, true);
        typedArray.recycle();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int center = getWidth() / 2;
        float radius = center - roundWidth / 2;
        paint.setColor(roundColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(roundWidth);
        paint.setAntiAlias(true);
        canvas.drawCircle(center, center, radius, paint);
        paint.setColor(textColor);
        paint.setStrokeWidth(0);
        paint.setTextSize(textSize);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        int percent = (int) (progress / (float) max * 100);
        String strPercent = percent + "%";
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        if (percent != 0) {
            canvas.drawText(strPercent, getWidth() / 2 - paint.measureText(strPercent) / 2, getWidth() / 2 + (fm.bottom - fm.top) / 2 - fm.bottom, paint);
        }
        RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius);
        paint.setColor(roundProgressColor);
        paint.setStrokeWidth(roundWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(oval, 0, 360 * progress / max, false, paint);
    }

    public void setProgress(int progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("进度Progress不能小于0");
        }
        if (progress > max) {
            progress = max;
        }
        if (progress <= max) {
            this.progress = progress;
            postInvalidate();
        }
    }
}
