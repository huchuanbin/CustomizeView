package com.example.huchuanbin.customizeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by huchuanbin on 16/4/7.
 */
public class CustomTitleView extends View {
    private String titleText;
    private int titleSize;
    private int titleColor;

    private Rect rect;
    private Paint paint;

    private TypedArray typedArray;

    public CustomTitleView(Context context) {
        this(context, null);
    }

    public CustomTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyleAttr, 0);
        int n = typedArray.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.CustomTitleView_titleText:
                    titleText = typedArray.getString(attr);
                    break;
                case R.styleable.CustomTitleView_titleSize:
                    titleSize = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomTitleView_titleColor:
                    titleColor = typedArray.getColor(attr, Color.BLACK);
                    break;
            }
        }
        typedArray.recycle();
        paint = new Paint();
        paint.setTextSize(titleSize);
        rect = new Rect();
        paint.getTextBounds(titleText, 0, titleText.length(), rect);
        this.setOnClickListener(view -> {
            titleText = randomText();
            postInvalidate();
        });

    }

    private String randomText() {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4)
        {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set)
        {
            sb.append("" + i);
        }

        return sb.toString();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            paint.setTextSize(titleSize);
            paint.getTextBounds(titleText, 0, titleText.length(), rect);
            float textWidth = rect.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;

        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            paint.setTextSize(titleSize);
            paint.getTextBounds(titleText, 0, titleText.length(), rect);
            float textHeight = rect.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);
        paint.setColor(titleColor);
        canvas.drawText(titleText, getWidth() / 2 - rect.width() / 2, getHeight() / 2 + rect.height() / 2, paint);
    }

}
