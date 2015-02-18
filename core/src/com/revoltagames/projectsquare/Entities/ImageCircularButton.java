package com.revoltagames.projectsquare.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.Dimension;
import java.awt.Image;

/**
 * Created by caenrique93 on 18/02/15.
 */
public class ImageCircularButton extends CircularButton {
    Texture image;
    Dimension imgSize;
    SpriteBatch spriteRendenrer;

    /**
     * Crea un boton con una imagen y un radio justo para que quepa la imagen
     * @param y
     * @param x
     * @param image
     */
    public ImageCircularButton(int x, int y, Image image) {
        super(x, y, 0, Color.WHITE);
        spriteRendenrer = new SpriteBatch();
        imgSize = image.getGraphics().getClipBounds().getSize();
        this.radio = (int) Math.sqrt(Math.pow(2 * imgSize.height, 2) / 2);
    }

    /**
     * crea un boton con una imagen y el tamaño especificado
     * @param y
     * @param x
     * @param radio
     * @param color Color de fondo
     * @param image
     */
    public ImageCircularButton(int x, int y, int radio, Color color, Texture image) {
        super(x, y, radio, color);
        this.image = image;
    }

    @Override
    public void draw(ShapeRenderer shapeR) {
        super.draw(shapeR);
        spriteRendenrer.begin();
        spriteRendenrer.draw(image, (float) imgSize.getHeight(), (float) imgSize.getHeight());
        spriteRendenrer.end();
    }


}
