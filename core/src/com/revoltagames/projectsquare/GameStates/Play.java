package com.revoltagames.projectsquare.GameStates;

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

    private List<Square> squares;
    Square swipedSquare;

    private Border[] borders;

    private int swipe = 0;


    protected Play(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        shapeR = new ShapeRenderer();
        squares = new LinkedList<Square>();
        squares.add(new Square());
        borders = new Border[4];
        borders[0] = new Border(0, ColorManager.RED);
        borders[1] = new Border(1, ColorManager.BLUE);
        borders[2] = new Border(2, ColorManager.YELLOW);
        borders[3] = new Border(3, ColorManager.GREEN);

        GestureManager.clear();
    }

    @Override
    public void update(float dt) {
        handleInput();

        squares.get(0).update(dt, swipe);

        if (squares.get(0).isOnAnimation()) {
            swipedSquare = squares.get(0);
            squares.add(0, new Square());
        }

        if (swipedSquare != null)
            swipedSquare.update(dt, swipe);

    }

    @Override
    public void draw() {
        shapeR.begin(ShapeRenderer.ShapeType.Filled);

        for (Border border: borders) {
            border.draw(shapeR);
        }

        squares.get(0).draw(shapeR);
        if (swipedSquare != null)
            swipedSquare.draw(shapeR);


        shapeR.end();
    }

    @Override
    public void handleInput() {
        swipe = GestureManager.getSwipe();
    }

    @Override
    public void dispose() {

    }

}
