package ru.varazdat.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.varazdat.game.states.GameStateManager;
import ru.varazdat.game.states.MenuState;

public class ArkanoidGame extends ApplicationAdapter {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 500;


	private SpriteBatch mSpriteBatch;
	private GameStateManager mGsm;


	
	@Override
	public void create () {
		mSpriteBatch = new SpriteBatch();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		mGsm = new GameStateManager();
		mGsm.push(new MenuState(mGsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mGsm.update(Gdx.graphics.getDeltaTime());
		mGsm.render(mSpriteBatch);
	}
	
	@Override
	public void dispose () {
		mSpriteBatch.dispose();
	}

	@Override
	public void resize(int width, int height) {
		mGsm.update(width, height);
	}
}
