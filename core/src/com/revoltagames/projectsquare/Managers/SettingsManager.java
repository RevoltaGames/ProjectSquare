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
import java.util.Arrays;
import java.util.Collections;
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

    private List<Integer> scores = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0));


    public static final String settingsPath = "settings.json";

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
        OutputStream file = null;
        OutputStream buffer = null;
        ObjectOutput output = null;
        try {
            file = Gdx.files.local(SettingsManager.settingsPath).write(false);
            buffer = new BufferedOutputStream(file);
            output = new ObjectOutputStream(buffer);

            output.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SettingsManager load() {
        SettingsManager sm = null;
        InputStream file = null;
        InputStream buffer = null;
        ObjectInput input = null;
        try {
            file = Gdx.files.local(SettingsManager.settingsPath).read();
            buffer = new BufferedInputStream(file);
            input = new ObjectInputStream(buffer);

            sm = (SettingsManager) input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return sm;
    }
}
