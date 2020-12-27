package com.example.gameforandroid.bubbles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.gameforandroid.Cell;
import com.example.gameforandroid.Map;
import com.example.gameforandroid.bubbles.states.BubbleActiveState;
import com.example.gameforandroid.bubbles.states.BubbleMoveState;
import com.example.gameforandroid.bubbles.states.BubbleStates;
import com.example.gameforandroid.bubbles.states.BubbleStopState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bubble extends Cell {
    private Paint bubblePaint;
    Random rand;
    private BubbleStates state;
    private BubbleStates stop;
    private BubbleStates active;
    private BubbleStates move;

    private float passedWayX = 0;
    private float passedWayY = 0;

    private int lenWay = 0;
    public int passedWay = 0;
    public ArrayList<Integer> wayX;
    public ArrayList<Integer> wayY;

    public ArrayList<List<Integer>> mapObjectArray;

    int r, g, b;

    public Bubble(int xPos, int yPos, int size, Map map) {
        super(xPos, yPos, size, map);
        this.map = map;
        bubblePaint = new Paint();
        rand = new Random();
        r = rand.nextInt(256);
        g = rand.nextInt(256);
        b = rand.nextInt(256);
        initStates();


        wayX = new ArrayList<Integer>();
        wayY = new ArrayList<Integer>();
/*
        wayX.add(3);
        wayX.add(4);
        wayX.add(4);
        wayX.add(4);
        wayX.add(4);

        wayY.add(2);
        wayY.add(2);
        wayY.add(1);
        wayY.add(0);
        wayY.add(1);
        int a = 1;
*/
    }

    @Override
    protected void onDraw(int left, int top, Canvas canvas) {
        super.onDraw(left, top, canvas);
        bubblePaint.setColor(Color.argb(255, r, g, b));
        bubblePaint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(left + (size * xPos) + size / 2 + passedWayX, top + (size * yPos) + size / 2 + passedWayY, (size / 2) - 2, bubblePaint);
        state.draw(left, top, canvas);
        canvas.save();
    }

    @Override
    public void onClick() {
        state.onTouchEvent();
    }

    public void update(long dt) {

        if (Math.abs(passedWayX) > size) {
            map.moveBubble(this.getxPos(), this.getyPos(), wayX.get(passedWay), wayY.get(passedWay));
            this.setxPos(wayX.get(passedWay));
            this.setyPos(wayY.get(passedWay));
            this.passedWay += 1;
            passedWayX = 0;
        } else if (Math.abs(passedWayY) > size) {
            map.moveBubble(this.getxPos(), this.getyPos(), wayX.get(passedWay), wayY.get(passedWay));
            this.setxPos(wayX.get(passedWay));
            this.setyPos(wayY.get(passedWay));
            this.passedWay += 1;
            passedWayY = 0;
        }
        state.update(dt);
    }

    private void initStates() {
        stop = new BubbleStopState(this);
        active = new BubbleActiveState(this);
        move = new BubbleMoveState(this);
        state = stop;
    }

    public void changeState(BubbleStates state) {

        this.state = state;
    }

    public BubbleStates getActiveState() {
        return active;
    }

    public BubbleStates getMoveState() {
        return move;
    }

    public BubbleStates getStopState() {
        return stop;
    }

    public void setPassedWayX(float speedX) {
        findWave(0, 0, 4, 4);
        this.passedWayX = 0;
    }

    public void setPassedWayY(float speedY) {
        this.passedWayY = speedY;
    }

    public void drosselX(float speedX) {
        this.passedWayX += speedX;
    }

    public void drosselY(float speedY) {
        this.passedWayY += speedY;
    }

    public void tickPassedWay() {

    }

    public int getPassedWay() {
        return passedWay;
    }

    @Override
    public int getType() {
        return -3;
    }

    public boolean findWave(int finishX, int fisnishY, int xUnit, int yUnit)
    {
        this.mapObjectArray = map.getMapObjectArray();
        this.mapObjectArray.get(finishX).set(fisnishY, 0);
        boolean add = true;
        int step = 0;
        this.mapObjectArray.get(xPos).set(yPos,-1);
        while (add == true)
        {
            add = false;
            for (int x = 0; x < map.xNum; x++)
            {
                for (int y = 0; y < map.yNum; y++)
                {
                    if (this.mapObjectArray.get(x).get(y) == step)
                    {

                        if (x - 1 >= 0 && this.mapObjectArray.get(x - 1).get(y) > -2 // <======
                                && this.mapObjectArray.get(x - 1).get(y) == -1)
                            this.mapObjectArray.get(x - 1).set(y, step + 1);

                        if (y - 1 >= 0 && this.mapObjectArray.get(x).get(y - 1) > -2// <======
                                && this.mapObjectArray.get(x).get(y - 1) == -1)
                            this.mapObjectArray.get(x).set(y - 1, step + 1);

                        if (x + 1 < map.xNum && this.mapObjectArray.get(x + 1).get(y) > -2 // <======
                                && this.mapObjectArray.get(x + 1).get(y) == -1)
                            this.mapObjectArray.get(x + 1).set(y, step + 1);

                        if (y + 1 < map.yNum && this.mapObjectArray.get(x).get(y + 1) > -2 // <======
                                && this.mapObjectArray.get(x).get(y + 1) == -1)
                            this.mapObjectArray.get(x).set(y + 1, step + 1);
                    }
                }
            }
            step++;

            add = true;
            if (this.mapObjectArray.get(xUnit).get(yUnit) > 0)
            { //решение найдено
                add = false;

                //searchWay(xPos, yPos, finishX, fisnishY);
                searchWay(4, 4, 0, 0, 0);
                wayX.remove(0);
                wayY.remove(0);
                changeState(getMoveState());
                return true;
            }
            if (step > map.yNum * map.xNum) //решение не найдено, если шагов больше чем клеток
            {
                add = false;
                //this.act = false;
            }

        }
        return false;
    }

    private boolean searchWay(int X, int Y, int fX, int fY , int deep)
    {
    if (fX == X && fY == Y)
    {
        lenWay = deep;
        //wayX.set(deep,fX);
        //wayY.set(deep,fY);
        wayX.add(fX);
        wayY.add(fY);
 //       System.out.println(Y);
        return true;
    }

    if (fY - 1 > 0)
    {
        if(mapObjectArray.get(fX).get(fY) < mapObjectArray.get(fX).get(fY-1) )
        {
            if (searchWay(X, Y, fX, fY-1, deep + 1)) {
                wayX.add(fX);
                wayY.add(fY);
                return true;
            }
        }
    }


    if (fX + 1 < map.xNum)
    {
        if(mapObjectArray.get(fX).get(fY) < mapObjectArray.get(fX+1).get(fY) ) {
            if (searchWay(X, Y, fX + 1, fY, deep + 1)) {
                wayX.add(fX);
                wayY.add(fY);
                return true;
            }
        }
    }

    if (fY + 1 < map.yNum)
    {
        if(mapObjectArray.get(fX).get(fY) < mapObjectArray.get(fX).get(fY+1) ) {
            if (searchWay(X, Y , fX, fY+ 1, deep + 1)) {
                wayX.add(fX);
                wayY.add(fY);
                return true;
            }
        }
    }

    if (fX - 1 > 0)
    {
        if(mapObjectArray.get(fX).get(fY) < mapObjectArray.get(fX-1).get(fY) ) {
            if (searchWay(X, Y, fX - 1, fY, deep + 1)) {
                wayX.add(fX);
                wayY.add(fY);
                return true;
            }
        }
    }
    return false;
    }
}

