package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.revoltagames.projectsquare.Managers.GameStateManager;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Created by caenrique93 on 17/02/15.
 */
public class Menu extends GameState {

    private ShapeRenderer shapeR;
    private PlayButton playB;


    public Menu(GameStateManager gsm) {
        super(gsm);
    }


    @Override
    public void init() {
        shapeR = new ShapeRenderer();
        playB = new PlayButton();
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void draw() {
        shapeR.begin(ShapeRenderer.ShapeType.Filled);
        playB.draw(shapeR);
        shapeR.end();
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (playB.touched(Gdx.input.getX(), Gdx.input.getY())) {
                gsm.setState(new Play(this.gsm));
            }
        }
    }

    @Override
    public void dispose() {

    }
}

class PlayButton {
    int x,y;
    int radio;

    PlayButton() {
        x = ProjectSquare.WIDTH / 2;
        y = ProjectSquare.HEIGTH / 4;
        radio = ProjectSquare.WIDTH / 9;
    }

    public void draw(ShapeRenderer shapeR) {
        Color oldColor = shapeR.getColor();
        shapeR.setColor(3/255f,169/255f,244/255f,1);
        shapeR.circle(x,y,radio);
        shapeR.setColor(oldColor);
    }

    public boolean touched(int tx, int ty) {
        ty = ProjectSquare.HEIGTH - ty;
        if (Math.abs(tx-x) < radio && Math.abs(ty-y) < radio)
            return true;
        return false;
    }
}