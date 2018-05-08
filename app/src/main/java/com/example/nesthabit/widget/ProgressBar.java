package com.example.nesthabit.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.wifi.WifiEnterpriseConfig;
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
    public static final int TYPE_CIRCLE = 0;
    public static final int TYPE_LINE = 1;

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
        currentProgress = ta.getFloat(R.styleable.ProgressBar_currentProgress, -1);
        type = ta.getInt(R.styleable.ProgressBar_type, TYPE_LINE);
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
        if (type == TYPE_CIRCLE) {
            @SuppressLint("DrawAllocation") RectF oval = new RectF(
                    getWidth() / 2 - radius,
                    getHeight() / 2 - radius,
                    getWidth() / 2 + radius,
                    getHeight() / 2 + radius);
            canvas.drawArc(oval, 0, 360, false, backCirclePaint);
            if (currentProgress > 0) {
                canvas.drawArc(oval, 90, currentProgress * 360,
                        false, foreCirclePaint);
            }
        } else if (type == TYPE_LINE) {
            canvas.drawLine(getWidth() / 2 - radius,
                    getHeight() / 2,
                    getWidth() / 2 + radius,
                    getHeight() / 2,
                    backCirclePaint);
            if (currentProgress > 0) {
                canvas.drawLine(getWidth() / 2 - radius,
                        getHeight() / 2,
                        (float) (getWidth() / 2 + radius * (currentProgress - 0.5) * 2),
                        getHeight() / 2,
                        foreCirclePaint);
            }
        }
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        setMeasuredDimension(
//                measureWidth(widthMeasureSpec),
//                measureHeight(heightMeasureSpec));
//    }

    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            if (type == TYPE_CIRCLE) {
                result = (int) (radius + width / 2);
            } else if (type == TYPE_LINE) {
                result = (int) radius;
            }
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            if (type == TYPE_CIRCLE) {
                result = (int) (radius + width / 2);
            } else if (type == TYPE_LINE) {
                result = (int) width;
            }
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    public void setCurrentProgress(float progress) {
        currentProgress = progress;
        postInvalidate();
    }
}
