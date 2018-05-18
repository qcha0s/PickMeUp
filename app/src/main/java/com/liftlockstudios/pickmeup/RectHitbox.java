package com.liftlockstudios.pickmeup;

public class RectHitbox {


    float m_left;
    float m_top;
    float m_right;
    float m_bottom;
    float m_height;


    boolean intersects(RectHitbox rectHitbox) {

        boolean hit = false;

        if(m_right > rectHitbox.m_left && m_left < rectHitbox.m_right) {
            // intersecting on the X axis
            if(m_top < rectHitbox.m_bottom && m_bottom > rectHitbox.m_top) {
                // intersecting on the Y too.
                hit = true;
            }
        }
        return hit;
    }

    public float getLeft(){
        return m_left;
    }

    public void setLeft(float left) {
        m_left = left;
    }


    public float getTop(){
        return m_top;
    }

    public void setTop(float top) {
       m_top = top;
    }

    public float getRight(){
        return m_right;
    }

    public void setRight(float right) {
        m_right = right;
    }


    public float getBottom(){
        return m_bottom;
    }

    public void setBottom(float bottom) {
        m_bottom = bottom;
    }

    public float getHeight(){
        return m_height;
    }
    public void setHeight(float height) {
        m_height = height;
    }
}
