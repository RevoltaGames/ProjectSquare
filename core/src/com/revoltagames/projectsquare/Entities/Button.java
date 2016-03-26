package com.revoltagames.projectsquare.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Entities.Shapes.Shape;
import com.revoltagames.projectsquare.Entities.Shapes.Square;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Implementa un botón con posibilidad de usar cualquier forma geométrica y una imagen, texto, o ambas cosas
 */
public class Button {

    /**
     * el Shape por defecto es un Square en el centro de la pantalla
     */
    private static Shape defaultShape = new Square(
            ProjectSquare.WIDTH/2,
            ProjectSquare.HEIGTH/2,
            ProjectSquare.WIDTH/5
    );

    private Shape shape;
    private String text;
    private Texture image;
    private SpriteBatch spRenderer;
    private BitmapFont font;

    /**
     * utiliza el Shape por defecto
     */
    public Button() {
        this(Button.defaultShape.clone());
    }

    /**
     * Utiliza el Shape por defecto, pero cambiando la posición a x e y. Las coordenadas establecen
     * la posición del centro del Shape
     * @param x coordenada horizontal
     * @param y coordenada vertical
     */
    public Button(float x, float y) {
        this();
        this.shape.setX(x);
        this.shape.setY(y);
    }

    /**
     * nuevo botón con el Shape especificado
     * @param shape la forma que utilizará el botón
     */
    public Button(Shape shape) {
        this(shape, null, null, null, null);
    }

    /**
     * nuevo botón usando el Shape especificado e incluyendo texto
     * @param shape la forma que utilizará el botón
     * @param text el texto que incluirá el botón
     * @param font la tipografía a utilizar
     */
    public Button(Shape shape, String text, BitmapFont font) {
        this(shape, text, null, new SpriteBatch(), font);
    }

    /**
     * nuevo botón usando el Shape especificado e incluyendo una imagen
     * @param shape la forma que utilizará el botón
     * @param image la imagen que tendrá. Es conveniente que sea cuadrada
     */
    public Button(Shape shape, Texture image) {
        this(shape, null, image, new SpriteBatch(), null);
    }

    /**
     * Constructor con todos los parámetros posibles
     * @param shape la forma que utilizará el botón
     * @param text el texto que incluirá el botón
     * @param image la imagen que tendrá. Es conveniente que sea cuadrada
     * @param spRenderer el renderer para la imagen
     * @param font la tipografía a utilizar
     */
    public Button(Shape shape, String text, Texture image, SpriteBatch spRenderer, BitmapFont font) {
        this.shape = shape;
        this.text = text;
        this.image = image;
        this.spRenderer = spRenderer;
        this.font = font;
    }

    /**
     * Cambia el Shape por defecto para siguiente botones
     * @param shape la forma que utilizará el botón
     */
    public static void setDefaultShape(Shape shape) {
        Button.defaultShape = shape;
    }

    /**
     * Establece el color del botón
     * @param color
     */
    public void setColor(Color color) {
        this.shape.setColor(color);
    }

    /**
     * Pinta el botón usando un ShapeRenderer
     * @param renderer
     */
    public void draw(ShapeRenderer renderer) {
        if(image == null) {
            this.shape.draw(renderer);
        }

        if(spRenderer != null) {
            spRenderer.setProjectionMatrix(renderer.getProjectionMatrix());
            spRenderer.begin();
            if(text != null && font != null) {
                BitmapFont.TextBounds tBounds = font.getBounds(text);
                float width = shape.getBoundaries().getWidth();
                float height = shape.getBoundaries().getHeight();
                if(tBounds.width <= width && tBounds.height <= height) {
                    font.draw(
                            spRenderer,
                            text,
                            (width - tBounds.width) / 2,
                            (height - tBounds.height) / 2
                    );
                }
            }
            if(image != null) {
                spRenderer.draw(
                        image,
                        shape.getBoundaries().getX() - shape.getBoundaries().getWidth()/2,
                        shape.getBoundaries().getY() - shape.getBoundaries().getHeight()/2,
                        shape.getBoundaries().getWidth(),
                        shape.getBoundaries().getHeight()
                );
            }
            spRenderer.end();
        }
    }

    /**
     * Dadas unas coordenadas tx y ty, devuelve true si dichas coordenadas
     * están dentro del Shape del botón y false en caso contrario
     * @param tx
     * @param ty
     * @return
     */
    public boolean touched(float tx, float ty) {
        return shape.touched(tx, ty);
    }

    /**
     * Cambia la imagen que utiliza el botón
     * @param img
     */
    public void setImage(Texture img) {
        this.image = img;
    }
}
