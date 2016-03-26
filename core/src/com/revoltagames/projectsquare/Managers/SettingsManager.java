package com.revoltagames.projectsquare.Managers;

import com.badlogic.gdx.Gdx;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by caenrique93 on 3/26/16.
 */
public class SettingsManager {
    /*
    public int dificulty;
    public boolean godMode;
    public boolean etc;
    */

    private List<Integer> scores = new ArrayList<Integer>();


    public static final String settingsPath = "settings.json";

    public void setNewScore(int score) {
        scores.add(score);
        scores.sort(Comparator.<Integer>reverseOrder());
        scores = scores.subList(0,5);
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void save() {
        try (

            OutputStream file = Gdx.files.local(SettingsManager.settingsPath).write(false);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
        ){
            output.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SettingsManager load() {
        SettingsManager sm = null;
        try (
            InputStream file = Gdx.files.local(SettingsManager.settingsPath).read();
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
        ){
            sm = (SettingsManager) input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return sm;
    }
}
