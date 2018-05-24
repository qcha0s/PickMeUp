package com.liftlockstudios.pickmeup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public abstract class GameObject {

    private Vector2D m_worldLocation;
    private float m_width;
    private float m_height;

    private RectHitbox m_rectHitbox = new RectHitbox();

    private boolean m_active = true;
    private boolean m_visible = true;


    private Animation m_anim = null;
    private boolean m_animated;
    private int m_animFPS;
    private int m_animFrameCount = 1;


    private char m_type;

    private String m_spriteName;

    private float m_vX;
    private float m_vY;
    private int m_facing;
    private boolean m_canMove = false;

    final int LEFT = -1;
    final int RIGHT = 1;




    public abstract void update(long fps, float gravity);

    public String getSpriteName() {
        return m_spriteName;
    }

    public Bitmap prepareSprite(Context context, String spriteName, int pixelsPerMeter) {

        // get a resource
        int resID = context.getResources().getIdentifier(spriteName,
                "drawable", context.getPackageName());

        // create the sprite
        Bitmap sprite = BitmapFactory.decodeResource(context.getResources(), resID);

        //TODO: maybe ??
        sprite = Bitmap.createScaledBitmap(sprite,
                (int)(m_width * m_animFrameCount * pixelsPerMeter),
                (int)(m_height * pixelsPerMeter), false);

        return sprite;
    }


    public void move(long fps) {
        if(m_vX != 0) {
            m_worldLocation.x += m_vX / fps;
        }

        if(m_vY != 0) {
            m_worldLocation.y += m_vY / fps;
        }
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


    public RectHitbox getHitbox() {
        return m_rectHitbox;
    }

    public void setRectHitbox() {
        m_rectHitbox.setLeft(m_worldLocation.x);
        m_rectHitbox.setTop(m_worldLocation.y);
        m_rectHitbox.setRight(m_worldLocation.x + m_width);
        m_rectHitbox.setBottom(m_worldLocation.y + m_height);
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

    public int getFacing() {
        return m_facing;
    }

    public void setFacing(int facing){
        m_facing = facing;
    }

    public float getVX() {
        return m_vX;
    }

    public void setVX(float vx) {
        if(m_canMove) {
            m_vX = vx;
        }
    }

    public float getVY() {
        return m_vY;
    }

    public void setVY(float vy) {
        if(canMove()) {
            m_vY = vy;
        }
    }

    public boolean canMove() {
        return m_canMove;
    }

    public void canMove(boolean canMove) {
        m_canMove = canMove;
    }


    //TODO: Animation frames

    public void setAnimFPS(int animFPS) {
        m_animFPS = animFPS;
    }

    public void setAnimFrameCount(int animFrameCount) {
        m_animFrameCount = animFrameCount;
    }

    public boolean isAnimated() {
        return m_animated;
    }

    public void setAnimated(Context context, int pPM, boolean animated) {
        m_animated = animated;
        m_anim = new Animation(context, m_spriteName, m_height, m_width, m_animFPS, m_animFrameCount, pPM);
    }

    public Rect getRectToDraw(long deltaTime) {
        return m_anim.getCurrentFrame(deltaTime, m_vX, canMove());
    }









}
