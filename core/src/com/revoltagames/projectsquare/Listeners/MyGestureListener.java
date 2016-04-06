package com.revoltagames.projectsquare.Listeners;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.revoltagames.projectsquare.Managers.GestureManager;
import com.badlogic.gdx.audio.Music;
import com.revoltagames.projectsquare.Managers.ResourceManager;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Listener reconocedor de gestos táctiles
 */
public class MyGestureListener implements GestureListener {

    private static Music track;
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if(Math.abs(velocityX)>Math.abs(velocityY)){
            if(velocityX>0){
                // Deslizar Derecha
                GestureManager.setSwipe(GestureManager.SW_RIGHT);
            }else if (velocityX<0){
                // Deslizar Izquierda
                GestureManager.setSwipe(GestureManager.SW_LEFT);
            }
        }else{
            if (velocityY>0) {
                // Deslizar abajo
                GestureManager.setSwipe(GestureManager.SW_DOWN);
            } else if (velocityY<0) {
                // Deslizar arriba
                GestureManager.setSwipe(GestureManager.SW_UP);
            }
        }
        return true;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}
