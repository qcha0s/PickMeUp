package com.liftlockstudios.pickmeup;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;

public class SoundManager {

    private SoundPool m_soundPool;

    int m_jumpSound = -1;
    //TODO: Add more sounds

    public void loadSounds(Context context) {
        m_soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
//        try {
//            AssetManager assetManager = context.getAssets();
//            AssetFileDescriptor descriptor;
//
//            // Sound FX
//           // descriptor = assetManager.openFd("jump.ogg");
//            //m_jumpSound = m_soundPool.load(descriptor, 0);
//
//            //TODO: Add more sounds
//
//
//
//        } catch (IOException e) {
//            Log.e("error", "failed to load sound file(s)");
//        }
    }


    public void playSound(String sound) {
        switch(sound) {
            case "jump":
                m_soundPool.play(m_jumpSound, 1, 1, 0, 0, 0);
                break;
        }
    }
}
