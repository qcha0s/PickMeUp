package com.liftlockstudios.pickmeup;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Player {

    private Bitmap sprite;
    private int x, y;
    private int vx, vy;
    private boolean jumping;

    private final int GRAVITY = -60;
    private int minY;
    private int maxY;
    private int minX;
    private int maxX;



    public Player(Context context, int screenW, int screenH) {
        x = 10;
        y = 10;
        vx = 0;
        vy = 0;
        sprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.bunny);
        jumping = false;

        maxY = screenH - sprite.getHeight() - 70; //FIXME: Magic Number
        minY = 0;

        maxX = screenW - sprite.getWidth();
        minX = 0;


    }


    public void update() {
        if(jumping) {
            y -= 120; //FIXME: Magic Number
        } else {
            y -= GRAVITY;               // gravity is a negative number
        }

        //TODO: clean this up and make
        if (y < minY) {
            y = minY;
        }

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

    public void setJumping() {
        jumping = true;
    }

    public void stopJumping() {
        jumping = false;
    }



}
