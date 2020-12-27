package com.example.gameforandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.gameforandroid.bubbles.Bubble;

import java.util.ArrayList;
import java.util.List;

public class Map extends View {
    private Paint LinePaint;
    private final int size;
    public final int xNum;
    public final int yNum;
    private int left;
    private int top;
    private Hud hud;
    private ArrayList<List<Cell>> mapObjectList;

    private ArrayList<List<Integer>> mapObjectArray;

    public boolean activeBubble;
    public int activeBubbleX;
    public int activeBubbleY;

    public Map(Context context) {
        super(context);

        size = 180;
        xNum = 5;
        yNum = 5;

        hud = new Hud();
        mapObjectList = new ArrayList<>(xNum);

        activeBubbleX = 0;
        activeBubbleY = 0;

        for(int _i = 0; _i < xNum; _i++) {
            mapObjectList.add(new ArrayList<Cell>(yNum));
        }

        for(int _i = 0; _i < xNum; _i++) {
            for(int _j = 0; _j < yNum; _j++) {
                mapObjectList.get(_i).add( new Cell(_i, _j, size, this) );
            }
        }


        //mapObjectList.get(4).add(2 , new Brick(4,2,size, this) );
       // mapObjectList.get(3).add(4 , new Brick(3,4,size, this) );
        //mapObjectList.get(4).set(3 , new Bubble(4,3,size, this) );
        activeBubble = false;
    }

    public void update(long dt) {
        for (List<Cell> mapObjects : mapObjectList) {
            for (Cell mapObject : mapObjects){
                mapObject.update(dt);
            }
        }
    }

    //@Override
    //protected void onDraw(Canvas canvas , double averageFPS) {
    public void draw(Canvas canvas , double averageFPS) {

       // super.onDraw(canvas);
        left = (canvas.getWidth() - (size * xNum)) / 2;
        top = (canvas.getHeight() - (size * yNum)) / 2;


        hud.onDraw(canvas , averageFPS);

        for (List<Cell> mapObjects : mapObjectList) {
            for (Cell mapObject : mapObjects){
                //mapObject.setColor(Color.YELLOW);
               //LinePrint.setStyle(Paint.Style.FILL);
                mapObject.onDraw(left,top,canvas);
            }
        }

        canvas.save();
        //canvas.restore();
    }

    public void onClick(int xc, int yc){

        int x;
        int y;
        boolean add = true;

        if (xc > left & yc > top) {
            x = (xc - left)/size;
            y = (yc - top)/size;
            if(x < xNum & y < yNum){
                //mapObjectList.get(x).get(y).onClick();
                //int a = 1;
                if (hud.getSTATE() == 1){
                    if (mapObjectList.get(x).get(y).getType() == -3) {
                        mapObjectList.get(x).get(y).onClick();
                    }
                    else if (mapObjectList.get(x).get(y).getType() == -1){
                        if (activeBubble){
                            activeBubble = false;
                            mapObjectList.get(activeBubbleX).get(activeBubbleY).addFinish(x,y);
                        }
                    }
                }
                else if (hud.getSTATE() == 2){
                    mapObjectList.get(x).set(y , hud.getUnit(x,y,size,this) );
                }
                else if (hud.getSTATE() == 3){
                    mapObjectList.get(x).set(y , hud.getUnit(x,y,size,this) );
                }
                else {
                    mapObjectList.get(x).set(y , hud.getUnit(x,y,size,this) );
                }

            }
        }
        else {
            hud.onClick(xc,yc);
        }

    }

    public void moveBubble(int mapPosX, int mapPosY, int bubblePosX, int bubblePosY){

        mapObjectList.get(bubblePosX).set(bubblePosY,mapObjectList.get(mapPosX).get(mapPosY));
        mapObjectList.get(mapPosX).set(mapPosY, new Cell(mapPosX, mapPosY, size, this));

    }

    public ArrayList<List<Integer>> getMapObjectArray(){
        mapObjectArray = new ArrayList<>(xNum);

        for(int _i = 0; _i < xNum; _i++) {
            mapObjectArray.add(new ArrayList<Integer>(yNum));
        }

        for(int _i = 0; _i < xNum; _i++) {
            for(int _j = 0; _j < yNum; _j++) {
                mapObjectArray.get(_i).add(mapObjectList.get(_i).get(_j).getType());
            }
        }
        return mapObjectArray;
    }


}
