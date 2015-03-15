package com.revoltagames.projectsquare.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Created by alejandro on 15/03/15.
 */
public class BackgroundClock {

    private float alpha;
    private Color color;
    private int max;
    private int increment;

    public BackgroundClock(int max, int increment) {
        color = ColorManager.ColorClock;
        this.max = max;
        this.increment = increment;
    }

    public void draw(ShapeRenderer shapeR){
        Color oldColor = shapeR.getColor();
        shapeR.setColor(color);
        shapeR.begin(ShapeRenderer.ShapeType.Filled);
            shapeR.arc(ProjectSquare.WIDTH/2, ProjectSquare.HEIGTH/2, ProjectSquare.HEIGTH, 90, (float)Math.toDegrees(alpha));

        shapeR.end();
        shapeR.setColor(oldColor);
    }

    public void upade(int timer, float seconCounter) {
        if (timer > max)
            max += increment;
        else {
            float slice = (2 * 3.1415f) / max;
            alpha = (30 - (timer - seconCounter)) * slice;
        }
    }
}
