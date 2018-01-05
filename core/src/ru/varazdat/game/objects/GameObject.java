package ru.varazdat.game.objects;


import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Вараздат on 03.01.2018.
 */

public abstract class GameObject {

    protected float xPosition;
    protected float yPosition;
    protected float xVelocity;
    protected float yVelocity;
    protected Rectangle mRectangle;


    public GameObject(){}


    public GameObject(float xPosition, float yPosition, float xVelocity, float yVelocity,
                      float width, float  height) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        mRectangle = new Rectangle(this.xPosition, this.yPosition, width, height);
    }

    public float getX() {
        return xPosition;
    }

    public void setX(float xPosition) {
        this.xPosition = xPosition;
        mRectangle.x = xPosition;
    }

    public void addX(float deltaX) {
        this.xPosition += deltaX;
        mRectangle.x += deltaX;
    }


    public float getY() {
        return yPosition;
    }

    public void setY(float yPosition) {
        this.yPosition = yPosition;
        mRectangle.y = yPosition;
    }

    public void addY(float deltaY) {
        this.yPosition += deltaY;
        mRectangle.y += deltaY;
    }

    public float getxVelocity() {
        return xVelocity;
    }

    public void setRectangle(Rectangle rectangle) {
        mRectangle = rectangle;
    }

    public void addxVelocity(float xVelocity) {
        this.xVelocity += xVelocity;
    }

    public void setxVelocity(float xVelocity) {
        this.xVelocity = xVelocity;
    }

    public float getyVelocity() {
        return yVelocity;
    }

    public void addyVelocity(float yVelocity) {
        this.yVelocity += yVelocity;
    }

    public void setyVelocity(float yVelocity) {
        this.yVelocity = yVelocity;
    }

    public Rectangle getRectangle() {
        return mRectangle;
    }

    public boolean overlaps(GameObject gameObject){
        return mRectangle.overlaps(gameObject.getRectangle());
    }

    public float getWidth(){
        return mRectangle.width;
    }

    public float getHeight(){
        return mRectangle.height;
    }


}
