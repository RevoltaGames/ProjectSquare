package com.revoltagames.projectsquare.Entities.Shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Created by caenrique93 on 15/03/15.
 */
public class Circle implements Shape {
    float x, y;
    float radio;
    Color color;

    public Circle(float x, float y, float radio, Color color) {
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.color = color;
    }

    public void draw(ShapeRenderer renderer) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        Color oldColor = renderer.getColor();
        renderer.setColor(color);
        renderer.circle(x, y, radio, 500);
        renderer.setColor(oldColor);
        renderer.end();
    }

    public boolean touched(float tx, float ty) {
        ty = ProjectSquare.HEIGTH - ty;
        if (Math.abs(tx-x) < radio && Math.abs(ty-y) < radio)
            return true;
        return false;
    }

    @Override
    public Rectangle getBoundaries() {
        return new Rectangle(x, y, radio, radio);
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

    @Override
    public Shape clone() {
        return new Circle(this.x, this.y, this.radio, this.color);
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRadio() {
        return radio;
    }

    public void setRadio(float radio) {
        this.radio = radio;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
