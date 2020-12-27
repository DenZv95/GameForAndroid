package com.example.gameforandroid.bubbles.states;

import android.graphics.Canvas;

import com.example.gameforandroid.Map;
import com.example.gameforandroid.bubbles.Bubble;

public class BubbleStopState extends BubbleStates {

    public BubbleStopState(Bubble context) {
        super(context);
    }

    @Override
    public void update(long dt) {

    }

    @Override
    public void draw(int left, int top, Canvas canvas) {

    }

    @Override
    public boolean onTouchEvent() {

        //bubble.map.activeBubble = bubble;
        bubble.map.activeBubble = true;
        bubble.map.activeBubbleX = bubble.getxPos();
        bubble.map.activeBubbleY = bubble.getyPos();
        bubble.changeState(bubble.getActiveState());
        return false;
    }

}
