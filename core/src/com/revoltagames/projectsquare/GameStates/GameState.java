package com.revoltagames.projectsquare.GameStates;

import com.revoltagames.projectsquare.Managers.GameStateManager;

/**
 * Created by caenrique93 on 17/02/15.
 */
public abstract class GameState {

    protected GameStateManager gsm;

    protected GameState(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }

    public abstract void init();
    public abstract void update(float dt);
    public abstract void draw();
    public abstract void handleInput();
    public abstract void dispose();
}
