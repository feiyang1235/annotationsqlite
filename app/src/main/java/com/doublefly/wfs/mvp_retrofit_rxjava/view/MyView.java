package com.doublefly.wfs.mvp_retrofit_rxjava.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by WFS on 2017/4/7.
 */
public class MyView extends View{
    private static final String TAG = "MyView";
    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        MeasureSpec.UNSPECIFIED
        Log.i(TAG, "onMeasure width: "+MeasureSpec.getMode(widthMeasureSpec));
        Log.i(TAG, "onMeasure height: "+MeasureSpec.getMode(heightMeasureSpec));
        setMeasuredDimension(200,200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint(); 
        p.setColor(0xffffffff);
        p.setAntiAlias(true);
        canvas.drawLine(0,0,200,200,p);
    }
}
