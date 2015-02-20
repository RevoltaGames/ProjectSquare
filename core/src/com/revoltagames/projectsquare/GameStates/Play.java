package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.audio.Music;
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

    protected Play(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        shapeR = new ShapeRenderer();
        spriteRenderer = new SpriteBatch();
        squares = new LinkedList<Square>();

        track= ProjectSquare.resourceManager.getMusic(rnd.nextInt(8));
        track.setLooping(true);
        track.play();


        gameOver = false;
        Square.squareNumbers = 0;

        squares.add(new Square());
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
        move= ProjectSquare.resourceManager.getMusic(ResourceManager.MOVE);
        squares.get(0).update(dt, swipe);r

        if(swipe != 0) {
            if (squares.get(0).getColor() != borders[swipe-1].getColor()) {
                track.stop();
                gameOver = true;
            }
        }

        if (squares.get(0).isOnAnimation()) {
            move.stop();
            move.play();
            swipedSquare = squares.get(0);
            squares.add(0, new Square());
        }

        if (swipedSquare != null) {
            swipedSquare.update(dt, swipe);
        }

        if(gameOver) {
            this.gsm.setState(new GameOver(this.gsm));
        }

    }

    @Override
    public void draw() {
        for (Border border: borders) {
            border.draw(shapeR);
        }

        squares.get(0).draw(shapeR, spriteRenderer);
        if (swipedSquare != null)
            swipedSquare.draw(shapeR, spriteRenderer);
    }

    @Override
    public void handleInput() {
        swipe = GestureManager.getSwipe();
    }

    @Override
    public void dispose() {

    }

}
