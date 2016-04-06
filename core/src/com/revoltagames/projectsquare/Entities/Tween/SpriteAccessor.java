package com.revoltagames.projectsquare.Entities.Tween;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * Clase que permite a TweenEngine acceder a parámetros de un Sprite para poder realizar
 * animaciones interpoladas.
 */
public class SpriteAccessor implements TweenAccessor<Sprite> {

    /**
     * Tipo de interpolación que modifica el Alpha (transparencia) de un Sprite.
     */
    public static final int ALPHA = 0;

    @Override
    public int getValues(Sprite target, int tweenType, float[] returnValues) {
        switch (tweenType) {
            case ALPHA:
                returnValues[0] = target.getColor().a;
                return 1;
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(Sprite target, int tweenType, float[] newValues) {
        switch (tweenType){
            case ALPHA:
                Color c = target.getColor();
                target.setColor(c.r,c.g,c.b, newValues[0]);
                break;
            default:
                assert false;
        }
    }
}
