package com.example.gameforandroid.bubbles.states;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import com.example.gameforandroid.Map;
import com.example.gameforandroid.bubbles.Bubble;

public abstract class BubbleStates {
    Bubble bubble;

    BubbleStates(Bubble context) {
        this.bubble = context;
    }

    public abstract boolean onTouchEvent();
    public abstract void update(long dt);
    public abstract void draw(int left, int top, Canvas canvas);


}
