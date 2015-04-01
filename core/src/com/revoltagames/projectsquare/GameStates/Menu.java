package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Entities.Button;
import com.revoltagames.projectsquare.Entities.Shapes.Circle;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.Managers.GameStateManager;
import com.revoltagames.projectsquare.Managers.ResourceManager;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Created by caenrique93 on 17/02/15.
 */
public class Menu extends GameState {

    private ShapeRenderer shapeR;
    private Button playB;
    private Button highScores;
    private Music track;


    public Menu(GameStateManager gsm) {
        super(gsm);
    }


    @Override
    public void init() {
        track= ProjectSquare.rm.getSound(ResourceManager.MENU);
        track.play();
        shapeR = new ShapeRenderer();
        Button.setDefaultShape(new Circle(
                ProjectSquare.WIDTH/2,
                ProjectSquare.HEIGTH/4,
                ProjectSquare.WIDTH/8,
                ColorManager.LIGHT_BLUE
        ));
        playB = new Button();
        highScores = new Button(ProjectSquare.WIDTH/2, ProjectSquare.HEIGTH/2);
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void draw() {
        playB.draw(shapeR);
        highScores.draw(shapeR);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (playB.touched(Gdx.input.getX(), Gdx.input.getY())) {
                track.stop();
                gsm.setState(new Play(this.gsm));
            }
            if (highScores.touched(Gdx.input.getX(), Gdx.input.getY())) {
                track.stop();
                gsm.push(new HighScores(this.gsm));
            }
        }
    }

    @Override
    public void dispose() {

    }
}

