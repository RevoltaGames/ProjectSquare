package com.revoltagames.projectsquare.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.revoltagames.projectsquare.Entities.Shapes.Shape;
import com.revoltagames.projectsquare.Managers.ColorManager;

/**
 * Created by Truji on 07/04/2016.
 */
public class ArrowButton extends Button {

    private int direction;

    private float[] vertices;

    /**
     * Constructor
     * @param shape
     * @param direction 0: Arriba, 1: Abajo
     */
    public ArrowButton(Shape shape, int direction) {
        super(shape);
        this.direction = direction;
        genVertices(shape);
    }

    @Override
    public void draw(ShapeRenderer renderer) {
        super.draw(renderer);
        drawArrow(renderer);
    }

    private void drawArrow(ShapeRenderer renderer) {
        Color oldColor = renderer.getColor();
        renderer.setColor(ColorManager.WHITE);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
            renderer.triangle(vertices[0],vertices[1],vertices[2],vertices[3],vertices[4],vertices[5]);
        renderer.end();

        renderer.setColor(oldColor);
    }

    private void genVertices(Shape shape) {
        float x, y, w, h;
        Rectangle r = shape.getBoundaries();
        x = r.getX();
        y = r.getY();
        w = r.getWidth();
        h = r.getHeight();
        float f = 6; // Escala del triangulo (mayor f menor triangulo)

        switch (direction) {
            case 0:
                vertices = new float[] {
                    x, y + h/(f+1),
                    x-w/(f-1), y-h/(f+1),
                    x+w/(f-1), y-h/(f+1)
                };
                break;
            case 1:
                vertices = new float[] {
                    x, y - h/(f+1),
                    x+w/(f-1), y+h/(f+1),
                    x-w/(f-1), y+h/(f+1)
                };
                break;
            default:
                vertices = new float[0];
        }
    }
}
