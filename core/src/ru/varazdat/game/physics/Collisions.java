package ru.varazdat.game.physics;

import ru.varazdat.game.objects.Bullet;

/**
 * Created by Вараздат on 03.01.2018.
 */

public class Collisions {


    public static void blockCollision(Bullet bullet, boolean isLateral){
        if(!isLateral) {
            bullet.setyVelocity(-bullet.getyVelocity());
        } else {
            bullet.setxVelocity(-bullet.getxVelocity());
        }
    }

    public static void wallCollision(Bullet bullet, boolean isCeiling){
        if(isCeiling) {
            bullet.setyVelocity(-bullet.getyVelocity());
        } else {
            bullet.setxVelocity(-bullet.getxVelocity());
        }
    }

    public static void platformCollision(Bullet bullet, float platfromVelocity){
        bullet.setxVelocity((float)(platfromVelocity + Math.sqrt(Math.pow(platfromVelocity,2)
                + Math.pow(bullet.getxVelocity(),2)*(1 - 2*platfromVelocity/bullet.getxVelocity()))));
        bullet.setyVelocity(-bullet.getyVelocity());
    }

}
