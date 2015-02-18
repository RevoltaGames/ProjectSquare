package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.revoltagames.projectsquare.Managers.GameStateManager;

/**
 * Created by caenrique93 on 18/02/15.
 */
public class LoadState extends GameState{
    SpriteBatch renderer;

    public LoadState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        renderer = new SpriteBatch();
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void draw() {
        renderer.begin();
        // TODO render load progress
        renderer.end();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void dispose() {

    }
}
