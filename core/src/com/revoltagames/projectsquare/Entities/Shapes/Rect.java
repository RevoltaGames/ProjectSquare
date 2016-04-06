package com.revoltagames.projectsquare.Entities.Shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Implementación de la interfaz Shape, con forma de Rectangulo
 */
public class Rect implements Shape {
    private float x, y, w, h;
    private Color color;
    private float alpha;

    private int swipe;


    /**
     * Crea un rectangulo en las coordenadas x e y, de tamaño w x h y de color aleatorio
     * @param x coordenada horizontal
     * @param y coordenada vertical
     * @param w ancho
     * @param h alto
     */
    public Rect(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        color = ColorManager.randomColor(4);
        alpha = 1.0f;
    }

    /**
     * Dibuja el rectangulo usando las coordenadas x e y como centro de la figura
     * @param shapeR renderer para dibujar la figura
     */
    public void draw(ShapeRenderer shapeR) {
        shapeR.begin(ShapeRenderer.ShapeType.Filled);
        Color oldColor = shapeR.getColor();
        shapeR.setColor(color);
        shapeR.rect(x - w / 2, y - h / 2, w, h);
        shapeR.setColor(oldColor);
        shapeR.end();
    }

    /**
     * Dadas unas coordenadas tx y ty, devuelve true si dichas coordenadas
     * están dentro del rectangulo y false en caso contrario
     * @param tx coordenada horizontal del punto
     * @param ty coordenada vertical del punto
     * @return true si el punto está dentro del cuadrado o false en caso contrario
     */
    @Override
    public boolean touched(float tx, float ty) {
        ty = ProjectSquare.HEIGTH - ty;
        return tx>=x-w/2 && tx<=x+w/2 && ty>=y-h/2 && ty<=y+h/2;
    }

    /**
     * @return Devuelve un Rectangle que contiene a la figura.
     */
    @Override
    public Rectangle getBoundaries() {
        return new Rectangle(this.x, this.y, this.w, this.h);
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
     * @return Devuelve la coordenada y del rectangulo
     */
    public float getY() {
        return y;
    }

    /**
     * @return Devuelve un nuevo rectangulo con los mismos parámentros que el actual
     */
    @Override
    public Shape clone() {
        return new Rect(this.x, this.y, this.w, this.h);
    }

    /**
     * Establece la coordenada y del rectangulo. Las coordenadas se miden desde el centro de la figura
     * @param y coordenada vertical
     */
    public void setY(float y) {
        this.y = y;
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
     * @return color del rectangulo
     */
    public Color getColor() {
        return color;
    }

    /**
     * Establece el color del rectangulo
     * @param color
     */
    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Establece el valor alpha en el color del rectangulo
     * @param alpha número entre 0 y 1
     */
    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    /**
     * @return el valor alpha del color del rectangulo
     */
    public float getAlpha() {
        return alpha;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }
}

