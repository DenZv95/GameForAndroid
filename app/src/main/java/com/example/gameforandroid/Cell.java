package com.example.gameforandroid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Cell {
    private Paint mPaint;
    protected int xPos;
    protected int yPos;
    protected int size;
    protected Map map;

    public Cell(int xPos, int yPos, int size , Map map) {
        this.size = size;
        this.xPos = xPos;
        this.yPos = yPos;
        mPaint = new Paint();
    }

    public void update(long dt) {
        //changeState(active);
    }

    protected void onDraw(int left , int top, Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        canvas.drawRect(left + (size*xPos),
                top + (size*yPos),
                size+left + (size*xPos),
                size+top + (size*yPos),
                mPaint);
        canvas.save();
    }

    public void onClick(){}

    public int getSize() {
        return size;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getType(){
        return -1;
    }
}
