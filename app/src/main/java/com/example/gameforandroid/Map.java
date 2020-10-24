package com.example.gameforandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Map extends View {

    private final int size;
    private final int xNum;
    private final int yNum;
    private int left;
    private int top;
    private Hud hud;
    private ArrayList<List<Cell>> mapObjectList;

    public Map(Context context) {
        super(context);

        size = 150;
        xNum = 5;
        yNum = 5;

        hud = new Hud();
        mapObjectList = new ArrayList<>(xNum);

        for(int _i = 0; _i < xNum; _i++) {
            mapObjectList.add(new ArrayList<Cell>(yNum));
        }

        for(int _i = 0; _i < xNum; _i++) {
            for(int _j = 0; _j < yNum; _j++) {
                mapObjectList.get(_i).add( new Cell(_i, _j, size) );
            }
        }

        //mapObjectList.get(1).add(1 , new Bubble(1,1,size) );
        //mapObjectList.get(2).add(2 , new Brick(2,2,size) );
        //mapObjectList.get(3).add(3 , new Bubble(3,3,size) );
    }

    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        left = (getWidth() - (size * xNum)) / 2;
        top = (getHeight() - (size * yNum)) / 2;

        hud.onDraw(canvas);

        for (List<Cell> mapObjects : mapObjectList) {
            for (Cell mapObject : mapObjects){
                mapObject.onDraw(left,top,canvas);
            }
        }

        //canvas.save();
        canvas.restore();
    }

    public void onClick(int xc, int yc){

        int x;
        int y;
        boolean add = true;

        if (xc > left & yc > top) {
            x = (xc - left)/size;
            y = (yc - top)/size;
            if(x < xNum & y < yNum){

                mapObjectList.get(x).set(y , hud.getUnit(x,y,size));
                int a = 1;
            }
        }
        else {
            hud.onClick(xc,yc);
        }

    }


}
