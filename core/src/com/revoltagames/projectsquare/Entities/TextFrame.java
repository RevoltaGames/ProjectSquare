package com.revoltagames.projectsquare.Entities;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Entities.Shapes.Square;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Created by caenrique93 on 4/5/16.
 */
public class TextFrame extends Square {
    private String text;
    private BitmapFont font;

    /**
     * Crea un cuadrado en las coordenadas x e y, de tamaño size y de color aleatorio
     *
     * @param x    coordenada horizontal
     * @param y    coordenada vertical
     * @param size tamaño
     */
    public TextFrame(float x, float y, float size, String text) {
        super(x, y, size);
        super.setColor(ColorManager.ColorClockBack);
        this.text = text;
        font = ProjectSquare.resManager.getFont(3);

    }

    /**
     * Establece el texto dentro del TextFrame
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Dibuja el TextFrame. Este método llama al método draw() de la clase Square, y además
     * dibuja el texto encima
     * @param shapeR
     * @param sb
     */
    public void draw(ShapeRenderer shapeR, SpriteBatch sb) {
        super.draw(shapeR);

        BitmapFont.TextBounds bounds = font.getBounds(text);
        float x = this.getX() - bounds.width/2;
        float y = this.getY() + bounds.height/2;
        sb.begin();
        font.draw(sb, text, x, y);
        sb.end();
    }
}
