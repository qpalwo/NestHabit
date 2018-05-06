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
public class ProgressBar extends View {

    private Paint backCirclePaint;
    private int backCircleColor;
    private Paint foreCirclePaint;
    private int foreCircleColor;
    private float radius;
    private float width;
    private float currentProgress;
    private int type;

    public ProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initVariable();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ProgressBar);
        radius = ta.getDimension(R.styleable.ProgressBar_radius, 50);
        width = ta.getDimension(R.styleable.ProgressBar_width, 10);
        backCircleColor = ta.getColor(R.styleable.ProgressBar_backCircleColor, 0xBBBBBB);
        foreCircleColor = ta.getColor(R.styleable.ProgressBar_foreCircleColor, 0xFF8A85);
        currentProgress = 0;
        //currentProgress = ta.getInteger(R.styleable.ProgressBar_currentProgress, -1);
        type = ta.getInt(R.styleable.ProgressBar_type, 1);
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
        if (type == 0) {
            @SuppressLint("DrawAllocation") RectF oval = new RectF(
                    getWidth() / 2 - radius,
                    getHeight() / 2 - radius,
                    getWidth() / 2 + radius,
                    getHeight() / 2 + radius);
            canvas.drawArc(oval, 0, 360, false, backCirclePaint);
            if (currentProgress > 0) {
                canvas.drawArc(oval, 90, currentProgress  * 360,
                        false, foreCirclePaint);
            }
        } else if (type == 1) {
            canvas.drawLine(0, 0, getWidth(), 0, backCirclePaint);
            if (currentProgress > 0){
                canvas.drawLine(0, 0, currentProgress / 100 * getWidth(), 0, foreCirclePaint);
            }
        }
    }

    public void setCurrentProgress(float progress) {
        currentProgress = progress;
        postInvalidate();
    }
}
