package com.revoltagames.projectsquare.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Clase que implementa el reloj de la pantalla de juego
 */
public class Clock {

    private float alpha;

    private Color color;

    private int max;

    private int anterior;

    /**
     * Constructor
     * @param max Tiempo máximo del reloj
     */
    public Clock(int max) {
        color = ColorManager.ColorClock;
        this.max = max;
        alpha = 1;
        anterior = max;
    }

    /**
     * Método que renderiza el reloj en pantalla
     * @param shapeR ShapeRenderer que se utilizará para dibujar
     */
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

    /**
     * Método de actualización por frame del reloj
     * @param timer Tiempo desde el último frame
     * @param secondCounter Tiempo transcurrido del segundo actual
     */
    public void update(int timer, float secondCounter) {
        if (timer > max)
            max = timer;
        else if (anterior < timer)
            max = timer;

        alpha = (timer - secondCounter) / (float)max ;
        anterior = timer;
    }
}
