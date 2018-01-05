package ru.varazdat.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.varazdat.game.ArkanoidGame;

/**
 * Created by Вараздат on 01.01.2018.
 */

public abstract class State {

    private OrthographicCamera mCamera;
    private GameStateManager gsm;
    private Viewport viewport;


    public State (GameStateManager gsm){
        this.gsm = gsm;
        mCamera = new OrthographicCamera();
        viewport = new FillViewport(ArkanoidGame.WIDTH, ArkanoidGame.HEIGHT, mCamera);
        viewport.apply();

        mCamera.position.set(mCamera.viewportWidth/2,mCamera.viewportHeight/2,0);
        mCamera.update();
    }

    protected abstract void handleInput();
    protected abstract void dispose();
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void update(float deltaTime);


    public OrthographicCamera getCamera() {
        return mCamera;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public GameStateManager getGsm() {
        return gsm;
    }
}
