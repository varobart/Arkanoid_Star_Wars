package ru.varazdat.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import ru.varazdat.game.ArkanoidGame;
import ru.varazdat.game.graphics.Textures;

/**
 * Created by Вараздат on 01.01.2018.
 */

public class MenuState extends State {


    private static final String MENU_MUSIC = "opening.mp3";
    private static final int TITLE_Y = 400;
    private static final int PLAY_BTN_Y = 250;
    private static final int RULES_BTN_Y = 150;

    private Texture mBackground;
    private Texture mTitle;
    private Texture mPlayBtn;
    private Texture mRulesBtn;
    private Music openingMusic;



    public MenuState(GameStateManager gsm){
        super(gsm);
        texturesInit();
        musicAndSoundsInit();
    }


    protected void texturesInit(){
        mBackground = new Texture(Textures.MENU_BACKGROUND);
        mTitle = new Texture(Textures.TITLE);
        mPlayBtn = new Texture(Textures.PLAY_BTN);
        mRulesBtn = new Texture(Textures.RULES_BTN);
    }

    protected void musicAndSoundsInit(){
        openingMusic = Gdx.audio.newMusic(Gdx.files.internal(MENU_MUSIC));
        openingMusic.setVolume(1f);
        openingMusic.setLooping(true);
        openingMusic.play();
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            getCamera().unproject(touchPos);
            //checks whether player has pressed play button to start the game
            if(touchPos.x > ArkanoidGame.WIDTH / 2 - mPlayBtn.getWidth() / 2 &&
                    touchPos.x < ArkanoidGame.WIDTH / 2 + mPlayBtn.getWidth() / 2 &&
                    touchPos.y > PLAY_BTN_Y && touchPos.y < TITLE_Y + mPlayBtn.getHeight()/2){
                        openingMusic.stop();
                        getGsm().push(new PlayState(getGsm()));
                        getGsm().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            }
        }
    }


    @Override
    public void render(SpriteBatch spriteBatch) {
        handleInput();
        getCamera().update();
        spriteBatch.setProjectionMatrix(getCamera().combined);
        spriteBatch.begin();
        spriteBatch.draw(mBackground, 0 ,0, ArkanoidGame.WIDTH, ArkanoidGame.HEIGHT);
        spriteBatch.draw(mTitle, ArkanoidGame.WIDTH / 2 - mTitle.getWidth() / 2,TITLE_Y);
        spriteBatch.draw(mPlayBtn, ArkanoidGame.WIDTH / 2 - mPlayBtn.getWidth() / 2,PLAY_BTN_Y);
        spriteBatch.draw(mRulesBtn, ArkanoidGame.WIDTH / 2 - mRulesBtn.getWidth() / 2,RULES_BTN_Y);
        spriteBatch.end();
    }

    @Override
    public void update(float deltaTime) {
    }


    @Override
    protected void dispose() {
        mBackground.dispose();
        mTitle.dispose();
        mPlayBtn.dispose();
        mRulesBtn.dispose();
        openingMusic.dispose();
    }

}
