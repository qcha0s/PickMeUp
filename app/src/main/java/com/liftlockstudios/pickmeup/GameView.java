package com.liftlockstudios.pickmeup;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {



    private boolean m_debugging = true; // change to false before production
    private volatile boolean m_playing;
    private Thread m_gameThread = null;

    private Player m_player;
    private Enemy m_enemy;

    // for drawing
    private Paint m_paint;
    private Canvas m_canvas;
    private SurfaceHolder m_holder;

    private Context m_context;
    private long m_startTime;
    private long m_deltaTime;
    private long m_fps;

//TODO: Work In Progress
//    private LevelManager m_lm;
//    private Viewport m_vp;
//
//    public InputController m_ic;


    public GameView(Context context, int screenW, int screenH){
        super(context);

        m_context = context;
        m_holder = getHolder();
        m_paint = new Paint();

    //  m_player = new Player(context, screenW, screenH);
    //  m_enemy = new Enemy(context, screenW, screenH);

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

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch(motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            // player lifted a finger
            case MotionEvent.ACTION_UP:
                m_player.stopJumping();

                break;


            // has the player touched the screen
            case MotionEvent.ACTION_DOWN:
                m_player.setJumping();

                break;

        }

        return true;
    }



    private void update() {
        //TODO: add update
    }

    private void draw() {

        if(m_holder.getSurface().isValid()) {
            // lock the memory for the canvas
            m_canvas = m_holder.lockCanvas();

            // clear out the last frame
            m_canvas.drawColor(Color.argb(255, 0, 0, 0));

            //TODO: Add drawing

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
