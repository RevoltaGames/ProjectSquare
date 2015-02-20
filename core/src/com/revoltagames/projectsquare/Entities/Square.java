package com.revoltagames.projectsquare.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.Managers.GestureManager;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Created by alejandro on 18/02/15.
 */
public class Square  {
    public static int squareNumbers = 0;

    private int x, y;
    private int dx, dy;

    private int number;
    private BitmapFont font;
    SpriteBatch spRenderer1;

    private int size;

    private int vel;

    private boolean onAnimation = false;

    Color color;

    public Square() {
        x = ProjectSquare.WIDTH / 2;
        y = ProjectSquare.HEIGTH / 2;

        number = squareNumbers++;

        dx = dy = 0;

        size = ProjectSquare.WIDTH / 3;

        vel = ProjectSquare.HEIGTH * 5;

        spRenderer1 = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("Fonts/font1.fnt"),
                Gdx.files.internal("Fonts/font1.png"), false);
        font.scale(1.2f);
        color = ColorManager.randomColor(4);
    }

    public void draw(ShapeRenderer shapeR, SpriteBatch spRenderer) {
        shapeR.begin(ShapeRenderer.ShapeType.Filled);
        Color oldColor = shapeR.getColor();
        shapeR.setColor(color);
        shapeR.rect(x-size/2, y-size/2, size, size);
        shapeR.setColor(oldColor);
        shapeR.end();

        BitmapFont.TextBounds bounds = font.getBounds(Integer.toString(number));
        spRenderer1.begin();
        font.draw(spRenderer1,
                Integer.toString(number),
                this.x-bounds.width/2,
                this.y+bounds.height/2);
        spRenderer1.end();
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

    public Color getColor() {
        return color;
    }
}
