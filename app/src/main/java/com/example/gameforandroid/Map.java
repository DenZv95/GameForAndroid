package com.example.gameforandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.getSystemService;

public class Map extends View {

    private final int size;
    private final int xNum;
    private final int yNum;
    int left;
    int top;
    private final Paint mPaint;
    List<Brick> brickList = new ArrayList<Brick>();

    public Map(Context context) {
        super(context);
        size = 150;
        xNum = 5;
        yNum = 5;
        mPaint = new Paint();
    }

    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        left = (getWidth() - (size * xNum)) / 2;
        top = (getHeight() - (size * yNum)) / 2;

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        for(int i = 0; i < xNum; i++)
        {
            for (int j = 0; j < yNum; j++)
            {
                canvas.drawRect(left + (size*i),
                                top + (size*j),
                                size+left + (size*i),
                                size+top + (size*j),
                                mPaint);
            }
        }

        for (Brick brick : brickList) {
            brick.onDraw(left,top,canvas);
        }

        canvas.save();
        // восстанавливаем холст
        canvas.restore();
    }

    public void addBrick(int xСoordinate, int yСoordinate){
        int x;
        int y;
//        left = (getWidth() - (size * xNum)) / 2;
//        top = (getHeight() - (size * yNum)) / 2;

        if (xСoordinate > left & yСoordinate > top) {
            x = (xСoordinate - left)/size;
            y = (yСoordinate - top)/size;
            if(x < xNum & y < yNum){
                brickList.add( new Brick(x, y, size) );
            }
        }
    }


}
