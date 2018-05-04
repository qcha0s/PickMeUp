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

    private volatile boolean m_playing;
    private Thread m_gameThread = null;

    private Player m_player;
    private Enemy m_enemy;

    // for drawing
    private Paint m_paint;
    private Canvas m_canvas;
    private SurfaceHolder m_holder;



    public GameView(Context context, int screenW, int screenH){
        super(context);

        m_holder = getHolder();
        m_paint = new Paint();

        m_player = new Player(context, screenW, screenH);

        m_enemy = new Enemy(context, screenW, screenH);

    }


    @Override
    public void run() {

        while(m_playing) {
            update();
            draw();
            control();
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
//        Log.d("dBug", "update()");
        m_player.update();
        m_enemy.update();
    }

    private void draw() {
//        Log.d("dBug", "draw()");
        if(m_holder.getSurface().isValid()) {
            // lock the memory for the canvas
            m_canvas = m_holder.lockCanvas();

            // clear out the last frame
            m_canvas.drawColor(Color.argb(255, 0, 0, 0));

            // draw the player
            m_canvas.drawBitmap(m_player.getSprite(),
                    m_player.getX(), m_player.getY(),
                    m_paint);

            // draw the enemy
            m_canvas.drawBitmap(m_enemy.getSprite(),
                    m_enemy.getX(), m_enemy.getY(),
                    m_paint);



            m_holder.unlockCanvasAndPost(m_canvas);

        }
    }

    private void control() {
//        Log.d("dBug", "control()");
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
