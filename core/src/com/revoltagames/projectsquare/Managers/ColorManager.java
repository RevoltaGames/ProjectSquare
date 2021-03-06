package com.revoltagames.projectsquare.Managers;

import com.badlogic.gdx.graphics.Color;

import java.util.Random;

/**
 * Colores utilizados en el juego
 */
public class ColorManager {

    private static Random rnd = new Random(System.currentTimeMillis());

    public static final Color RED;
    public static Color PINK;
    public static Color PURPLE;
    public static Color DEEP_PURPLE;
    public static Color INDIGO;
    public static Color BLUE;
    public static Color LIGHT_BLUE;
    public static Color CYAN;
    public static Color TEAL;
    public static Color GREEN;
    public static Color LIGHT_GREEN;
    public static Color LIME;
    public static Color YELLOW;
    public static Color AMBER;
    public static Color ORANGE;
    public static Color DEEP_ORANGE;
    public static Color BROWN;
    public static Color GREY;
    public static Color BLUE_GREY;
    public static Color BLACK;
    public static Color WHITE;
    public static Color NRED;
    public static Color NBLUE;
    public static Color NYELLOW;
    public static Color NGREEN;
    public static Color MID_GREY;

    private static Color[] colores = new Color[26];
    //public static Color ColorFondo = new MyColor(227,224,176,1);
    public static Color ColorFondo = new MyColor(212,212,212,1);
    public static Color ColorClock = new MyColor(200,200,200,1);
    public static Color ColorClockBack = new MyColor(230,230,230,1);

    static {
        RED = colores[0] = new MyColor(244, 67, 54, 1);
        BLUE = colores[1] = new MyColor(33, 150, 243, 1);
        YELLOW = colores[2] = new MyColor(255, 235, 59, 1);
        GREEN = colores[3] = new MyColor(76, 175, 80, 1);
        PINK = colores[4] = new MyColor(233, 30, 99, 1);
        PURPLE = colores[5] = new MyColor(156, 39, 176, 1);
        DEEP_PURPLE = colores[6] = new MyColor(103, 58, 183, 1);
        INDIGO = colores[7] = new MyColor(63, 81, 181, 1);
        LIGHT_BLUE = colores[8] = new MyColor(3, 169, 244, 1);
        CYAN = colores[9] = new MyColor(0, 188, 212, 1);
        TEAL = colores[10] = new MyColor(0, 150, 136, 1);
        LIME = colores[11] = new MyColor(205, 220, 57, 1);
        LIGHT_GREEN = colores[12] = new MyColor(139, 195, 74, 1);
        AMBER = colores[13] = new MyColor(255, 193, 7, 1);
        ORANGE = colores[14] = new MyColor(255, 152, 0, 1);
        DEEP_ORANGE = colores[15] = new MyColor(255, 87, 34, 1);
        BROWN = colores[16] = new MyColor(121, 85, 72, 1);
        GREY = colores[17] = new MyColor(158, 158, 158, 1);
        BLUE_GREY = colores[18] = new MyColor(96, 125, 139, 1);
        BLACK = colores[19] = new MyColor(0, 0, 0, 1);
        WHITE = colores[20] = new MyColor(255, 255, 255, 255);
        NRED = colores[21] = new MyColor(225, 91, 100, 1);
        NBLUE= colores[22] = new MyColor(68, 91, 134, 1);
        NYELLOW = colores[23] = new MyColor(251, 179, 107, 1);
        NGREEN = colores[24] = new MyColor(171, 188, 133, 1);
        MID_GREY = colores[25] = new MyColor(102, 102, 102, 1);
    }

    /**
     * Devuelve un color aleatorio de todos los posibles
     * @return Color aleatorio
     */
    public static Color randomColor() {
        return colores[rnd.nextInt(22)];
    }

    /**
     * Devuelve un color aleatorio entre los n primeros
     * @param n número de colores posibles
     * @return
     */
    public static Color randomColor(int n) {
        return colores[rnd.nextInt(n)+21];
    }

    /**
     * Clase auxiliar para poder escribir los colores en rgb 0-255
     */
    private static class MyColor extends Color {
        public MyColor(int r, int g, int b, float s) {
            super(r/255f, g/255f, b/255f, s);
        }
    }
}


