package com.liftlockstudios.pickmeup;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Player {

    private Bitmap sprite;
    private int x, y;
    private int vx, vy;


    public Player(Context context) {
        x = 10;
        y = 10;
        vx = 0;
        vy = 0;
        sprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.bunny);
    }


    public void update() {

    }

    public Bitmap getSprite() {
        return sprite;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }



}
