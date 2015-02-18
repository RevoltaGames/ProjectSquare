package com.revoltagames.projectsquare.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Created by alejandro on 18/02/15.
 */
public class CircularButton {
    int x,y;
    int radio;

    public CircularButton() {
        x = ProjectSquare.WIDTH / 2;
        y = ProjectSquare.HEIGTH / 4;
        radio = ProjectSquare.WIDTH / 9;
    }

    public void draw(ShapeRenderer shapeR) {
        Color oldColor = shapeR.getColor();
        shapeR.setColor(3/255f,169/255f,244/255f,1);
        shapeR.circle(x, y, radio, 1000);
        shapeR.setColor(oldColor);
    }

    public boolean touched(int tx, int ty) {
        ty = ProjectSquare.HEIGTH - ty;
        if (Math.abs(tx-x) < radio && Math.abs(ty-y) < radio)
            return true;
        return false;
    }
}
