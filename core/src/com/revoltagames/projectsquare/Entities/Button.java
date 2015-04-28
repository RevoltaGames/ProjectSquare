package com.revoltagames.projectsquare.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Entities.Shapes.Circle;
import com.revoltagames.projectsquare.Entities.Shapes.Shape;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Created by caenrique93 on 15/03/15.
 */
public class Button {
    private static Shape defaultShape = new Circle(
            ProjectSquare.WIDTH/2,
            ProjectSquare.HEIGTH/2,
            ProjectSquare.WIDTH/5,
            ColorManager.LIGHT_BLUE
    );

    private Shape shape;
    private String text;
    private Texture image;
    private SpriteBatch spRenderer;
    private BitmapFont font;

    public Button() {
        this(Button.defaultShape.clone());
    }

    public Button(float x, float y) {
        this();
        this.shape.setX(x);
        this.shape.setY(y);
    }

    public Button(Shape shape) {
        this(shape, null, null, null, null);
    }

    public Button(Shape shape, String text, BitmapFont font) {
        this(shape, text, null, new SpriteBatch(), font);
    }

    public Button(Shape shape, Texture image) {
        this(shape, null, image, new SpriteBatch(), null);
    }

    public Button(Shape shape, String text, Texture image, SpriteBatch spRenderer, BitmapFont font) {
        this.shape = shape;
        this.text = text;
        this.image = image;
        this.spRenderer = spRenderer;
        this.font = font;
    }

    public static void setDefaultShape(Shape shape) {
        Button.defaultShape = shape;
    }

    public void setColor(Color color) {
        this.shape.setColor(color);
    }

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

    public boolean touched(float tx, float ty) {
        return shape.touched(tx, ty);
    }

    public void setImage(Texture img) {
        this.image = img;
    }
}
