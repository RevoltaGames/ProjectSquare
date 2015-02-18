package com.revoltagames.projectsquare.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Created by alejandro on 18/02/15.
 */
public class Border {

    private int x, y, width, heigth;
    private Color color;

    private int type; // 0 izq, 1 der, 2 arrib, 3 abajo

    public Border (int type, Color color) {
        this.type = type;
        this.color = color;

        switch (type) {
            case 0:
                width = ProjectSquare.WIDTH / 10;
                heigth = ProjectSquare.HEIGTH;
                x = 0;
                y = 0;
                break;
            case 1:
                width = ProjectSquare.WIDTH / 10;
                heigth = ProjectSquare.HEIGTH;
                x = ProjectSquare.WIDTH - width;
                y = 0;
                break;
            case 2:
                width = ProjectSquare.WIDTH;
                heigth = ProjectSquare.WIDTH / 10;
                x = 0;
                y = 0;
                break;
            case 3:
                width = ProjectSquare.WIDTH;
                heigth = ProjectSquare.WIDTH / 10;
                x = 0;
                y = ProjectSquare.HEIGTH - heigth;
        }
    }

    public void draw(ShapeRenderer shapeR) {
        shapeR.begin(ShapeRenderer.ShapeType.Filled);
        Color oldColor = shapeR.getColor();
        shapeR.setColor(color);
        shapeR.rect(x,y,width,heigth);
        shapeR.setColor(oldColor);
        shapeR.end();
     }

    public Color getColor() {
        return color;
    }

}
