package com.revoltagames.projectsquare.Entities.Tween;

import com.revoltagames.projectsquare.Entities.Square;

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
    @Override
    public int getValues(Square target, int tweenType, float[] returnValues) {
        switch (tweenType) {
            case POS_AND_SIZE:
                returnValues[0] = target.getX();
                returnValues[1] = target.getY();
                returnValues[2] = target.getSize();
                return 3;
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
            default:
                assert false;
        }
    }
}
