package ru.varazdat.game.objects;

/**
 * Created by Вараздат on 02.01.2018.
 */

public class Bullet extends GameObject{



    public Bullet(float xPosition, float yPosition, float xVelocity, float yVelocity, float width, float height) {
        super(xPosition, yPosition, xVelocity, yVelocity, width, height);
    }

    public Bullet(){}

    public void blockCollision(boolean isLateral){
        if(!isLateral) {
            yVelocity = -yVelocity;
        } else {
            xVelocity = -xVelocity;
        }
    }

    public void wallCollision(boolean isCeiling){
        if(isCeiling) {
            yVelocity = -yVelocity;
        } else {
            xVelocity = -xVelocity;
        }
    }

    public void platformCollision(float platfromVelocity){
        //xVelocity = (float) (platfromVelocity*(-1 + Math.sqrt( 1 + xVelocity*(2*platfromVelocity + xVelocity)/platfromVelocity/platfromVelocity  )));
        xVelocity = (float)(platfromVelocity + Math.sqrt(Math.pow(platfromVelocity,2)
            + Math.pow(xVelocity,2)*(1 - 2*platfromVelocity/xVelocity)));
        yVelocity = -yVelocity;
    }



}
