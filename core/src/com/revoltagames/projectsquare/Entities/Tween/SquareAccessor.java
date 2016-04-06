package com.revoltagames.projectsquare.Entities.Tween;

import com.badlogic.gdx.graphics.Color;
import com.revoltagames.projectsquare.Entities.Shapes.Square;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * Created by alejandro on 4/03/15.
 */
public class SquareAccessor implements TweenAccessor<Square>{

    /**
     * private TweenManager tweenManager;
     * tweenManager = new TweenManager();
     * en update
     * tweenManager.update(delta);
     * al crear
     * tween.registerAccessor(Square.class, new SquareAccessor());
     *
     * Animacion
     * Tween.set(cuadradoAanimar, type).target(inicial).start(tweenManager);
     * Tween.to(cuadradoAanimar, type, duration).target(final).start(tweenManager);
     */

    public static final int POS_AND_SIZE = 0;
    public static final int ALPHA = 1;
    public static final int COLOR = 2;

    @Override
    public int getValues(Square target, int tweenType, float[] returnValues) {
        switch (tweenType) {
            case POS_AND_SIZE:
                returnValues[0] = target.getX();
                returnValues[1] = target.getY();
                returnValues[2] = target.getSize();
                return 3;
            case ALPHA:
                returnValues[0] = target.getAlpha();
                return 1;
            case COLOR:
                returnValues[0] = target.getColor().r;
                returnValues[1] = target.getColor().g;
                returnValues[2] = target.getColor().b;
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(Square target, int tweenType, float[] newValues) {
        switch (tweenType) {
            case POS_AND_SIZE:
                target.setX(newValues[0]);
                target.setY(newValues[1]);
                target.setSize(newValues[2]);
                break;
            case ALPHA:
                target.setAlpha(newValues[0]);
                break;
            case COLOR:
                Color newColor = new Color(newValues[0], newValues[1], newValues[2],1);
                target.setColor(newColor);
            default:
                assert false;
        }
    }
}
