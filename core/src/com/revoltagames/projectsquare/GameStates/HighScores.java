package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.revoltagames.projectsquare.Managers.GameStateManager;
import com.revoltagames.projectsquare.ProjectSquare;


/**
 * Created by caenrique93 on 28/02/15.
 */
public class HighScores extends GameState {
    private FileHandle scoreHandle;
    private String scores;
    private SpriteBatch renderer;
    BitmapFont font;
    String mesage;
    String[] s;
    BitmapFont.TextBounds bounds;
    float gap;
    private float textWidth;


    protected HighScores(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        scoreHandle = Gdx.files.local("highScores");
        scores = scoreHandle.readString();
        s = scores.split(" ");
        mesage = "HighScores";
        renderer = new SpriteBatch();
        font = ProjectSquare.resManager.getFont(2);
        bounds = font.getBounds(mesage);
        textWidth = bounds.width;
        System.out.println(bounds.width + " " + bounds.height);
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
        for(int i=0;i<4;i++) {
            numberBound = font.getBounds(s[i]);
            font.draw(renderer, s[i], ProjectSquare.WIDTH/2 - numberBound.width/2, 3*ProjectSquare.HEIGTH/4 - numberBound.height/2 - 2*gap - i*gap);
        }
        renderer.end();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            gsm.pop();
            System.out.println("touched");
        }
    }

    @Override
    public void dispose() {

    }
}
