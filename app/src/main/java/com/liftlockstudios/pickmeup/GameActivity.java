package com.liftlockstudios.pickmeup;

import android.app.Activity;
import android.os.Bundle;



public class GameActivity extends Activity {

    private GameView m_gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        m_gameView = new GameView(this);
        setContentView(m_gameView);
    }


    @Override
    protected void onPause() {
        super.onPause();
        m_gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        m_gameView.resume();
    }



}
