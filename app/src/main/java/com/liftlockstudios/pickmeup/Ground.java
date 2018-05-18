package com.liftlockstudios.pickmeup;

public class Ground extends GameObject {

    Ground(float worldStartX, float worldStartY, char type) {

        final float HEIGHT = 1;
        final float WIDTH = 1;

        setHeight(HEIGHT);
        setWidth(WIDTH);
        setType(type);

        setSpriteName("ground");
        setWorldLocation(worldStartX, worldStartY, 0);
        setRectHitbox();
    }

    public void update(long fps, float gravity) { }


}
