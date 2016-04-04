package com.revoltagames.projectsquare.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by caenrique93 on 3/26/16.
 */
public class SettingsManager {

    public int dificulty = 0;
    public boolean godMode = false;
    private List<Integer> scores = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0));

    public SettingsManager() {
        this.load();
    }

    private String listToString(List<Integer> list) {
        String lista = "";
        for (int item: list) {
            lista += item + " ";
        }
        return lista;
    }

    public void setNewScore(int score) {
        scores.add(score);
        Collections.sort(scores);
        scores = scores.subList(1,scores.size());
        Collections.reverse(scores);
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void save() {
        Preferences projectSquare = Gdx.app.getPreferences("projectSquare");
        projectSquare.putString("scores", listToString(this.scores));
        projectSquare.putBoolean("godMode", this.godMode);
        projectSquare.putInteger("dificulty", this.dificulty);

        projectSquare.flush();
        System.out.println("guardando ajustes");
    }

    public void load() {
        Preferences pref = Gdx.app.getPreferences("projectSquare");
        if(pref.contains("godMode")) this.godMode = pref.getBoolean("godMode");
        if(pref.contains("scores")) this.scores = toList(pref.getString("scores"));
        if(pref.contains("dificulty")) this.dificulty = pref.getInteger("dificulty");
    }

    private List<Integer> toList(String scores) {
        List<Integer> intList = new ArrayList<Integer>();
        for(String s: scores.split(" ")) intList.add(Integer.valueOf(s));
        return intList;
    }
}
