package com.revoltagames.projectsquare.Entities.Shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Interfaz usada para definir formaas geométricas
 */
public interface Shape {
    public void draw(ShapeRenderer renderer);
    public boolean touched(float tx, float ty);
    public Rectangle getBoundaries();
    public Color getColor();
    public void setColor(Color color);
    public void setX(float x);
    public void setY(float y);
    public float getX();
    public float getY();
    public Shape clone();
}
