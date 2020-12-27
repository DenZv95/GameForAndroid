package com.example.gameforandroid.bubbles.states;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.gameforandroid.bubbles.Bubble;

import java.util.ArrayList;

public class BubbleMoveState extends BubbleStates {
    private Paint bubbleActivePaint;


    public BubbleMoveState(Bubble context) {
        super(context);
        bubbleActivePaint = new Paint();
        bubbleActivePaint.setColor(Color.GREEN);
        bubbleActivePaint.setStyle(Paint.Style.STROKE);
        bubbleActivePaint.setStrokeWidth(15);



    }

    @Override
    public void update(long dt) {
        if (bubble.passedWay < bubble.wayX.size() ) {

            if (bubble.getxPos() < bubble.wayX.get(bubble.getPassedWay())) {
                bubble.drosselX((float) (0.3 * dt));
            }
            //Лево
            else if (bubble.getxPos() > bubble.wayX.get(bubble.getPassedWay())) {
                bubble.drosselX((float) (0.3 * dt * -1));
            }
            //Верх
            else if (bubble.getyPos() > bubble.wayY.get(bubble.getPassedWay())) {
                bubble.drosselY((float) (0.3 * dt * -1));
            }
            //Низ
            else if (bubble.getyPos() < bubble.wayY.get(bubble.getPassedWay())){
                bubble.drosselY((float) (0.3 * dt));
            }
        }
        else {
            bubble.changeState(bubble.getStopState());
        }
    }

    @Override
    public void draw(int left, int top, Canvas canvas) {
        //int size = bubble.getSize();
        //canvas.drawCircle( left + size*bubble.getxPos() + size/2, top + size*bubble.getyPos() + size/2, ((size - 30)/2)-2, bubbleActivePaint);

    }

    @Override
    public boolean onTouchEvent() {

        //lenWay = 0;
        //speedX = 0;
        return false;
    }
}
