package com.revoltagames.projectsquare.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Created by alejandro on 15/03/15.
 */
public class Clock {

    private float alpha;
    private Color color;
    private int max;

    private int anterior;

    public Clock(int max) {

        color = ColorManager.ColorClock;
        this.max = max;
        alpha = 1;
        anterior = max;
    }

    public void draw(ShapeRenderer shapeR){
        Color oldColor = shapeR.getColor();
        shapeR.setColor(color);
        shapeR.begin(ShapeRenderer.ShapeType.Filled);

            // Fondo
            shapeR.setColor(ColorManager.ColorClockBack);
            shapeR.rect(ProjectSquare.WIDTH * 2 / 12, 3 * ProjectSquare.HEIGTH / 16, ProjectSquare.WIDTH * 8 / 12, ProjectSquare.HEIGTH / 50);

            // Barra
            shapeR.setColor(color);
            shapeR.rect(ProjectSquare.WIDTH * 2 / 12, 3 * ProjectSquare.HEIGTH / 16, alpha * ProjectSquare.WIDTH * 8 / 12, ProjectSquare.HEIGTH / 50);


        shapeR.end();
        shapeR.setColor(oldColor);
    }

    public void upade(int timer, float seconCounter) {
        if (timer > max)
            max = timer;
        else if (anterior < timer)
            max = timer;

        alpha = (timer - seconCounter) / (float)max ;
        anterior = timer;
    }
}
