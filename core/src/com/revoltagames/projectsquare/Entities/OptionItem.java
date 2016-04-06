package com.revoltagames.projectsquare.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Created by alejandro on 6/04/16.
 */
public class OptionItem {

    private Switch toggle;

    private float y;

    private float x;

    private float width;

    private float height;

    private Color backColor = ColorManager.RED;

    private TextFrame textBox;

    public OptionItem(float x, float y, boolean isOn, String text) {
        this.x = x;
        this.y = y;
        this.width = ProjectSquare.WIDTH * 8 / 10;
        this.height = ProjectSquare.HEIGTH / 16;
        textBox = new TextFrame(x, y, width, height, text);
        toggle = new Switch(x + width/2 - width / 10, y, isOn);
    }

    public void draw(ShapeRenderer renderer) {
        Color oldColor = renderer.getColor();
        renderer.setColor(backColor);

        textBox.draw(renderer);

        renderer.end();

        toggle.draw(renderer);
    }

    public void toggle() {
        toggle.toggle();
    }

    public boolean touched(float tx, float ty) {
        return toggle.touched(tx, ty);
    }

    public Rectangle getBoundaries() {
        return new Rectangle(x, y, width, height);
    }
}
