package com.revoltagames.projectsquare.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.Managers.GestureManager;
import com.revoltagames.projectsquare.ProjectSquare;

import sun.org.mozilla.javascript.ast.Yield;

/**
 * Created by alejandro on 18/02/15.
 */
public class Square  {
    public static int squareNumbers = 0;

    private int x, y;
    private int dx, dy;

    private int number;
    private BitmapFont font;

    private int size;

    private int vel;

    Color color;

    private boolean onAnimation = false;

    //Para la previsualizacion
    private int[] miniSize;
    private int[] prevX;
    private int prevY;

    //Para animacion
    private float animationTime = 0.4f;
    private float animationTimer = 0;
    private int xAni, yAni;
    private int sizeAni;
    boolean colocado = false;

    public Square() {
        x = ProjectSquare.WIDTH / 2;
        y = ProjectSquare.HEIGTH / 2;

        number = squareNumbers++;
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.scale(3.0f);

        dx = dy = 0;

        size = ProjectSquare.WIDTH / 3;

        vel = ProjectSquare.HEIGTH * 5;

        color = ColorManager.randomColor(4);

        miniSize = new int[] {
                size/4,
                size/6,
                size/8
        };

        prevX = new int[3];

        prevX[0] = x - size/2 + size/6;
        prevX[1] = prevX[0] + miniSize[0] + miniSize[0]/6;
        prevX[2] = prevX[1] + miniSize[1] + miniSize[0]/6;

        prevY = y + size / 2 + miniSize[0] / 10;

    }

    public void draw(ShapeRenderer shapeR, SpriteBatch spRenderer) {
        if (colocado) {
            shapeR.begin(ShapeRenderer.ShapeType.Filled);
            Color oldColor = shapeR.getColor();
            shapeR.setColor(color);
            shapeR.rect(x - size / 2, y - size / 2, size, size);
            shapeR.setColor(oldColor);
            shapeR.end();

            spRenderer.begin();
            BitmapFont.TextBounds bounds = font.getBounds(Integer.toString(number));
            font.draw(spRenderer,
                    Integer.toString(number),
                    this.x - bounds.width / 2,
                    this.y + bounds.height / 2);
            spRenderer.end();
        } else {
            drawTransition(shapeR);
        }
    }

    private void drawTransition(ShapeRenderer shapeR) {
        shapeR.begin(ShapeRenderer.ShapeType.Filled);
        Color oldColor = shapeR.getColor();
        shapeR.setColor(color);
        shapeR.rect(xAni, yAni, sizeAni, sizeAni);
        shapeR.setColor(oldColor);
        shapeR.end();
    }

    public void drawAsNext(ShapeRenderer shapeR, int pos) {
        shapeR.begin(ShapeRenderer.ShapeType.Filled);
        Color oldColor = shapeR.getColor();
        shapeR.setColor(color);
        shapeR.rect(prevX[pos], prevY, miniSize[pos], miniSize[pos]);
        shapeR.setColor(oldColor);
        shapeR.end();
    }

    public void update(float dt, int swipe) {
        if (!colocado) {
            updatePrevToFirstAnimation(dt);
        } else {
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

            x += dx * vel * dt;
            y += dy * vel * dt;
        }
    }

    public void updatePrevToFirstAnimation(float dt) {
        animationTimer += dt;
        sizeAni = (int) (animationTimer * (size-miniSize[0])/animationTime);
        xAni = prevX[0] + (int) (animationTimer * (((x-size/2)-prevX[0])/animationTime));
        yAni = prevY + (int) (animationTimer * (((y-size/2)-prevY)/animationTime));
        if (animationTime < animationTimer)
            colocado = true;
    }

    public boolean isOnAnimation() {
        return onAnimation;
    }

    public Color getColor() {
        return color;
    }

    public void setColocado(boolean b) {
        colocado = b;
    }
}
