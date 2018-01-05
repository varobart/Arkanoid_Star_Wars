package ru.varazdat.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import ru.varazdat.game.ArkanoidGame;
import ru.varazdat.game.graphics.Textures;
import ru.varazdat.game.levels.LevelOne;
import ru.varazdat.game.objects.Bullet;
import ru.varazdat.game.objects.GameObjectBuilder;
import ru.varazdat.game.objects.Platform;
import ru.varazdat.game.objects.SpaceShip;
import ru.varazdat.game.physics.Collisions;

/**
 * Created by Вараздат on 02.01.2018.
 */

public class PlayState extends State {


    public static final String DUEL_OF_THE_FATES = "duel_of_the_fates.mp3";
    public static final String LIGHTLABER_1 = "saberon.mp3";
    public static final String LIGHTLABER_2 = "saber.wav";



    private Texture mBackground;
    private Texture mPlatformTexture;
    private Texture mStarShip;
    private Texture mGameOver;
    private Texture mArrows;
    private TextureRegion mLeftBtn;
    private TextureRegion mRightBtn;
    private Array<SpaceShip> ships;
    private Bullet mBullet;
    private Platform mPlatform;
    private Texture mBulletTexture;
    private Music duelMusic;
    private Sound lightsaber1;
    private Sound lightsaber3;
    private boolean isGameOver;


    public PlayState(GameStateManager gsm){
        super(gsm);
        isGameOver = false;
        texturesInit();
        musicAndSoundsInit();
        gameObjectsInit();
    }


    protected void gameObjectsInit(){
        mBullet = (Bullet) new GameObjectBuilder(new Bullet())
                .xAndY(ArkanoidGame.WIDTH / 2 - mBulletTexture.getWidth() / 2, LevelOne.BULLET_Y)
                .xVelocityAndyVelocity(MathUtils.random(LevelOne.BULLET_MIN_VELOCITY_X,
                        LevelOne.BULLET_MAX_VELOCITY_X), LevelOne.BULLET_VELOCITY_Y)
                .widthAndHeight(mBulletTexture.getWidth(), mBulletTexture.getHeight())
                .build();

        mPlatform = (Platform) new GameObjectBuilder(new Platform())
                .xAndY(ArkanoidGame.WIDTH / 2 - mPlatformTexture.getWidth() / 2, LevelOne.PLATFORM_Y)
                .xVelocityAndyVelocity(0, 0)
                .widthAndHeight(mPlatformTexture.getWidth(), mPlatformTexture.getHeight())
                .build();

        ships = new Array<SpaceShip>();
        LevelOne.initSpaceShips(ships, mStarShip.getWidth(), mStarShip.getHeight());
    }


    protected void texturesInit(){
        mBackground = new Texture(Textures.LEVEL_1_BACKGROUND);
        mGameOver = new Texture(Textures.GAME_OVER);
        mPlatformTexture = new Texture(Textures.PLATFORM);
        mStarShip = new Texture(Textures.STAR_SHIP_1);
        mBulletTexture = new Texture(Textures.BULLET);
        mArrows = new Texture(Textures.ARROWS);
        mLeftBtn = new TextureRegion(mArrows, 0, 0,
                mArrows.getWidth()/2, mArrows.getHeight());
        mRightBtn = new TextureRegion(mArrows, mArrows.getWidth()/2, 0,
                mArrows.getWidth()/2, mArrows.getHeight());
    }

    protected void musicAndSoundsInit(){
        duelMusic = Gdx.audio.newMusic(Gdx.files.internal(DUEL_OF_THE_FATES));
        duelMusic.setLooping(true);
        duelMusic.play();
        lightsaber1 = Gdx.audio.newSound(Gdx.files.internal(LIGHTLABER_1));
        lightsaber3 = Gdx.audio.newSound(Gdx.files.internal(LIGHTLABER_2));
    }


    @Override
    protected void handleInput() {
        if(isGameOver){
            if(Gdx.input.isTouched()) {
                duelMusic.stop();
                getGsm().pop();
                getGsm().push(new PlayState(getGsm()));
                getGsm().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

            }
            return;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            mPlatform.setxVelocity(-LevelOne.PLATFORM_VELOCITY_X);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            mPlatform.setxVelocity(LevelOne.PLATFORM_VELOCITY_X);
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            getGsm().pop();
        }
        //checks whether the player has touched left or right arrow in order to move the platform
        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            getCamera().unproject(touchPos);
            if(touchPos.x < LevelOne.ARROW_X + mRightBtn.getRegionWidth() &&
                    touchPos.y < LevelOne.ARROW_Y + mRightBtn.getRegionHeight())
                mPlatform.setxVelocity(-LevelOne.PLATFORM_VELOCITY_X);
            if(touchPos.x > ArkanoidGame.WIDTH - LevelOne.ARROW_X - mRightBtn.getRegionWidth() &&
                    touchPos.y < LevelOne.ARROW_Y + mRightBtn.getRegionHeight())
                mPlatform.setxVelocity(LevelOne.PLATFORM_VELOCITY_X);
        }
    }



    @Override
    public void render(SpriteBatch spriteBatch) {

        handleInput();
        getCamera().update();
        spriteBatch.setProjectionMatrix(getCamera().combined);
        spriteBatch.begin();
        spriteBatch.draw(mBackground,0,0, ArkanoidGame.WIDTH, ArkanoidGame.HEIGHT);
        spriteBatch.draw(mPlatformTexture, mPlatform.getX(), LevelOne.PLATFORM_Y);
        for(SpaceShip ship : ships){
            spriteBatch.draw(mStarShip, ship.getX(), ship.getY(), ship.getWidth(), ship.getHeight());
        }
        spriteBatch.draw(mBulletTexture, mBullet.getX(), mBullet.getY());
        if(isGameOver){
            spriteBatch.draw(mGameOver, ArkanoidGame.WIDTH/2 - mGameOver.getWidth()/2,
                    ArkanoidGame.HEIGHT/2 - mGameOver.getHeight()/2 );
        }
        spriteBatch.draw(mLeftBtn, LevelOne.ARROW_X, LevelOne.ARROW_Y);
        spriteBatch.draw(mRightBtn, ArkanoidGame.WIDTH - LevelOne.ARROW_X - mRightBtn.getRegionWidth(),
                LevelOne.ARROW_Y);
        spriteBatch.end();

    }

    @Override
    public void update(float deltaTime) {
        mPlatform.addX(mPlatform.getxVelocity()* deltaTime);
        if(mPlatform.getX() < 0){
            mPlatform.setX(0);
        }
        if(mPlatform.getX() > ArkanoidGame.WIDTH - mPlatformTexture.getWidth()) {
            mPlatform.setX(ArkanoidGame.WIDTH- mPlatformTexture.getWidth());
        }

        //detects the collisions between bullet and spaceships
        for(SpaceShip ship : ships){
            if(mBullet.overlaps(ship)){
                //checks whether the collision has been laternal or not
                if(( mBullet.getX() > ship.getX() + ship.getWidth() - 2 && mBullet.getY() > ship.getHeight() + 2  &&
                        mBullet.getY() < ship.getHeight() + ship.getHeight() - 2)
                    || ( mBullet.getX() < ship.getX() + 2 && mBullet.getY() > ship.getY() + 2  &&
                        mBullet.getY() < ship.getY() + ship.getHeight() - 2 )) {
                        Collisions.blockCollision(mBullet, true);
                } else {
                    Collisions.blockCollision(mBullet, false);
                }
                ships.removeValue(ship, false);
                lightsaber1.play();
            }
        }

        //detects the collisions between bullet and walls
        if(mBullet.getX() < 0 || mBullet.getX() > ArkanoidGame.WIDTH - mBulletTexture.getWidth()){
            Collisions.wallCollision(mBullet, false);
            lightsaber3.play();
        }
        //detects the collisions between bullet and "ceiling"
        if(mBullet.getY() > ArkanoidGame.HEIGHT - mBulletTexture.getHeight()){
            Collisions.wallCollision(mBullet, true);
            lightsaber3.play();
        }

        //checks whether the bullet has fallen or not
        if(mBullet.getY() < 0){
            isGameOver = true;
        }

        //detects the collisions between bullet and platfoem
        if(mBullet.getX() > mPlatform.getX() && mBullet.getX() < mPlatform.getX() + mPlatformTexture.getWidth()
                && mBullet.getY() < mPlatformTexture.getHeight() + LevelOne.PLATFORM_Y){
            Collisions.platformCollision(mBullet, mPlatform.getxVelocity());
        }
        mBullet.addX(mBullet.getxVelocity()*deltaTime);
        mBullet.addY(mBullet.getyVelocity()*deltaTime);
        mPlatform.setxVelocity(0);
    }


    @Override
    protected void dispose() {
        mBackground.dispose();
        mStarShip.dispose();
        mPlatformTexture.dispose();
        mBulletTexture.dispose();
        mGameOver.dispose();
    }
}
