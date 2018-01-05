package ru.varazdat.game.objects;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Вараздат on 05.01.2018.
 */

public class GameObjectBuilder {


    private GameObject mGameObject;


    public GameObjectBuilder(GameObject gameObject){
        mGameObject = gameObject;
        mGameObject.setRectangle(new Rectangle());
    }


    public GameObjectBuilder xAndY(float x, float y) {
        mGameObject.setX(x);
        mGameObject.setY(y);
        mGameObject.getRectangle().x = x;
        mGameObject.getRectangle().y = y;
        return this;
    }

    public GameObjectBuilder xVelocityAndyVelocity(float xVelocity, float yVelocity) {
        mGameObject.setxVelocity(xVelocity);
        mGameObject.setyVelocity(yVelocity);
        return this;
    }


    public GameObjectBuilder widthAndHeight(float width, float height) {
        mGameObject.getRectangle().width = width;
        mGameObject.getRectangle().height = height;
        return this;
    }



    public GameObject build() {
        return mGameObject;
    }
}
