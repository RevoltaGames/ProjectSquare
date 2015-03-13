package com.revoltagames.projectsquare.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Created by alejandro on 18/02/15.
 */
public class Square {
    private float x, y, size;
    private Color color;
    private float alpha;

    private int swipe;


    public Square(float x, float y, float size) {
        this.x = x;
        this.y = y;
        this.size = size;
        color = ColorManager.randomColor(4);
        alpha = 0.6f;
    }

    public void draw(ShapeRenderer shapeR) {
        shapeR.begin(ShapeRenderer.ShapeType.Filled);
        Color oldColor = shapeR.getColor();
        shapeR.setColor(color.r, color.g, color.b, alpha);
        shapeR.rect(x - size / 2, y - size / 2, size, size);
        shapeR.setColor(oldColor);
        shapeR.end();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public int getSwipe() {
        return swipe;
    }

    public void setSwipe(int swipe) {
        this.swipe = swipe;
    }

    public boolean atBorder() {
        int w = ProjectSquare.WIDTH;
        int h = ProjectSquare.HEIGTH;
        return x >= w || x <= 0 || y <= 0|| y>= h;
    }

    public Color getColor() {
        return color;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public float getAlpha() {
        return alpha;
    }
}

