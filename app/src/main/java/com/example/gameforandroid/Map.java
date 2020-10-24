package com.example.gameforandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class Map extends View {

    private final int size;
    private final int xNum;
    private final int yNum;
    private int left;
    private int top;
    ArrayList<List<Cell>> mapObjectList;

    public Map(Context context) {
        super(context);

        size = 150;
        xNum = 5;
        yNum = 5;

        mapObjectList = new ArrayList<>(xNum);

        for(int _i = 0; _i < xNum; _i++) {
            mapObjectList.add(new ArrayList<Cell>(yNum));
        }

        for(int _i = 0; _i < xNum; _i++) {
            for(int _j = 0; _j < yNum; _j++) {
                mapObjectList.get(_i).add( new Cell(_i, _j, size) );
            }
        }

        mapObjectList.get(1).add(1 , new Bubble(1,1,size) );
        mapObjectList.get(2).add(2 , new Brick(2,2,size) );
        mapObjectList.get(3).add(3 , new Bubble(3,3,size) );
    }

    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        left = (getWidth() - (size * xNum)) / 2;
        top = (getHeight() - (size * yNum)) / 2;

        for (List<Cell> mapObjects : mapObjectList) {
            for (Cell mapObject : mapObjects){
                mapObject.onDraw(left,top,canvas);
            }
        }

        canvas.save();
        canvas.restore();
    }

    public void addBrick(int xСoordinate, int yСoordinate){
        /*
        int x;
        int y;
        boolean add = true;

        if (xСoordinate > left & yСoordinate > top) {
            x = (xСoordinate - left)/size;
            y = (yСoordinate - top)/size;
            if(x < xNum & y < yNum){

                for(int i = 0; i < brickList.size(); i++) {
                    if(brickList.get(i).getX() == x && brickList.get(i).getY() == y){
                        rowList.get(x)[y] = -1;
                        brickList.remove(i);
                        add = false;
                        break;
                    }
                }

                for(int i = 0; i < bubbleList.size(); i++) {
                    if(bubbleList.get(i).getX() == x && bubbleList.get(i).getY() == y){
                        //bubbleList.remove(i);
                        add = false;
                        break;
                    }
                }

                if(add){
                    rowList.get(x)[y] = -3;
                    brickList.add( new Brick(x, y, size) );
                }
            }
        }
         */
    }

    public void addBubble(int xСoordinate, int yСoordinate){
        /*
        int x;

        int y;
        boolean add = true;

        if (xСoordinate > left & yСoordinate > top) {
            x = (xСoordinate - left)/size;
            y = (yСoordinate - top)/size;
            if(x < xNum & y < yNum){
                for(int i = 0; i < brickList.size(); i++) {
                    if(brickList.get(i).getX() == x && brickList.get(i).getY() == y){
                        //brickList.remove(i);
                        add = false;
                        break;
                    }
                }
                for(int i = 0; i < bubbleList.size(); i++) {
                    if(bubbleList.get(i).getX() == x && bubbleList.get(i).getY() == y){
                        rowList.get(x)[y] = -1;
                        bubbleList.remove(i);
                        add = false;
                        break;
                    }
                }
                if(add){
                    rowList.get(x)[y] = -2;
                    bubbleList.add( new Bubble(x, y, size) );
                }
            }
        }

        */
    }

}
