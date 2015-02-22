package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Entities.Border;
import com.revoltagames.projectsquare.Entities.Square;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.Managers.GameStateManager;
import com.revoltagames.projectsquare.Managers.GestureManager;
import com.revoltagames.projectsquare.Managers.ResourceManager;
import com.revoltagames.projectsquare.ProjectSquare;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by caenrique93 on 17/02/15.
 */
public class Play extends GameState {


    private ShapeRenderer shapeR;
    private SpriteBatch spriteRenderer;

    private List<Square> squares;
    Square swipedSquare;

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
    private long currentTime;
    private long lastTime;

    private BitmapFont font;

    protected Play(GameStateManager gsm) {
        super(gsm);
        timeIncrement = 15;
        scoreIncrement = 20;
    }

    @Override
    public void init() {
        shapeR = new ShapeRenderer();
        spriteRenderer = new SpriteBatch();
        squares = new LinkedList<Square>();

        track= ProjectSquare.resourceManager.getMusic(rnd.nextInt(8));
        track.setLooping(true);
        track.play();

        move = ProjectSquare.resourceManager.getMusic(ResourceManager.MOVE);

        timer = 30;
        score = 0;
        phase = 0;
        phaseScore = 0;
        lastTime = System.currentTimeMillis();

        font = new BitmapFont(Gdx.files.internal("Fonts/font1.fnt"),
                Gdx.files.internal("Fonts/font1.png"),
                false);
        font.scale(1.2f);

        gameOver = false;
        Square.squareNumbers = 0;

        for (int i = 0; i < 4; i++)
            squares.add(new Square());
        squares.get(0).setColocado(true);

        borders = new Border[4];
        borders[GestureManager.SW_LEFT-1] = new Border(GestureManager.SW_LEFT-1, ColorManager.NBLUE);
        borders[GestureManager.SW_RIGHT-1] = new Border(GestureManager.SW_RIGHT-1, ColorManager.NGREEN);
        borders[GestureManager.SW_DOWN-1] = new Border(GestureManager.SW_DOWN-1, ColorManager.NYELLOW);
        borders[GestureManager.SW_UP-1] = new Border(GestureManager.SW_UP-1, ColorManager.NRED);
        GestureManager.clear();
    }

    @Override
    public void update(float dt) {
        handleInput();

        if(gameOver) {
            this.gsm.setState(new GameOver(this.gsm, score));
        }

        currentTime = System.currentTimeMillis();
        if (currentTime - lastTime >= 1000) {
            lastTime = currentTime;
            timer--;
        }

        squares.get(0).update(dt, swipe);

        // Acaba de deslizar el cuadrado
        if(swipe != 0) {
            if (squares.get(0).getColor() != borders[swipe-1].getColor()) {
                track.stop();
                gameOver = true;
            }
            score++;
            phaseScore++;
            if (phaseScore == scoreIncrement) {
                phase++;
                timer += timeIncrement;
                phaseScore = 0;
            }
        }

        if (timer == 0) gameOver = true;

        if (squares.get(0).isOnAnimation()) {
            move.stop();
            move.play();
            swipedSquare = squares.get(0);
            squares.remove(0);
            squares.add(new Square());
        }

        if (swipedSquare != null) {
            swipedSquare.update(dt, swipe);
        }
    }

    @Override
    public void draw() {
        for (Border border: borders) {
            border.draw(shapeR);
        }

        squares.get(0).draw(shapeR, spriteRenderer);
        squares.get(1).drawAsNext(shapeR, 0);
        squares.get(2).drawAsNext(shapeR, 1);
        squares.get(3).drawAsNext(shapeR, 2);

        if (swipedSquare != null)
            swipedSquare.draw(shapeR, spriteRenderer);

        drawTimer();
    }

    private void drawTimer() {
        spriteRenderer.begin();
        BitmapFont.TextBounds timerBound = font.getBounds(Integer.toString(timer));
        float timerX = ProjectSquare.WIDTH/2 - timerBound.width/2;
        float timerY = 4*ProjectSquare.HEIGTH/5 + timerBound.height/2;

        Color oldColor = spriteRenderer.getColor();
        spriteRenderer.setColor(Color.LIGHT_GRAY);
        font.draw(spriteRenderer, Integer.toString(timer), timerX, timerY);
        spriteRenderer.setColor(oldColor);
        spriteRenderer.end();
    }

    @Override
    public void handleInput() {
        swipe = GestureManager.getSwipe();
    }

    @Override
    public void dispose() {

    }

}
