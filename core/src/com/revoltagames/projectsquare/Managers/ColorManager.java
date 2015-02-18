package com.revoltagames.projectsquare.Managers;

import com.badlogic.gdx.graphics.Color;

import java.util.Random;

/**
 * Created by alejandro on 18/02/15.
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

    private static Color[] colores = new Color[21];

    static {
        RED = colores[0] = new MyColor(244, 67, 54, 1);
        PINK = colores[1] = new MyColor(233, 30, 99, 1);
        PURPLE = colores[2] = new MyColor(156, 39, 176, 1);
        DEEP_PURPLE = colores[3] = new MyColor(103, 58, 183, 1);
        INDIGO = colores[4] = new MyColor(63, 81, 181, 1);
        BLUE = colores[5] = new MyColor(33, 150, 243, 1);
        LIGHT_BLUE = colores[6] = new MyColor(3, 169, 244, 1);
        CYAN = colores[7] = new MyColor(0, 188, 212, 1);
        TEAL = colores[8] = new MyColor(0, 150, 136, 1);
        GREEN = colores[9] = new MyColor(76, 175, 80, 1);
        LIGHT_GREEN = colores[10] = new MyColor(139, 195, 74, 1);
        LIME = colores[11] = new MyColor(205, 220, 57, 1);
        YELLOW = colores[12] = new MyColor(255, 235, 59, 1);
        AMBER = colores[13] = new MyColor(255, 193, 7, 1);
        ORANGE = colores[14] = new MyColor(255, 152, 0, 1);
        DEEP_ORANGE = colores[15] = new MyColor(255, 87, 34, 1);
        BROWN = colores[16] = new MyColor(121, 85, 72, 1);
        GREY = colores[17] = new MyColor(158, 158, 158, 1);
        BLUE_GREY = colores[18] = new MyColor(96, 125, 139, 1);
        BLACK = colores[19] = new MyColor(0, 0, 0, 1);
        WHITE = colores[20] = new MyColor(1, 1, 1, 1);
    }

    public static Color randomColor() {
        int sdfasdf = rnd.nextInt(21);
        System.out.println(sdfasdf);
        return colores[sdfasdf];
    }

    private static class MyColor extends Color {
        public MyColor(int r, int g, int b, float s) {
            super(r/255f, g/255f, b/255f, s);
        }
    }
}


