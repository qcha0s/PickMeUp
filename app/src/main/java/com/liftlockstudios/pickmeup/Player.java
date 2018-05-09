package com.liftlockstudios.pickmeup;


import android.content.Context;



public class Player extends GameObject {

    final float MAX_X_VELOCITY = 10f;
    public boolean m_isPressingRight = false;

    private boolean m_isJumping;
    private long m_jumpTime;
    private long m_maxJumpTime = 700; // 7/10ths of a second


    Player(Context context, float worldStartX, float worldStartY, int pixelsPerMeter) {
        final float HEIGHT = 2;
        final float WIDTH = 1;


        setWidth(WIDTH);
        setHeight(HEIGHT);
        setType('p');
        setSpriteName("bunny");

        setWorldLocation(worldStartX, worldStartY, 0);

        setVX(0);
        setVY(0);
        setFacing(RIGHT);
        m_isJumping = false;

        canMove(true);
        setActive(true);
        setVisible(true);


    }

    public void update(long fps, float gravity) {

        if(m_isPressingRight) {
            setVX(MAX_X_VELOCITY);
        } else {
            setVX(0);
        }


        //TODO: Add Jumping and Gravity

        if(m_isJumping) {
            long timeJumping = System.currentTimeMillis() - m_jumpTime;
            if(timeJumping < m_maxJumpTime) {
                if(timeJumping < m_maxJumpTime / 2) {
                    setVY(-gravity); // heading up
                } else if (timeJumping > m_maxJumpTime / 2) {
                    setVY(gravity); // going down
                }
            } else {
                m_isJumping = false;
            }
        } else {
           setVY(gravity);

        }



        move(fps);



    }

}
