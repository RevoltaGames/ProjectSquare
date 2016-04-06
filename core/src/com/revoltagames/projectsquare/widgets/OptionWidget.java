package com.revoltagames.projectsquare.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.revoltagames.projectsquare.Entities.Switch;
import com.revoltagames.projectsquare.Entities.TextFrame;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Created by alejandro on 6/04/16.
 */
public class OptionWidget {

    private Switch toggle;

    private float y;

    private float x;

    private float width;

    private float height;

    private Color backColor = ColorManager.ColorClock;

    private TextFrame textBox;

    public OptionWidget(float x, float y, boolean isOn, String text) {
        this.x = x;
        this.y = y;
        this.width = ProjectSquare.WIDTH * 8 / 10;
        this.height = ProjectSquare.HEIGTH / 16;
        textBox = new TextFrame(x, y, width, height, text, backColor);
        textBox.setTextCentered(false);
        toggle = new Switch(x + width/2 - width / 10, y, isOn);
    }

    public void draw(ShapeRenderer renderer, SpriteBatch spriteRenderer) {
        textBox.draw(renderer, spriteRenderer);
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
