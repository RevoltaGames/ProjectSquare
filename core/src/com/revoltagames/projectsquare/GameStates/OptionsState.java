package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Entities.OptionItem;
import com.revoltagames.projectsquare.Entities.Switch;
import com.revoltagames.projectsquare.Managers.GameStateManager;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Created by alejandro on 6/04/16.
 */
public class OptionsState extends GameState {

    private ShapeRenderer shapeR;

    private OptionItem test;

    public OptionsState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        shapeR = new ShapeRenderer();
        test = new OptionItem(ProjectSquare.WIDTH/2, ProjectSquare.HEIGTH/2, true, "hola k ase");
    }

    @Override
    public void update(float dt) {
        this.handleInput();
    }

    @Override
    public void draw() {
        test.draw(shapeR);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (test.touched(Gdx.input.getX(), Gdx.input.getY()))
                test.toggle();
        }
    }

    @Override
    public void dispose() {

    }
}
