package com.example.gameforandroid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Brick extends Cell {
    private Paint brickPaint;
    public Brick(int xPos, int yPos, int size, Map map) {
        super(xPos, yPos, size, map);
        brickPaint = new Paint();
    }

    @Override
    protected void onDraw(int left, int top, Canvas canvas) {
        super.onDraw(left, top, canvas);
        brickPaint.setColor(Color.BLACK);
        brickPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(left + (size*xPos),
                top + (size*yPos),
                size+left + (size*xPos),
                size+top + (size*yPos),
                brickPaint);
        canvas.save();
    }

    @Override
    public int getType() {
        return -2;
    }

    @Override
    public void addFinish(int X, int Y) {
        super.addFinish(X, Y);
    }
}
