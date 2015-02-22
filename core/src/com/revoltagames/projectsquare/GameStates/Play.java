package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Entities.Border;
import com.revoltagames.projectsquare.Entities.Square;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.Managers.GameStateManager;
import com.revoltagames.projectsquare.Managers.GestureManager;

import java.util.LinkedList;
import java.util.List;

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


    protected Play(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        shapeR = new ShapeRenderer();
        spriteRenderer = new SpriteBatch();
        squares = new LinkedList<Square>();

        gameOver = false;
        Square.squareNumbers = 0;

        for (int i = 0; i < 4; i++)
            squares.add(new Square());
        squares.get(0).setColocado(true);

        borders = new Border[4];
        borders[GestureManager.SW_LEFT-1] = new Border(GestureManager.SW_LEFT-1, ColorManager.GREEN);
        borders[GestureManager.SW_RIGHT-1] = new Border(GestureManager.SW_RIGHT-1, ColorManager.BLUE);
        borders[GestureManager.SW_DOWN-1] = new Border(GestureManager.SW_DOWN-1, ColorManager.YELLOW);
        borders[GestureManager.SW_UP-1] = new Border(GestureManager.SW_UP-1, ColorManager.RED);

        GestureManager.clear();
    }

    @Override
    public void update(float dt) {
        handleInput();

        squares.get(0).update(dt, swipe);

        if(swipe != 0) {
            if (squares.get(0).getColor() != borders[swipe-1].getColor()) {
                gameOver = true;
            }
        }

        if (squares.get(0).isOnAnimation()) {
            swipedSquare = squares.get(0);
            squares.remove(0);
            squares.add(new Square());
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
        squares.get(1).drawAsNext(shapeR, 0);
        squares.get(2).drawAsNext(shapeR, 1);
        squares.get(3).drawAsNext(shapeR, 2);

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
