package ru.varazdat.game.levels;

import com.badlogic.gdx.utils.Array;

import ru.varazdat.game.objects.SpaceShip;

/**
 * Created by Вараздат on 05.01.2018.
 */

public class LevelOne {

    public static int BULLET_Y = 150;
    public static int BULLET_VELOCITY_Y = -100;
    public static int BULLET_MIN_VELOCITY_X = -150;
    public static int BULLET_MAX_VELOCITY_X = 150;
    public static int PLATFORM_Y = 20;
    public static int PLATFORM_VELOCITY_X = 200;
    public static int ARROW_Y = 30;
    public static int ARROW_X = 30;

    public static void initSpaceShips(Array<SpaceShip> ships, int width, int height){
        int x = 0;
        int y = 400;
        for(int i = 0; i < 18; i++){
            if(i%6 == 0){x += 50;}
            SpaceShip spaceShip = new SpaceShip(x , y , 0, 0,
                   width, height);

            x += 120;
            ships.add(spaceShip);
            if(i%6 == 5){
                x = 0;
                y -= 70;}
        }
    }

}
