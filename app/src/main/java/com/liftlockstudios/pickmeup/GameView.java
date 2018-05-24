package com.liftlockstudios.pickmeup;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable {



    private boolean m_debugging = true; // change to false before production
    private volatile boolean m_playing;
    private Thread m_gameThread = null;


    // for drawing
    private Paint m_paint;
    private Canvas m_canvas;
    private SurfaceHolder m_holder;

    private Context m_context;
    private long m_startTime;
    private long m_deltaTime;
    private long m_fps;


    private LevelManager m_lm;
    private Viewport m_vp;
    public InputController m_ic;
   // public SoundManager m_sm;


    public GameView(Context context, int screenW, int screenH){
        super(context);

        m_context = context;
        m_holder = getHolder();
        m_paint = new Paint();

        m_vp = new Viewport(screenW, screenH);

    //    m_sm = new SoundManager();
       // m_sm.loadSounds(context);

        loadLevel("LevelCave", 15, 2);

    }


    public void loadLevel(String level, float px, float py){
        m_lm = null;

        m_lm = new LevelManager(m_context, m_vp.getPixelsPerMeterX(),
                m_vp.getScreenWidth(), m_ic, level, px, py);

        m_ic = new InputController(m_vp.getScreenWidth(), m_vp.getScreenHeight());

        m_vp.setWorldCenter(m_lm.m_gameObjects.get(m_lm.m_playerIndex).getWorldLocation().x,
                m_lm.m_gameObjects.get(m_lm.m_playerIndex).getWorldLocation().y);

    }


    @Override
    public void run() {

        while(m_playing) {
            m_startTime = System.currentTimeMillis();

            update();
            draw();

            // calculate fps
            m_deltaTime = System.currentTimeMillis() - m_startTime;
            if(m_deltaTime >= 1) {
                m_fps = 1000 / m_deltaTime;
            }
        }
    }


    private Activity getActivity() {
        return(Activity)m_context;
    }


    //TODO: replace this in the Input Manager
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        if(m_lm != null) {
            m_ic.handleInput(motionEvent, m_lm, m_vp);
        }



        switch(motionEvent.getAction() * MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
//
//                Intent i = new Intent(getActivity(), GameOverActivity.class);
//                getActivity().startActivity(i);
//                getActivity().finish();


                break;
        }
        return true;
    }



    private void update() {
        for(GameObject go : m_lm.m_gameObjects) {
            if(go.isActive()) {
                // clip anything off screen
                if(!m_vp.clipObjects(go.getWorldLocation().x, go.getWorldLocation().y, go.getWidth(), go.getHeight())) {
                    go.setVisible(true);

                    int hit = m_lm.m_player.checkCollisions(go.getHitbox());

                    Log.d("HitBoxCheck", "hit is: " + hit);

                    if(hit > 0) {
                        // we have a collision
                        switch (go.getType()) {

                           // add pickups



                            default: // regular tile
                                if(hit == 1) { // left or right
                                    m_lm.m_player.setVX(0);
                                    m_lm.m_player.setPressingRight(false);
                                }

                                if(hit == 2) { // ground
                                    m_lm.m_player.m_isFalling = false;
                                    m_lm.m_player.setVY(0);
                                }
                                break;

                        }
                    }

                    if(m_lm.isPlaying()) {
                        go.update(m_fps, m_lm.m_gravity);

                    }
                } else {
                    go.setVisible(false);
                }
            }
        }

        if(m_lm.isPlaying()) {
            m_vp.setWorldCenter(m_lm.m_gameObjects.get(m_lm.m_playerIndex).getWorldLocation().x,
                    m_lm.m_gameObjects.get(m_lm.m_playerIndex).getWorldLocation().y);
        }



    }

    private void draw() {

        if(m_holder.getSurface().isValid()) {
            // lock the memory for the canvas
            m_canvas = m_holder.lockCanvas();

            // clear out the last frame
            m_paint.setColor(Color.argb(255, 0, 0, 255));
            m_canvas.drawColor(Color.argb(255, 0, 0, 255));

            Rect toScreen2d = new Rect();

            for(int layer = -1; layer <= 1; layer++) {
                for(GameObject go : m_lm.m_gameObjects) {
                    if(go.isVisible() && go.getWorldLocation().z == layer) {
                        toScreen2d.set(m_vp.worldToScreen(
                                go.getWorldLocation().x, go.getWorldLocation().y,
                                go.getWidth(), go.getHeight()));

                        if(go.isAnimated()) {
                            if(go.getFacing() == 1) {
                                Matrix flipper = new Matrix();
                                flipper.preScale(-1, 1);
                                Rect r = go.getRectToDraw(System.currentTimeMillis());
                                Bitmap b = Bitmap.createBitmap(m_lm.m_spriteArray[m_lm.getSpriteIndex(go.getType())],
                                        r.left, r.top, r.width(), r.height(), flipper, true);

                                m_canvas.drawBitmap(b, toScreen2d.left, toScreen2d.top, m_paint);
                            } else {
                                m_canvas.drawBitmap(m_lm.m_spriteArray[m_lm.getSpriteIndex(go.getType())],
                                        go.getRectToDraw(System.currentTimeMillis()),
                                        toScreen2d, m_paint);
                            }
                        } else {

                            m_canvas.drawBitmap(m_lm.m_spriteArray[m_lm.getSpriteIndex(go.getType())],
                                    toScreen2d.left, toScreen2d.top, m_paint);
                        }
                    }
                }
            }


            // draw buttons
            m_paint.setColor(Color.argb(128, 255, 255, 255));
            ArrayList<Rect> buttonsToDraw;
            buttonsToDraw = m_ic.getButtons();

            for(Rect rect : buttonsToDraw) {
                RectF rf = new RectF(rect.left, rect.top, rect.right, rect.bottom);
                m_canvas.drawRoundRect(rf, 15f, 15f, m_paint);
            }




            if(m_debugging) {
                m_paint.setTextSize(48);
                m_paint.setTextAlign(Paint.Align.LEFT);
                m_paint.setColor(Color.argb(255, 255,255, 255));
                m_canvas.drawText("FPS: " + m_fps, 10, 60, m_paint);
                m_canvas.drawText("Num Objs: " + m_lm.m_gameObjects.size(), 10, 120, m_paint);
                m_canvas.drawText("Num Clip: " + m_vp.getNumClipped(), 10, 180, m_paint);
                m_canvas.drawText("PlayerX: " + m_lm.m_gameObjects.get(m_lm.m_playerIndex).getWorldLocation().x, 10, 240, m_paint);
                m_canvas.drawText("PlayerY: " + m_lm.m_gameObjects.get(m_lm.m_playerIndex).getWorldLocation().y, 10, 300, m_paint);

                m_vp.resetNumClipped();
            }


            m_holder.unlockCanvasAndPost(m_canvas);

        }
    }


    public void pause() {
        m_playing = false;
        try {
            m_gameThread.join();
        } catch(InterruptedException e) {
            Log.d("error in GameView - Pause: ", e.getMessage());
        }
    }

    public void resume() {
        m_playing = true;
        m_gameThread = new Thread(this);
        m_gameThread.start();
    }



}
