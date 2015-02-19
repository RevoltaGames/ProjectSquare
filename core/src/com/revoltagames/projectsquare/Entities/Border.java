package com.revoltagames.projectsquare.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Created by alejandro on 18/02/15.
 */
public class Border {

    private int[] x, y;
    private float[] vertices;
    private int width, heigth;
    private Color color;

    private int type; // 0 izq, 1 der, 2 arrib, 3 abajo

    public Border (int type, Color color) {
        this.type = type;
        this.color = color;
        x = new int[4];
        y = new int[4];


        width = ProjectSquare.WIDTH / 12;

        switch (type) {
            case 0:
                vertices = new float[]{
                        0,0,
                        0,ProjectSquare.HEIGTH,
                        width, ProjectSquare.HEIGTH-width,
                        width, width
                };
                break;
            case 1:
                vertices = new float[] {
                        ProjectSquare.WIDTH, 0,
                        ProjectSquare.WIDTH, ProjectSquare.HEIGTH,
                        ProjectSquare.WIDTH - width, ProjectSquare.HEIGTH - width,
                        ProjectSquare.WIDTH - width, width
                };
                break;
            case 2:
                vertices = new float[] {
                        0, ProjectSquare.HEIGTH,
                        ProjectSquare.WIDTH, ProjectSquare.HEIGTH,
                        ProjectSquare.WIDTH - width, ProjectSquare.HEIGTH - width,
                        width, ProjectSquare.HEIGTH - width
                };
                break;
            case 3:
                vertices = new float[] {
                        width, width,
                        0, 0,
                        ProjectSquare.WIDTH, 0,
                        ProjectSquare.WIDTH - width, width
                };
        }
    }

    public void draw(ShapeRenderer shapeR) {
        shapeR.begin(ShapeRenderer.ShapeType.Filled);
        Color oldColor = shapeR.getColor();
        shapeR.setColor(color);
        shapeR.triangle(vertices[0], vertices[1], vertices[2], vertices[3], vertices[4], vertices[5]);
        shapeR.triangle(vertices[0], vertices[1], vertices[4], vertices[5], vertices[6], vertices[7]);
        shapeR.setColor(oldColor);
        shapeR.end();
     }

    public Color getColor() {
        return color;
    }

}
