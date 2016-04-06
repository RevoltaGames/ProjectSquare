package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Managers.GameStateManager;
import com.revoltagames.projectsquare.ProjectSquare;
import com.revoltagames.projectsquare.widgets.OptionWidget;

/**
 * Created by alejandro on 6/04/16.
 */
public class SettingsState extends GameState {

    private ShapeRenderer shapeR;
    private SpriteBatch spriteB;

    private OptionWidget test;

    public SettingsState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        shapeR = new ShapeRenderer();
        spriteB = new SpriteBatch();
        test = new OptionWidget(ProjectSquare.WIDTH/2, ProjectSquare.HEIGTH/2, true, "hola k ase");
    }

    @Override
    public void update(float dt) {
        this.handleInput();
        this.changeSettings();
    }

    private void changeSettings() {
        ProjectSquare.settingsManager.godMode = test.getState();
    }

    @Override
    public void draw() {
        test.draw(shapeR, spriteB);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (test.touched(Gdx.input.getX(), Gdx.input.getY()))
                test.toggle();
            else
                this.gsm.pop();
        }
    }

    @Override
    public void dispose() {

    }
}
