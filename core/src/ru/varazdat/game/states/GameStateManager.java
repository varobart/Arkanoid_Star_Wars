package ru.varazdat.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Вараздат on 01.01.2018.
 */

public class GameStateManager {

    private Stack<State> states;


    public GameStateManager(){
        states = new Stack<State>();
    }


    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop();
    }

    public void set(State state){
        states.pop();
        states.push(state);
    }

    public void render(SpriteBatch spriteBatch){
        states.peek().render(spriteBatch);
    }

    public void update(float deltaTime){
        states.peek().update(deltaTime);
    }

    public void update(int width, int height){
        states.peek().getViewport().update(width, height);
    }



}
