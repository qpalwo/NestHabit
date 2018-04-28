package com.example.nesthabit.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.nesthabit.R;

/**
 * Created by dizzylay on 2018/4/28.
 */
public class CircleProgressBar extends View {

    private Paint backCirclePaint;
    private int backCircleColor;
    private Paint foreCirclePaint;
    private int foreCircleColor;
    private float radius;
    private float width;
    private int currentProgress;

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initVariable();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);
        radius = ta.getDimension(R.styleable.CircleProgressBar_radius, 50);
        width = ta.getDimension(R.styleable.CircleProgressBar_width, 10);
        backCircleColor = ta.getColor(R.styleable.CircleProgressBar_backCircleColor, 0xBBBBBB);
        foreCircleColor = ta.getColor(R.styleable.CircleProgressBar_foreCircleColor, 0xFF8A85);
        currentProgress = ta.getInteger(R.styleable.CircleProgressBar_currentProgress, 100);
        ta.recycle();
    }

    private void initVariable() {
        backCirclePaint = new Paint();
        backCirclePaint.setAntiAlias(true);
        backCirclePaint.setDither(true);
        backCirclePaint.setColor(backCircleColor);
        backCirclePaint.setStyle(Paint.Style.STROKE);
        backCirclePaint.setStrokeWidth(width);

        foreCirclePaint = new Paint();
        foreCirclePaint.setAntiAlias(true);
        foreCirclePaint.setDither(true);
        foreCirclePaint.setColor(foreCircleColor);
        foreCirclePaint.setStyle(Paint.Style.STROKE);
        foreCirclePaint.setStrokeWidth(width);
        foreCirclePaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        @SuppressLint("DrawAllocation") RectF oval = new RectF(
                getWidth() / 2 - radius,
                getHeight() / 2 - radius,
                getWidth() / 2 + radius,
                getHeight() / 2 + radius);
        canvas.drawArc(oval, 0, 360, false, backCirclePaint);
        if (currentProgress >= 0) {
            canvas.drawArc(oval, 90, ((float) currentProgress / 100) * 360,
                    false, foreCirclePaint);
        }
    }

    public void setCurrentProgress(int progress) {
        currentProgress = progress;
        postInvalidate();
    }
}
