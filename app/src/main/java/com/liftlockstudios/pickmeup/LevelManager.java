package com.liftlockstudios.pickmeup;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;

public class LevelManager {


    public int m_mapWidth;
    public int m_mapHeight;
    public Player m_player;
    public int m_playerIndex;
    public Bitmap[] m_spriteArray;

    private String m_levelName;
    private boolean m_playing;

    private float m_gravity;

    LevelData m_levelData;
    ArrayList<GameObject> m_gameObjects;

    public LevelManager(Context context, int ppm, int screenW, InputController ic, String levelName, float px, float py) {

        m_levelName = levelName;
        switch(m_levelName) {
            case "LevelCave":
                m_levelData = new LevelCave();
                break;

            //TODO: Add more Levels
        }

        m_gameObjects = new ArrayList<>();
        m_spriteArray = new Bitmap[10];

        loadMapData(context, ppm, px, py);

        m_playing = true;

    }


    public boolean isPlaying(){
        return m_playing;
    }


    public Bitmap getSprite(char tileType) {
        int index;
        switch(tileType) {
            case '.':
                index = 0;
                break;
            case '1':
                index = 1;
                break;
            case 'p':
                index = 2;
                break;
            default:
                index = 0;
                break;
        }

        return m_spriteArray[index];
    }

    public int getSpriteIndex(char tileType){ //FIXME: remove duplicate code
        int index;

        switch(tileType) {
            case '.':
                index = 0;
                break;
            case '1':
                index = 1;
                break;
            case 'p':
                index = 2;
                break;
            default:
                index = 0;
                break;
        }

        return index;
    }



    public void loadMapData(Context context, int ppm, float px, float py) {

        char t; // tile
        int currentIndex = -1;

        for(int r = 0; r < m_levelData.tiles.size(); r++) {
            for(int c = 0; c < m_levelData.tiles.get(r).length(); c++) {

                t = m_levelData.tiles.get(r).charAt(c);
                if( t != '.') { // safe to ignore empty spaces
                    currentIndex++;
                    switch(t){
                        case '1':
                            m_gameObjects.add(new Ground(c, r, t));
                            break;
                        case 'p':
                            m_gameObjects.add(new Player(context, c, r, ppm));
                            m_playerIndex = currentIndex;
                            m_player = (Player)m_gameObjects.get(m_playerIndex);
                            break;
                    }

                    // if the sprite isn't prepared yet
                    if(m_spriteArray[getSpriteIndex(t)] == null) {
                        // prepare it now
                        m_spriteArray[getSpriteIndex(t)] =
                                m_gameObjects.get(currentIndex).prepareSprite(context, m_gameObjects.get(currentIndex)
                                                .getSpriteName(), ppm);
                    }

                }

            }

        }

    }

}
