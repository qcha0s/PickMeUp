package com.liftlockstudios.pickmeup;

import android.graphics.Rect;

public class Viewport {

    private Vector2D m_viewportWorldCenter;
    private Rect m_convertedRect;
    private int m_pixelsPerMeterX;
    private int m_pixelsPerMeterY;
    private int m_screenXRes;
    private int m_screenYRes;
    private int m_screenCenterX;
    private int m_screenCenterY;
    private int m_metersToShowX;
    private int m_metersToShowY;
    private int m_numClipped;

    Viewport(int x, int y) {

        m_screenXRes = x;
        m_screenYRes = y;

        m_screenCenterX = m_screenXRes / 2;
        m_screenCenterY = m_screenYRes / 2;

        m_pixelsPerMeterX = m_screenXRes / 32;
        m_pixelsPerMeterY = m_screenYRes / 18;

        m_metersToShowX = 34;
        m_metersToShowY = 20;

        m_convertedRect = new Rect();
        m_viewportWorldCenter = new Vector2D();

    }


    public Rect worldToScreen(float objX, float objY, float objW, float objH) {

        int left = (int)(m_screenCenterX - ((m_viewportWorldCenter.x - objX) * m_pixelsPerMeterX));
        int top = (int)(m_screenCenterY - ((m_viewportWorldCenter.y - objY) * m_pixelsPerMeterY));
        int right = (int)(left + (objW * m_pixelsPerMeterX));
        int bottom = (int)(top + (objH * m_pixelsPerMeterY));

        m_convertedRect.set(left,top, right, bottom);
        return m_convertedRect;
    }

    public boolean clipObjects(float objX, float objY, float objW, float objH) {
        boolean clipped = true;

        if(objX - objW < m_viewportWorldCenter.x + (m_metersToShowX / 2)) {
            if (objX + objW > m_viewportWorldCenter.x - (m_metersToShowX / 2)) {
                if(objY - objH < m_viewportWorldCenter.y + (m_metersToShowY /2)) {
                    if(objY + objH > m_viewportWorldCenter.y - (m_metersToShowY / 2)) {
                        clipped = false;
                    }
                }
            }
        }

        // dBugging
        if(clipped) {
            m_numClipped++;
        }

        return clipped;
    }

    public int getScreenWidth() {
        return m_screenXRes;
    }

    public int getScreenHeight() {
        return m_screenYRes;
    }


    public int getNumClipped() {
        return m_numClipped;
    }

    public void resetNumClipped() {
        m_numClipped = 0;
    }

    public void setWorldCenter(float x, float y) {
        m_viewportWorldCenter.x = x;
        m_viewportWorldCenter.y = y;
    }

    public int getPixelsPerMeterX() {
        return m_pixelsPerMeterX;
    }

    public int getPixelsPerMeterY() {
        return m_pixelsPerMeterY;
    }

}
