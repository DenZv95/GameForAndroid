package com.example.gameforandroid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Bubble extends Cell{
    private Paint bubblePaint;

    public Bubble(int xPos, int yPos, int size) {
        super(xPos, yPos, size);
        bubblePaint = new Paint();
    }

    @Override
    protected void onDraw(int left, int top, Canvas canvas) {
        super.onDraw(left, top, canvas);
        bubblePaint.setColor(Color.YELLOW);
        bubblePaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle( left + (size*xPos) + size/2, top + (size*yPos) + size/2, (size/2)-2, bubblePaint);
        canvas.save();
    }

}
