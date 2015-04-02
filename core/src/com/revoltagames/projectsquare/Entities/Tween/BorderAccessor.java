package com.revoltagames.projectsquare.Entities.Tween;

import com.badlogic.gdx.utils.StreamUtils;
import com.revoltagames.projectsquare.Entities.Border;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * Created by Truji on 02/04/2015.
 */
public class BorderAccessor implements TweenAccessor<Border> {

    public static final int POS_AND_SIZE = 0;
    @Override
    public int getValues(Border target, int tweenType, float[] returnValues) {
        switch (tweenType){
            case POS_AND_SIZE:
                float[] vertices = target.getVertices();
                for (int i = 0; i < vertices.length; i++)
                    returnValues[i] = vertices[i];
                return vertices.length;
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(Border target, int tweenType, float[] newValues) {
        switch (tweenType) {
            case POS_AND_SIZE:
                float[] vertices = target.getVertices();
                for (int i = 0; i < vertices.length; i++)
                    vertices[i] = newValues[i];
                break;
            default:
                assert false;
        }
    }
}
