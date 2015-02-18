package com.revoltagames.projectsquare.GameStates;

import com.revoltagames.projectsquare.Managers.GameStateManager;

/**
 * Created by caenrique93 on 17/02/15.
 */
public class Play extends GameState {

    protected Play(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void draw() {

    }

    @Override
    public void handleInput() {
        
    }

    @Override
    public void dispose() {

    }
}
