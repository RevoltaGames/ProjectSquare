package com.revoltagames.projectsquare.Managers;

/**
 * Created by alejandro on 17/02/15.
 */
public class GestureManager {

    public static final int SW_RIGHT = 1;
    public static final int SW_LEFT = 2;
    public static final int SW_UP = 3;
    public static final int SW_DOWN = 4;

    private static int swipe = 0;

    /**
     * Guarda el deslizamiento realizado por el usuario
     * @param dir dirección del deslizamiento.
     */
    public static void setSwipe(int dir) {
        swipe = dir;
    }

    /**
     * Devuelve el deslizamiento realizado por el usuario o 0 si no ha realizado ninguno desde
     * la ultima llamada
     * @return el deslizamiento.
     */
    public static int getSwipe() {
        int oldswipe = swipe;
        swipe = 0;
        return oldswipe;
    }

    /**
     * Borra (si existe) el deslizamiento almacenado
     */
    public static void clear() {
        swipe = 0;
    }
}
