package com.liftlockstudios.pickmeup;

import android.graphics.Rect;

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


}
