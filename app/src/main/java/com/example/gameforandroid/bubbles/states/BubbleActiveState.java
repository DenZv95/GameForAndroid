package com.example.gameforandroid.bubbles.states;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.gameforandroid.bubbles.Bubble;

public class BubbleActiveState extends BubbleStates{

    private Paint bubbleActivePaint;

    public BubbleActiveState(Bubble context) {
        super(context);
        bubbleActivePaint = new Paint();
        bubbleActivePaint.setColor(Color.RED);
        bubbleActivePaint.setStyle(Paint.Style.STROKE);
        bubbleActivePaint.setStrokeWidth(15);
    }

    @Override
    public void update(long dt) {


    }

    @Override
    public void draw(int left, int top, Canvas canvas) {
        int size = bubble.getSize();
        //canvas.drawCircle( left + (size*xPos) + size/2, top + (size*yPos) + size/2, (size/2)-2, bubblePaint);
        canvas.drawCircle( left + size*bubble.getxPos() + size/2, top + size*bubble.getyPos() + size/2, ((size - 30)/2)-2, bubbleActivePaint);

    }

    @Override
    public boolean onTouchEvent() {
        bubble.changeState(bubble.getStopState());
       // bubble.map.activeBubble = null;
        bubble.map.activeBubble = false;
        return false;
    }
}
