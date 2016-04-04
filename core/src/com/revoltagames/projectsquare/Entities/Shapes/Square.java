package com.revoltagames.projectsquare.Entities.Shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Implementación de la interfaz Shape, con forma de cuadrado
 */
public class Square implements Shape {
    private float x, y, size;
    private Color color;
    private float alpha;

    private int swipe;


    /**
     * Crea un cuadrado en las coordenadas x e y, de tamaño size y de color aleatorio
     * @param x coordenada horizontal
     * @param y coordenada vertical
     * @param size tamaño
     */
    public Square(float x, float y, float size) {
        this.x = x;
        this.y = y;
        this.size = size;
        color = ColorManager.randomColor(4);
        alpha = 1.0f;
    }

    /**
     * Dibuja el cuadrado usando las coordenadas x e y como centro de la figura
     * @param shapeR renderer para dibujar la figura
     */
    public void draw(ShapeRenderer shapeR) {
        shapeR.begin(ShapeRenderer.ShapeType.Filled);
        Color oldColor = shapeR.getColor();
        shapeR.setColor(color.r, color.g, color.b, alpha);
        shapeR.rect(x - size / 2, y - size / 2, size, size);
        shapeR.setColor(oldColor);
        shapeR.end();
    }

    /**
     * Dadas unas coordenadas tx y ty, devuelve true si dichas coordenadas
     * están dentro del cuadrado y false en caso contrario
     * @param tx coordenada horizontal del punto
     * @param ty coordenada vertical del punto
     * @return true si el punto está dentro del cuadrado o false en caso contrario
     */
    @Override
    public boolean touched(float tx, float ty) {
        ty = ProjectSquare.HEIGTH - ty;
        return tx>=x-size/2 && tx<=x+size/2 && ty>=y-size/2 && ty<=y+size/2;
    }

    /**
     * @return Devuelve un Rectangle que contiene a la figura.
     */
    @Override
    public Rectangle getBoundaries() {
        return new Rectangle(this.x, this.y, this.size, this.size);
    }

    /**
     * Las coordenadas se miden desde el centro de la figura
     * @return Devuelve la coordenada x del cuadrado
     */
    public float getX() {
        return x;
    }

    /**
     * Establece la coordenada x del cuadrado. Las coordenadas se miden desde el centro de la figura
     * @param x coordenada horizontal
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Las coordenadas se miden desde el centro de la figura
     * @return Devuelve la coordenada y del cuadrado
     */
    public float getY() {
        return y;
    }

    /**
     * @return Devuelve un nuevo cuadrado con los mismos parámentros que el actual
     */
    @Override
    public Shape clone() {
        return new Square(this.x, this.y, this.size);
    }

    /**
     * Establece la coordenada y del cuadrado. Las coordenadas se miden desde el centro de la figura
     * @param y coordenada vertical
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * @return El tamaño del cuadrado
     */
    public float getSize() {
        return size;
    }

    /**
     * Establece el tamaño del cuadrado
     * @param size
     */
    public void setSize(float size) {
        this.size = size;
    }

    public int getSwipe() {
        return swipe;
    }

    public void setSwipe(int swipe) {
        this.swipe = swipe;
    }

    public boolean atBorder() {
        int w = ProjectSquare.WIDTH;
        int h = ProjectSquare.HEIGTH;
        return x >= w || x <= 0 || y <= 0|| y>= h;
    }

    /**
     * @return color del cuadrado
     */
    public Color getColor() {
        return color;
    }

    /**
     * Establece el color del cuadrado
     * @param color
     */
    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Establece el valor alpha en el color del cuadrado
     * @param alpha número entre 0 y 1
     */
    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    /**
     * @return el valor alpha del color del cuadrado
     */
    public float getAlpha() {
        return alpha;
    }
}

