package com.liftlockstudios.pickmeup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

public class Animation {

    Bitmap m_spriteSheet;
    String m_spriteSheetName;

    private Rect m_sourceRect;
    private int m_frameCount;
    private int m_currentFrame;

    private long m_frameTicker;
    private int m_framePeriod;
    private int m_frameWidth;
    private int m_frameHeight;

    public int m_pixelsPerMeter;

    Animation(Context context, String spriteSheetName,
              float frameHeight, float frameWidth, int animFPS, int frameCount, int ppm) {

        m_currentFrame = 0;
        m_frameCount = frameCount;
        m_frameWidth = (int)frameWidth * ppm;
        m_frameHeight = (int)frameHeight * ppm;
        m_sourceRect = new Rect(0, 0, m_frameWidth, m_frameHeight);
        Log.d("dBug:", "animFPS: " + animFPS);
        m_framePeriod = 1000 / animFPS;
        m_frameTicker = 0l;
        m_spriteSheetName = spriteSheetName;
        m_pixelsPerMeter = ppm;

    }

    public Rect getCurrentFrame(long time, float xV, boolean moves) {

        if(xV != 0 || moves == false) {
            if(time > m_frameTicker + m_framePeriod) {
                m_frameTicker = time;
                m_currentFrame++;
                if(m_currentFrame >= m_frameCount) {
                    m_currentFrame = 0;
                }
            }
        }


        m_sourceRect.left = m_currentFrame * m_frameWidth;
        m_sourceRect.right = m_sourceRect.left + m_frameWidth;

        return m_sourceRect;
    }

}
