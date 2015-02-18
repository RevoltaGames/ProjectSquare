package com.revoltagames.projectsquare.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Managers.GestureManager;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Created by alejandro on 18/02/15.
 */
public class Square  {

    private int x, y;
    private int dx, dy;

    private int size;

    private int vel;

    private boolean onAnimation = false;

    Color color;

    public Square() {
        x = ProjectSquare.WIDTH / 2;
        y = ProjectSquare.HEIGTH / 2;

        dx = dy = 0;

        size = ProjectSquare.WIDTH * 6 / 10;

        vel = ProjectSquare.HEIGTH * 5;

        // TODO Colores aleatorios
        color = new Color(3/255f,169/255f,244/255f,1);
    }

    public void draw(ShapeRenderer shapeR) {
        Color oldColor = shapeR.getColor();
        shapeR.setColor(color);

        shapeR.rect(x-size/2, y-size/2, size, size);

        shapeR.setColor(oldColor);
    }

    public void update(float dt, int swipe) {
        if (!onAnimation) {
            switch (swipe) {
                case GestureManager.SW_DOWN:
                    dy = -1;
                    onAnimation = true;
                    break;
                case GestureManager.SW_LEFT:
                    dx = -1;
                    onAnimation = true;
                    break;
                case GestureManager.SW_RIGHT:
                    dx = 1;
                    onAnimation = true;
                    break;
                case GestureManager.SW_UP:
                    dy = 1;
                    onAnimation = true;
                    break;
                default:
            }
        }

        x += dx*vel*dt;
        y += dy*vel*dt;
    }

    public boolean isOnAnimation() {
        return onAnimation;
    }
}
