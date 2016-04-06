package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.revoltagames.projectsquare.Entities.Score;
import com.revoltagames.projectsquare.Managers.GameStateManager;
import com.revoltagames.projectsquare.ProjectSquare;

import java.util.List;


/**
 * Created by caenrique93 on 28/02/15.
 */
public class HighScores extends GameState {
    private List<Score> scores;
    private SpriteBatch renderer;
    BitmapFont font;
    String mesage;
    BitmapFont.TextBounds bounds;
    float gap;
    private float textWidth;


    protected HighScores(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        mesage = "HighScores";
        renderer = new SpriteBatch();
        font = ProjectSquare.resManager.getFont(7);
        bounds = font.getBounds(mesage);
        textWidth = bounds.width;
        scores = ProjectSquare.settingsManager.getScores();
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void draw() {
        BitmapFont.TextBounds numberBound;
        renderer.begin();
        font.draw(renderer, mesage, ProjectSquare.WIDTH/2 - textWidth/2, 3 * ProjectSquare.HEIGTH / 4 - bounds.height / 2);
        gap = font.getLineHeight();
        for(int i=0;i<5;i++) {
            Score sc = scores.get(i);
            String score = String.valueOf(sc.puntuacion);
            String name = sc.nombre;
            String scoreName = score + " " + name;
            numberBound = font.getBounds(scoreName);
            font.draw(renderer, scoreName, ProjectSquare.WIDTH/2 - numberBound.width/2, 3*ProjectSquare.HEIGTH/4 - numberBound.height/2 - 2*gap - i*gap);
        }
        renderer.end();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            gsm.pop();
        }
    }

    @Override
    public void dispose() {

    }
}
