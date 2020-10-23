package com.example.gameforandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Brick {
    private Paint mPaint;
    private int xPos,yPos,size;


    public Brick(int xPos, int yPos, int size) {
        this.size = size;
        this.xPos = xPos;
        this.yPos = yPos;
        mPaint = new Paint();
    }

    protected void onDraw(int left , int top, Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(left + (size*xPos),
                        top + (size*yPos),
                        size+left + (size*xPos),
                        size+top + (size*yPos),
                        mPaint);
        canvas.save();
           // }
        ///}
        //canvas.save();
        // восстанавливаем холст
        //canvas.restore();
    }


}
