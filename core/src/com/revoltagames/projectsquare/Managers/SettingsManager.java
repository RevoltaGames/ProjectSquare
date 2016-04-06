package com.revoltagames.projectsquare.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.revoltagames.projectsquare.Entities.Score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Clase que centraliza los ajustes del juego y los serializa en un formato editable para facilitar el debug.
 * Actualmente dicho formato es xml
 */
public class SettingsManager {

    public int dificulty = 0;
    public boolean godMode = false;
    public boolean time = true;
    public boolean lives = true;
    public boolean sound = true;
    private List<Score> scores = new ArrayList<Score>(Arrays.asList(
            new Score(0, "AAA"),
            new Score(0, "AAA"),
            new Score(0, "AAA"),
            new Score(0, "AAA"),
            new Score(0, "AAA")
    ));

    public SettingsManager() {
        this.load();
    }

    /**
     * Añade una nueva puntuación a la lista de puntuaciones máximas y se queda con las 5 mejores
     * @param score Entero que representa la puntuación
     */
    public void setNewScore(int score, String name) {
        scores.add(new Score(score, name));
        Collections.sort(scores);
        scores = scores.subList(1,scores.size());
        Collections.reverse(scores);
    }

    /**
     * @return una lista con las puntuaciónes máximas
     */
    public List<Score> getScores() {
        return scores;
    }

    /**
     * Guarda los ajustes en un archivo xml. Si está funcionando en Linux o OSX se guarda en ~/.prefs/projectSquare.xml,
     * en android se utiliza la clase SharedPreferences, y en Windows se guarda en %UserProfile%/.prefs/projectSquare.xml
     * Este método permite que los ajustes se guarden aunque se actualize la aplicación y también permite tener ajustes locales
     * que facilitan el testeo.
     */
    public void save() {
        Preferences projectSquare = Gdx.app.getPreferences("projectSquare");
        projectSquare.putString("scores", listToString(this.scores));
        projectSquare.putBoolean("godMode", this.godMode);
        projectSquare.putBoolean("lives", this.lives);
        projectSquare.putBoolean("time", this.time);
        projectSquare.putBoolean("sound", this.sound);
        projectSquare.putInteger("dificulty", this.dificulty);

        projectSquare.flush();
        System.out.println("guardando ajustes");
    }

    /**
     * Carga los ajustes desde un archivo de configuración en xml. Este archivo se encuentra en Linux o OSX en ~/.prefs/projectSquare.xml,
     * en Windows en %UserProfile%/.prefs/projectSquare.xml y en android se utiliza la clase SharedPreferences.
     */
    public void load() {
        Preferences pref = Gdx.app.getPreferences("projectSquare");
        if(pref.contains("godMode")) this.godMode = pref.getBoolean("godMode");
        if(pref.contains("lives")) this.lives = pref.getBoolean("lives");
        if(pref.contains("time")) this.time = pref.getBoolean("time");
        if(pref.contains("sound")) this.sound = pref.getBoolean("sound");
        if(pref.contains("scores")) this.scores = toList(pref.getString("scores"));
        if(pref.contains("dificulty")) this.dificulty = pref.getInteger("dificulty");
    }

    /**
     * Utilidad para convertir una lista de enteros en String
     * @param list Lista de enteros
     * @return representación en String de la lista de enteros
     */
    private String listToString(List<Score> list) {
        String lista = "";
        for (Score item: list) {
            lista += item + " ";
        }
        return lista;
    }

    /**
     * Utilidad para parsear un String que contiene la representación de una lista
     * @param scores String representando una lista de enteros
     * @return Lista de enteros
     */
    private List<Score> toList(String scores) {
        List<Score> scoreList = new ArrayList<Score>();
        for(String s: scores.split(" ")) {
            int i = s.indexOf(":");
            Score sc = new Score(Integer.valueOf(s.substring(0,i)), s.substring(i + 1, s.length()));
            scoreList.add(sc);
        }
        return scoreList;
    }
}


