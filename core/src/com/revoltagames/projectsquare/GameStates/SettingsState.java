package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Managers.GameStateManager;
import com.revoltagames.projectsquare.ProjectSquare;
import com.revoltagames.projectsquare.widgets.OptionWidget;
import com.revoltagames.projectsquare.widgets.ToggleWidget;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by alejandro on 6/04/16.
 */
public class SettingsState extends GameState {

    private ShapeRenderer shapeR;
    private SpriteBatch spriteB;

    private ToggleWidget test;
    private OptionWidget testOption;

    public SettingsState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        shapeR = new ShapeRenderer();
        spriteB = new SpriteBatch();
        test = new ToggleWidget(ProjectSquare.WIDTH/2, 2*ProjectSquare.HEIGTH/3, ProjectSquare.settingsManager.godMode, "God mode");
        testOption = new OptionWidget(
                ProjectSquare.WIDTH/2,
                ProjectSquare.HEIGTH/3,
                "Dificulty",
                new ArrayList<String>(Arrays.asList("1", "2", "3")),
                ProjectSquare.settingsManager.dificulty%3
        );
    }

    @Override
    public void update(float dt) {
        this.handleInput();
        this.changeSettings();
    }

    private void changeSettings() {
        ProjectSquare.settingsManager.godMode = test.getState();
        ProjectSquare.settingsManager.dificulty = testOption.getOption();
    }

    @Override
    public void draw() {
        testOption.draw(shapeR, spriteB);
        test.draw(shapeR, spriteB);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            testOption.touched(Gdx.input.getX(), Gdx.input.getY());
            if (test.touched(Gdx.input.getX(), Gdx.input.getY()))
                test.toggle();
            else if (!testOption.isTouched(Gdx.input.getX(), Gdx.input.getY()))
                this.gsm.pop();
        }
    }

    @Override
    public void dispose() {

    }
}
