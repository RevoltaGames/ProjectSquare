package com.revoltagames.projectsquare.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Entities.Shapes.Rect;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Created by caenrique93 on 4/5/16.
 */
public class TextFrame extends Rect {
    private String text;
    private BitmapFont font;

    private boolean textCentered = true;

    /**
     * Crea un cuadrado en las coordenadas x e y, de tamaño size
     *
     * @param x    coordenada horizontal
     * @param y    coordenada vertical
     * @param size tamaño
     */
    public TextFrame(float x, float y, float size, String text) {
        super(x, y, size, size);
        super.setColor(ColorManager.ColorClockBack);
        this.text = text;
        font = ProjectSquare.resManager.getFont("greySmallFont");

    }

    /**
     * Crea un rectangulo en las coordenadas x e y, de tamaño w x h
     *
     * @param x    coordenada horizontal
     * @param y    coordenada vertical
     */
    public TextFrame(float x, float y, float w, float h, String text) {
        super(x, y, w, h);
        super.setColor(ColorManager.ColorClockBack);
        this.text = text;
        font = ProjectSquare.resManager.getFont("greySmallFont.ttf");
    }

    /**
     * Crea un rectangulo del color indicado
     * @param x coordenada x
     * @param y coordenada y
     * @param w ancho
     * @param h alto
     * @param text texto
     * @param color colorFondo
     */
    public TextFrame(float x, float y, float w, float h, String text, Color color) {
        super(x, y, w, h);
        super.setColor(color);
        this.text = text;
        font = ProjectSquare.resManager.getFont("greySmallFont.ttf");
    }

    public void setFont(BitmapFont font) {
        this.font = font;
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
        if (textCentered)
            font.draw(sb, text, x, y);
        else
            font.draw(sb, text, x - getW()/2 + bounds.width/2 + getW()/20, y);
        sb.end();
    }

    /**
     * Indica si el texto se dibujará centrado (true) o alineado a la izquierda (false)
     * @return true: Centrado, false: Alineado Izquierda
     */
    public boolean isTextCentered() {
        return textCentered;
    }

    /**
     * Establece si el texto se dibujará centrado (true) o alineado a la izquierda (false)
     * @param textCentered true: Centrado, false: Alineado Izquierda
     */
    public void setTextCentered(boolean textCentered) {
        this.textCentered = textCentered;
    }
}
