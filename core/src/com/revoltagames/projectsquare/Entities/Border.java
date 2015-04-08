package com.revoltagames.projectsquare.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Entities.Tween.BorderAccessor;
import com.revoltagames.projectsquare.ProjectSquare;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by alejandro on 18/02/15.
 */
public class Border {

    private int[] x, y;
    private float[] vertices;
    private Color color;

    static {
        Tween.registerAccessor(Border.class, new BorderAccessor());
    }

    private static int width = ProjectSquare.WIDTH / 12;
    private static int size_cuadradoMenu = ProjectSquare.WIDTH / 3;
    private static int x0Prima = ProjectSquare.WIDTH/2 - size_cuadradoMenu/2;
    private static int xMaxPrima = ProjectSquare.WIDTH/2 + size_cuadradoMenu/2;
    private static int y0Prima = ProjectSquare.HEIGTH/2 - size_cuadradoMenu/2;
    private static int yMaxPrima = ProjectSquare.HEIGTH/2 + size_cuadradoMenu/2;

    public static final float[] vertices_izquierda_menu = new float[] {
            x0Prima,y0Prima,
            x0Prima, yMaxPrima,
            x0Prima + width, yMaxPrima - width,
            x0Prima + width, y0Prima + width
    };

    public static final float[] vertices_izquierda = new float[] {
            0,0,
            0,ProjectSquare.HEIGTH,
            width, ProjectSquare.HEIGTH-width,
            width, width
    };

    public static final float[] vertices_derecha_menu = new float[] {
            xMaxPrima, y0Prima,
            xMaxPrima, yMaxPrima,
            xMaxPrima - width, yMaxPrima - width,
            xMaxPrima - width, y0Prima + width
    };

    public static final float[] vertices_derecha = new float[] {
            ProjectSquare.WIDTH, 0,
            ProjectSquare.WIDTH, ProjectSquare.HEIGTH,
            ProjectSquare.WIDTH - width, ProjectSquare.HEIGTH - width,
            ProjectSquare.WIDTH - width, width
    };

    public static final float[] vertices_arriba_menu = new float[] {
            x0Prima, yMaxPrima,
            xMaxPrima, yMaxPrima,
            xMaxPrima - width, yMaxPrima - width,
            x0Prima + width, yMaxPrima - width
    };

    public static final float[] vertices_arriba = new float[] {
            0, ProjectSquare.HEIGTH,
            ProjectSquare.WIDTH, ProjectSquare.HEIGTH,
            ProjectSquare.WIDTH - width, ProjectSquare.HEIGTH - width,
            width, ProjectSquare.HEIGTH - width
    };

    public static final float[] vertices_abajo_menu = new float[] {
            x0Prima + width,y0Prima + width,
            x0Prima, y0Prima,
            xMaxPrima, y0Prima,
            xMaxPrima - width, y0Prima + width
    };

    public static final float[] vertices_abajo = new float[] {
            width, width,
            0, 0,
            ProjectSquare.WIDTH, 0,
            ProjectSquare.WIDTH - width, width
    };



    private int type; // 0 izq, 1 der, 2 arrib, 3 abajo
    private static final float[][] vertices_menu = new float[][] {vertices_izquierda_menu, vertices_derecha_menu, vertices_arriba_menu, vertices_abajo_menu};
    private static final float[][] vertices_play = new float[][] {vertices_izquierda, vertices_derecha, vertices_arriba, vertices_abajo};

    public Border (int type, Color color) {
        this.type = type;
        this.color = color;
        x = new int[4];
        y = new int[4];


        switch (type) {
            case 0:
                vertices = vertices_izquierda_menu;
                break;
            case 1:
                vertices = vertices_derecha_menu;
                break;
            case 2:
                vertices = vertices_arriba_menu;
                break;
            case 3:
                vertices = vertices_abajo_menu;
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

    public float[] getVertices() {
        return vertices;
    }

    public void setVertices(float[] newvertices) {
        this.vertices = new float[8];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = newvertices[i];
        }
    }


    public void startAnimation() {
        Tween.set(this, BorderAccessor.POS_AND_SIZE).target(vertices_menu[type]).start(ProjectSquare.tweenManager);
        Tween.to(this, BorderAccessor.POS_AND_SIZE, 1).target(vertices_play[type]).start(ProjectSquare.tweenManager);
    }

}
