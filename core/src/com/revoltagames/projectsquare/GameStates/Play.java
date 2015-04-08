package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Entities.BackgroundClock;
import com.revoltagames.projectsquare.Entities.Border;
import com.revoltagames.projectsquare.Entities.SquaresManager;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.Managers.GameStateManager;
import com.revoltagames.projectsquare.Managers.GestureManager;
import com.revoltagames.projectsquare.Managers.ResourceManager;
import com.revoltagames.projectsquare.ProjectSquare;

import java.util.Random;

/**
 * Created by caenrique93 on 17/02/15.
 */
public class Play extends GameState {

    private ShapeRenderer shapeR;
    private SpriteBatch spriteRenderer;

    private Border[] borders;

    private int swipe = 0;
    private boolean gameOver;

    private static Music track;
    private Music move;
    private static Random rnd = new Random(System.currentTimeMillis());

    private int timer;
    private final int timeIncrement;
    private int score;
    private final int scoreIncrement;
    private int phase;
    private int phaseScore;
    private float secondCounter = 0;

    private BitmapFont font;
    private BitmapFont font70;

    private SquaresManager squaresManager;

    private BackgroundClock clock;

    protected Play(GameStateManager gsm, Border[] borders) {
        super(gsm);
        timeIncrement = 15;
        scoreIncrement = 20;
        this.borders = borders;
    }

    protected Play(GameStateManager gsm) {
        super(gsm);
        timeIncrement = 15;
        scoreIncrement = 20;
        borders = new Border[4];
        borders[GestureManager.SW_LEFT - 1] = new Border(GestureManager.SW_LEFT - 1, ColorManager.NBLUE);
        borders[GestureManager.SW_RIGHT - 1] = new Border(GestureManager.SW_RIGHT - 1, ColorManager.NGREEN);
        borders[GestureManager.SW_DOWN - 1] = new Border(GestureManager.SW_DOWN - 1, ColorManager.NYELLOW);
        borders[GestureManager.SW_UP - 1] = new Border(GestureManager.SW_UP - 1, ColorManager.NRED);
        GestureManager.clear();
        for (Border b: borders)
            b.startAnimation();
    }

    @Override
    public void init() {
        shapeR = new ShapeRenderer();
        spriteRenderer = new SpriteBatch();

        track = ProjectSquare.resManager.getMusic(rnd.nextInt(8));
        track.setLooping(true);
        track.play();

        move = ProjectSquare.resManager.getSound(ResourceManager.MOVE);

        timer = 30;
        score = 0;
        phase = 0;
        phaseScore = 0;

        font = new BitmapFont(Gdx.files.internal("Fonts/font1.fnt"),
                Gdx.files.internal("Fonts/font1.png"),
                false);
        font.scale(1.2f);

        font70 = ProjectSquare.resManager.getFont(1);

        gameOver = false;

        squaresManager = new SquaresManager(this);

       /* borders = new Border[4];
        borders[GestureManager.SW_LEFT - 1] = new Border(GestureManager.SW_LEFT - 1, ColorManager.NBLUE);
        borders[GestureManager.SW_RIGHT - 1] = new Border(GestureManager.SW_RIGHT - 1, ColorManager.NGREEN);
        borders[GestureManager.SW_DOWN - 1] = new Border(GestureManager.SW_DOWN - 1, ColorManager.NYELLOW);
        borders[GestureManager.SW_UP - 1] = new Border(GestureManager.SW_UP - 1, ColorManager.NRED);*/
        GestureManager.clear();

        clock = new BackgroundClock(timer);


    }

    @Override
    public void update(float dt) {
        handleInput();

        clock.upade(timer, secondCounter);

        if (gameOver) {
            this.gsm.setState(new GameOver(this.gsm, score));
        }

        secondCounter += dt;
        if (secondCounter >= 1) {
            secondCounter = 0;
            timer--;
        }

        squaresManager.update(dt, swipe);

        // Acaba de deslizar el cuadrado
        if (swipe != 0) {
            score++;
            phaseScore++;
            if (phaseScore == scoreIncrement) {
                phase++;
                timer += timeIncrement;
                phaseScore = 0;
            }
        }

        if (timer == 0) gameOver = true;
    }

    @Override
    public void draw() {
        clock.draw(shapeR);

        for (Border border : borders) {
            border.draw(shapeR);
        }

        squaresManager.draw(shapeR, spriteRenderer);

        drawScore();
    }


    private void drawScore() {
        spriteRenderer.begin();
        BitmapFont.TextBounds timerBound =
                font70.getBounds(Integer.toString(score));
        float timerX = ProjectSquare.WIDTH / 2 - timerBound.width / 2;
        float timerY = 4 * ProjectSquare.HEIGTH / 5 + timerBound.height / 2;

        Color oldColor = spriteRenderer.getColor();
        spriteRenderer.setColor(Color.LIGHT_GRAY);
        font70.draw(spriteRenderer, Integer.toString(score), timerX, timerY);
        spriteRenderer.setColor(oldColor);
        spriteRenderer.end();
    }

    @Override
    public void handleInput() {
        swipe = GestureManager.getSwipe();
    }

    @Override
    public void dispose() {
        track.stop();
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Border[] getBorders() {
        return borders;
    }
}
