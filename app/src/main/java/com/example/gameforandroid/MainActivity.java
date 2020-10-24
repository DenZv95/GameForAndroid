package com.example.gameforandroid;

import androidx.annotation.ContentView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    float x;
    Map map;
    float y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        map = new Map(this);
        map.setOnTouchListener(this::onTouch);
        setContentView(map);
    }

    //@Override
    public boolean onTouch(View v, MotionEvent event) {
        x = event.getX();
        y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                map.onClick( (int) x, (int) y );
                //map.addBubble( (int) x, (int) y );
                setContentView(map);
                break;
            case MotionEvent.ACTION_MOVE: // движение
                break;
            //case MotionEvent.ACTION_UP: // отпускание
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

}