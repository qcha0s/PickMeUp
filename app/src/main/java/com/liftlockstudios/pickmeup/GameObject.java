package com.liftlockstudios.pickmeup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public abstract class GameObject {

    private Vector2D m_worldLocation;
    private float m_width;
    private float m_height;

    private boolean m_active = true;
    private boolean m_visible = true;

    private int m_animFrameCount = 1;
    private char m_type;

    private String m_spriteName;


    public abstract void update(long fps, float gravity);

    public String getSpriteName() {
        return m_spriteName;
    }

    public Bitmap prepareSprite(Context context, String spriteName /* TODO: scale */) {

        // get a resource
        int resID = context.getResources().getIdentifier(spriteName,
                "drawable", context.getPackageName());

        // create the sprite
        Bitmap sprite = BitmapFactory.decodeResource(context.getResources(), resID);


        //TODO: Scale image

        return sprite;
    }

    public Vector2D getWorldLocation() {
        return m_worldLocation;
    }

    public void setWorldLocation(float x, float y, int z){
        m_worldLocation = new Vector2D();
        m_worldLocation.x = x;
        m_worldLocation.y = y;
        m_worldLocation.z = z;
    }

    public void setSpriteName(String spriteName) {
        m_spriteName = spriteName;
    }

    public float getWidth(){
        return m_width;
    }

    public void setWidth(float width){
        m_width = width;
    }

    public float getHeight(){
        return m_height;
    }

    public void setHeight(float height) {
        m_height = height;
    }

    public boolean isActive() {
        return m_active;
    }

    public void setActive(boolean active) {
        m_active = active;
    }

    public boolean isVisible() {
        return m_visible;
    }

    public void setVisible(boolean visible) {
        m_visible = visible;
    }

    public char getType(){
        return m_type;
    }

    public void setType(char type) {
        m_type = type;
    }

    //TODO: Animation frames

}
