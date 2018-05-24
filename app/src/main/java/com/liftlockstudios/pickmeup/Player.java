package com.liftlockstudios.pickmeup;


import android.content.Context;



public class Player extends GameObject {

    final float MAX_X_VELOCITY = 10f;


    public boolean m_isPressingRight = false;
    public boolean m_isPressingLeft = false;


    public boolean m_isFalling;
    private boolean m_isJumping;
    private long m_jumpTime;
    private long m_maxJumpTime = 700; // 7/10ths of a second

    RectHitbox m_hitLeft;
    RectHitbox m_hitTop;
    RectHitbox m_hitRight;
    RectHitbox m_hitBottom;


    Player(Context context, float worldStartX, float worldStartY, int pixelsPerMeter) {
        final float HEIGHT = 2;
        final float WIDTH = 1;


        setWidth(WIDTH);
        setHeight(HEIGHT);

        setVX(0);
        setVY(0);
        setFacing(RIGHT);

        m_isJumping = false;
        m_isFalling = false;

        canMove(true);
        setActive(true);
        setVisible(true);

        setType('p');
        setSpriteName("bunny");


        final int ANIMATION_FPS = 16;
        final int ANIMATION_FRAME_COUNT = 2;

        setAnimFPS(ANIMATION_FPS);
        setAnimFrameCount(ANIMATION_FRAME_COUNT);
        setAnimated(context, pixelsPerMeter, true);

        setWorldLocation(worldStartX, worldStartY, 0);

        m_hitRight = new RectHitbox();
        m_hitTop = new RectHitbox();
        m_hitLeft = new RectHitbox();
        m_hitBottom = new RectHitbox();

    }

    public void update(long fps, float gravity) {

        if(m_isPressingRight) {
            setVX(MAX_X_VELOCITY);
        } else if (m_isPressingLeft) {
            setVX(-MAX_X_VELOCITY);
        } else {
            setVX(0);
        }

        if(getVX() > 0) {
            setFacing(RIGHT);
        } else if (getVX() < 0) {
            setFacing(LEFT);
        }

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

           m_isFalling = true;
        }



        move(fps);

        // collisions
        Vector2D location = getWorldLocation();
        float lx = location.x;
        float ly = location.y;

        // FIXME: ewwwwwwwwwwwwww
        m_hitBottom.m_left = lx + getWidth() * 0.2f;
        m_hitBottom.m_top = ly + (getHeight() * 0.95f);
        m_hitBottom.m_right = lx + getWidth() * 0.8f;
        m_hitBottom.m_bottom = ly + getHeight() * 0.95f;

        m_hitTop.m_left = lx + getWidth() * 0.4f;
        m_hitTop.m_top = ly;
        m_hitTop.m_right = lx + getWidth() * 0.6f;
        m_hitTop.m_bottom = ly + getHeight();

        m_hitLeft.m_left = lx + getWidth() * 0.2f;
        m_hitLeft.m_top = ly + (getHeight() * 0.2f);
        m_hitLeft.m_right = lx + getWidth() * 0.3f;
        m_hitLeft.m_bottom = ly + getHeight() * 0.8f;

        m_hitRight.m_left = lx + getWidth() * 0.2f;
        m_hitRight.m_top = ly + (getHeight() * 0.2f);
        m_hitRight.m_right = lx + getWidth() * 0.7f;
        m_hitRight.m_bottom = ly + getHeight() * 0.8f;


    }


    public int checkCollisions(RectHitbox rectHitbox) {
        int collided = 0; // 0 - No collision / 1 - left or Right / 2 - ground / 3 - head

        if(m_hitLeft.intersects(rectHitbox)) {
            collided = 1;
        }

        if(m_hitTop.intersects(rectHitbox)) {
            collided = 3;
        }

        if(m_hitRight.intersects(rectHitbox)) {
            collided = 1;
        }

        if(m_hitBottom.intersects(rectHitbox)) {
            collided = 2;
        }

        return collided;
    }

    public void setPressingRight(boolean isPressingRight) {
        m_isPressingRight = isPressingRight;
    }

    public void setPressingLeft(boolean isPressingLeft) {
        m_isPressingLeft = isPressingLeft;
    }

    public void startJump() { //TODO: add sound
        if(!m_isFalling) { // can't jump if falling
            if(!m_isJumping) { // not already jumping
                m_isJumping = true;
                m_jumpTime = System.currentTimeMillis();
                //TODO: play sound
            }

        }
    }


}
