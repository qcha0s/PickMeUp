package com.liftlockstudios.pickmeup;


import android.content.Context;



public class Player extends GameObject {

    Player(Context context, float worldStartX, float worldStartY, int pixelsPerMeter) {
        final float HEIGHT = 2;
        final float WIDTH = 1;

        setWidth(WIDTH);
        setHeight(HEIGHT);
        setType('p');
        setSpriteName("bunny");

        setWorldLocation(worldStartX, worldStartY, 0);

    }

    public void update(long fps, float gravity) { }

}
