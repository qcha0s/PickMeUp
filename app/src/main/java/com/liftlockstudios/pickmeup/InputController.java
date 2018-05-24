package com.liftlockstudios.pickmeup;

import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

public class InputController {

    Rect m_left;
    Rect m_right;
    Rect m_jump;
    Rect m_shoot;
    Rect m_pause;


    InputController(int screenWidth, int screenHeight) {

        int buttonWidth = screenWidth / 8;
        int buttonHeight = screenHeight / 7;
        int buttonPadding = screenWidth / 80;

        m_left = new Rect(
                buttonPadding - 0,
                screenHeight - buttonHeight - buttonPadding,
                buttonWidth - 0,
                screenHeight - buttonPadding);

        m_right = new Rect(
                buttonWidth + buttonPadding,
                screenHeight - buttonHeight - buttonPadding,
                buttonWidth + buttonPadding + buttonWidth,
                screenHeight - buttonPadding);

        m_jump = new Rect(
                screenWidth - buttonWidth - buttonPadding,
                screenHeight - buttonHeight - buttonPadding - buttonHeight - buttonPadding,
                screenWidth - buttonPadding,
                screenHeight - buttonPadding - buttonHeight - buttonPadding
        );

        m_shoot = new Rect(
                screenWidth - buttonWidth - buttonPadding,
                screenHeight - buttonHeight - buttonPadding,
                screenWidth - buttonPadding,
                screenHeight - buttonPadding);

        m_pause = new Rect(
                screenWidth - buttonPadding - buttonWidth,
                buttonPadding - 0,
                screenWidth - buttonPadding,
                buttonPadding + buttonHeight);

    }


    public ArrayList getButtons() {
        ArrayList<Rect> currentButtonList = new ArrayList<>();

        currentButtonList.add(m_left);
        currentButtonList.add(m_right);
        currentButtonList.add(m_jump);
        currentButtonList.add(m_shoot);
        currentButtonList.add(m_pause);

        return currentButtonList;

    }

    public void handleInput(MotionEvent motionEvent, LevelManager lm, /* SoundManager sound, */ Viewport vp) {
        int pointerCount = motionEvent.getPointerCount();

        for(int i = 0; i < pointerCount; i++) {
            int x = (int)motionEvent.getX(i);
            int y = (int)motionEvent.getY(i);

            if(lm.isPlaying()) {

                switch(motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:

                        if(m_right.contains(x ,y)) {
                            lm.m_player.setPressingRight(false);
                            lm.m_player.setPressingLeft(true);
                        } else if(m_left.contains(x, y)) {
                            lm.m_player.setPressingRight(true);
                            lm.m_player.setPressingLeft(false);
                        } else if(m_jump.contains(x, y)) {
                            // call jump
                        } else if(m_pause.contains(x,y)) {
                            lm.switchPlayingStatus();
                        }

                        break;



                    case MotionEvent.ACTION_UP:
                        if(m_right.contains(x ,y)) {
                            lm.m_player.setPressingRight(false);
                        } else if(m_left.contains(x, y)) {
                            lm.m_player.setPressingLeft(false);
                        }
                        break;



                    case MotionEvent.ACTION_POINTER_DOWN:

                        if(m_right.contains(x ,y)) {
                            lm.m_player.setPressingRight(false);
                            lm.m_player.setPressingLeft(true);
                        } else if(m_left.contains(x, y)) {
                            lm.m_player.setPressingRight(true);
                            lm.m_player.setPressingLeft(false);
                        } else if(m_jump.contains(x, y)) {
                            // call jump
                        } else if(m_pause.contains(x,y)) {
                            lm.switchPlayingStatus();
                        }

                        break;



                    case MotionEvent.ACTION_POINTER_UP:
                        if(m_right.contains(x ,y)) {
                            lm.m_player.setPressingRight(false);
                        } else if(m_left.contains(x, y)) {
                            lm.m_player.setPressingLeft(false);
                        }
                        break;

                }




            } else {
                switch(motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        if(m_pause.contains(x, y)) {
                            lm.switchPlayingStatus();
                        }
                        break;


                }
            }

        }
    }





}
