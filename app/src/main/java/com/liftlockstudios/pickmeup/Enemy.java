package com.liftlockstudios.pickmeup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Enemy {

    //TODO: clean this and player up to use a consistant base class.
    private Bitmap sprite;
    private int x, y;
    private int vX, vY;

    private final int GRAVITY = -60;

    private int minX;
    private int maxX;

    private int minY;
    private int maxY;




    public Enemy(Context context, int screenW, int screenH) {

        sprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);

        minX = 0;
        maxX = screenW - sprite.getWidth();

        minY = 0;
        maxY = screenH - sprite.getHeight() - 70;

        x = maxX - 100;
        y = minY;

    }


    public void update() {

        y -= GRAVITY;               // gravity is a negative number

        if (y > maxY) {
            y = maxY;
        }




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
