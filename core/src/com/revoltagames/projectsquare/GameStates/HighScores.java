package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.revoltagames.projectsquare.Managers.GameStateManager;
import com.revoltagames.projectsquare.ProjectSquare;

import java.util.ArrayList;


/**
 * Created by caenrique93 on 28/02/15.
 */
public class HighScores extends GameState {
    private ArrayList<Integer> scores;
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
        font = ProjectSquare.resManager.getFont(2);
        bounds = font.getBounds(mesage);
        textWidth = bounds.width;
        scores = ProjectSquare.dataManager.getHighScores();
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
            String score = scores.get(i).toString();
            numberBound = font.getBounds(score);
            font.draw(renderer, score, ProjectSquare.WIDTH/2 - numberBound.width/2, 3*ProjectSquare.HEIGTH/4 - numberBound.height/2 - 2*gap - i*gap);
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
