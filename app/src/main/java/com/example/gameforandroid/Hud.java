package com.example.gameforandroid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.gameforandroid.bubbles.Bubble;

public class Hud {

    private Paint cellPaint;
    private Paint brickPaint;
    private Paint bubblePaint;
    private Paint bubbleActivePaint;
    private Paint statePaint;

    private int STATE = 0;
    private int size, left;
    private int top = 50;

    public Hud() {
        this.size = 150;
        cellPaint = new Paint();
        cellPaint.setColor(Color.BLACK);
        cellPaint.setStyle(Paint.Style.STROKE);
        cellPaint.setStrokeWidth(3);

        brickPaint = new Paint();
        brickPaint.setColor(Color.BLACK);
        brickPaint.setStyle(Paint.Style.FILL);

        bubblePaint = new Paint();
        bubblePaint.setColor(Color.YELLOW);
        bubblePaint.setStyle(Paint.Style.FILL);

        bubbleActivePaint = new Paint();
        bubbleActivePaint.setColor(Color.RED);
        bubbleActivePaint.setStyle(Paint.Style.STROKE);
        bubbleActivePaint.setStrokeWidth(15);

        statePaint = new Paint();
        statePaint.setColor(Color.GREEN);
        statePaint.setStyle(Paint.Style.FILL);
    }

    public void onDraw(Canvas canvas) {
        left = (canvas.getWidth() - (size * 4)) / 2;

        for (int i = 0; i < 4; i++)
        {
            canvas.drawRect(left + (size * i),
                    top,
                    size+left + (size * i),
                    size+top,
                    cellPaint);
        }


        //STATE
        canvas.drawRect(left + (size * STATE),
                top,
                size+left + (size * STATE),
                size+top,
                statePaint);

        canvas.drawCircle( left + (size )/2, top + (size)/2, ((size - 20)/2)-2, bubblePaint);

        canvas.drawCircle( left + (size) + size/2, top  + size/2, ((size - 20)/2)-2, bubblePaint);
        canvas.drawCircle( left + (size) + size/2, top  + size/2, ((size - 30)/2)-2, bubbleActivePaint);

        canvas.drawRect(left + (size*2) + 20,
                top + (20),
                size+left + (size*2) - 20,
                size+top + (size*0) - 20,
                brickPaint);

        canvas.drawRect(left + (size*3) + 20,
                top + (20),
                size+left + (size*3) - 20,
                size+top - 20,
                cellPaint);

        canvas.save();
    }

    public Cell getUnit(int xPos, int yPos, int size, Map map) {
        switch(STATE) {
            case 1:
                return new Bubble(xPos, yPos, size, map);
            case 2:
                return new Brick(xPos, yPos, size, map);
            case 3:
                return new Cell(xPos, yPos, size, map);
            default:
                return new Bubble(xPos, yPos, size, map);
        }
    }

    public void onClick(int xc, int yc){
        int x;
        int y;
        if (xc > left & yc > top) {
            x = (xc - left)/size;
            y = (yc - top)/size;
            if(x < 4 & y < 1){
                STATE = x;
            }
        }
    }

}
