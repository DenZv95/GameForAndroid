package com.example.gameforandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Map extends View {

    private final int size;
    private final int xNum;
    private final int yNum;
    private final Paint mPaint;

    public Map(Context context) {
        super(context);
        size = 150;
        xNum = 5;
        yNum = 5;
        mPaint = new Paint();
    }

    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        int left = (getWidth() - (size * xNum)) / 2;
        int top = (getHeight() - (size * yNum)) / 2;

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        for(int i = 0; i < xNum; i++)
        {
            for (int j = 0; j < yNum; j++)
            {
                canvas.drawRect(left + (size*i),
                                top + (size*j),
                                size+ left + (size*i),
                                size+ top + (size*j),
                                mPaint);
            }
        }
        canvas.save();
        // восстанавливаем холст
        canvas.restore();

    }
}
