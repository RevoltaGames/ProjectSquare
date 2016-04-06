package com.revoltagames.projectsquare.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.revoltagames.projectsquare.Entities.Shapes.Shape;
import com.revoltagames.projectsquare.Entities.Shapes.Square;
import com.revoltagames.projectsquare.Entities.Tween.SquareAccessor;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.ProjectSquare;

import aurelienribon.tweenengine.Tween;

/**
 * Created by alejandro on 6/04/16.
 */
public class Switch {

    static {
        Tween.registerAccessor(Square.class, new SquareAccessor());
    }

    private float y;

    private float x;

    private float width;

    private float height;

    private Color backColor = ColorManager.GREY;
    private Color onColor = ColorManager.BLUE;
    private Color offColor = ColorManager.MID_GREY;

    private Square toggle;

    private boolean isOn;

    public Switch(float x, float y, boolean isOn) {
        this.x = x;
        this.y = y;
        this.width = ProjectSquare.WIDTH / 8;
        this.height = width / 2;
        this.isOn = isOn;
        if (isOn) {
            toggle = new Square(x + height/2, y, height);
            toggle.setColor(onColor);
        } else {
            toggle = new Square(x - height/2, y, height);
            toggle.setColor(offColor);
        }
    }

    public void draw(ShapeRenderer renderer) {
        Color oldColor = renderer.getColor();
        renderer.setColor(backColor);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.rect(x - width / 2, y - height / 2, width, height);

        renderer.setColor(oldColor);
        renderer.end();

        toggle.draw(renderer);
    }

    public void toggle() {
        if (isOn){
            Tween.to(toggle, SquareAccessor.POS_AND_SIZE, 0.2f)
                    .target(x-height/2,y,height)
                    .start(ProjectSquare.tweenManager);
            Tween.to(toggle, SquareAccessor.COLOR, 0.2f)
                    .target(offColor.r, offColor.g, offColor.b)
                    .start(ProjectSquare.tweenManager);
        }
        else {
            Tween.to(toggle, SquareAccessor.POS_AND_SIZE, 0.2f)
                    .target(x+height/2,y,height)
                    .start(ProjectSquare.tweenManager);
            Tween.to(toggle, SquareAccessor.COLOR, 0.2f)
                    .target(onColor.r, onColor.g, onColor.b)
                    .start(ProjectSquare.tweenManager);
        }

        isOn = !isOn;

    }

    public boolean touched(float tx, float ty) {
        ty = ProjectSquare.HEIGTH - ty;
        return tx>x-width/2 && tx<=x+width/2 && ty>y-height/2 && ty <= y+height/2;
    }

    public Rectangle getBoundaries() {
        return new Rectangle(x, y, width, height);
    }
}
