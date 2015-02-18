package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Entities.CircularButton;
import com.revoltagames.projectsquare.Managers.GameStateManager;

/**
 * Created by caenrique93 on 17/02/15.
 */
public class Menu extends GameState {

    private ShapeRenderer shapeR;
    private CircularButton playB;


    public Menu(GameStateManager gsm) {
        super(gsm);
    }


    @Override
    public void init() {
        shapeR = new ShapeRenderer();
        playB = new CircularButton();
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void draw() {
        shapeR.begin(ShapeRenderer.ShapeType.Filled);
        playB.draw(shapeR);
        shapeR.end();
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (playB.touched(Gdx.input.getX(), Gdx.input.getY())) {
                gsm.setState(new Play(this.gsm));
            }
        }
    }

    @Override
    public void dispose() {

    }
}

