package com.revoltagames.projectsquare.Entities;

public class Score implements Comparable<Score>{
    public int puntuacion;
    public String nombre;
    public Score(int p, String n) {
        this.puntuacion = p;
        this.nombre = n;
    }

    @Override
    public int compareTo(Score score) {
        return this.puntuacion - score.puntuacion;
    }

    @Override
    public String toString() {
        return this.puntuacion + ":" + this.nombre;
    }
}
