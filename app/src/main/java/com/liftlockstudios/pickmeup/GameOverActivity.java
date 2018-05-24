package com.liftlockstudios.pickmeup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameOverActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Button buttonPlay = findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();

    }

}
