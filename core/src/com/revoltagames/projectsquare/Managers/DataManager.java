package com.revoltagames.projectsquare.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by caenrique93 on 2/04/15.
 */
public class DataManager {
    /**
     * La estructura de el archivo de datos es la siguiente:
     *      1 2 3 4 5 6
     *      cada numero es un Integer, los 5 primeros corresponden a las puntuaciones máximas en
     *      orden descendente. El 6 Integer es la puntuación acumulada. Se separan por espacios.
     *      Si se desea añadir mas información, se hará por posicion, es decir, dependiendo de la
     *      posición que ocupe en el archivo significará una cosa u otra.
     */
    private FileHandle file;

    private ArrayList<Integer> highScores;
    private int coins;

    public DataManager() {
        file = Gdx.files.local("database");
        if (!file.exists()) {
            createDataBase();
        }
        loadData();
    }

    private void loadData() {
        highScores = new ArrayList<Integer>();
        InputStream io = file.read();
        for(int i=0; i<5; i++) {
            try {
                highScores.add(io.read());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Problema al cargar las puntuaciones máximas");
            }
        }
        try {
            coins = io.read();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problema al cargar las monedas");
        }
    }

    public void saveData() {
        OutputStream io = file.write(false);
        for(int score : highScores) {
            try {
                io.write(score);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Problema escribiendo las puntuaciones máximas");
            }
        }
        try {
            io.write(coins);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problema escribiendo las monedas");
        }
    }

    private void createDataBase() {
        try {
            file.file().createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problema creando la base de datos");
        }
        OutputStream io = file.write(false);
        for(int i=0; i<6; i++) {
            try {
                io.write(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Integer> getHighScores() {
        return highScores;
    }

    public void saveScore(int score) {
        highScores.set(4, score);
        Collections.sort(highScores);
        Collections.reverse(highScores);
    }

    public void saveCoins(int coins) {
        this.coins = coins;
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int score) {
        coins += score;
    }
}
