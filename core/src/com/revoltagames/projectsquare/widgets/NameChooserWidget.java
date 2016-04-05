package com.revoltagames.projectsquare.widgets;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Entities.Button;
import com.revoltagames.projectsquare.Entities.Shapes.Square;
import com.revoltagames.projectsquare.Entities.TextFrame;

import java.util.Vector;

/**
 * Widget con seleccionador de nombre letra por letra
 */
public class NameChooserWidget {
    private float size;
    private float x;
    private float y;
    private int numLeters;

    private ShapeRenderer shapeRenderer;
    private SpriteBatch renderer;
    private BitmapFont font;

    private Vector<Character> letras;
    private Vector<TextFrame> frames;
    private Vector<Button> previousButtons;
    private Vector<Button> nextButtons;

    /**
     * Constructor de la clase NameChooserWidget. Las coordenadas son con respecto al centro del widget
     * @param x coordenada horizontal del widget.
     * @param y coordenada vertical del widget.
     * @param size anchura del widget.
     * @param numLeters número de letras que muestra.
     */
    public NameChooserWidget(float x, float y, float size, int numLeters) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.numLeters = numLeters;

        float gap = size / 12;
        float letterSize = size / numLeters - (numLeters-1)*gap/numLeters;
        float letterDistance = letterSize + gap;
        float origin = this.x - size/2 + letterSize/2;


        letras = new Vector<Character>();
        frames = new Vector<TextFrame>();
        nextButtons = new Vector<Button>();
        previousButtons = new Vector<Button>();

        for(int i=0; i< numLeters; i++) {
            letras.add('A');
            frames.add(new TextFrame(origin + letterDistance*i, this.y, letterSize, String.valueOf(letras.get(i))));
            nextButtons.add(new Button(new Square(origin + letterDistance*i, this.y + letterSize, letterSize)));
            previousButtons.add(new Button(new Square(origin + letterDistance*i, this.y - letterSize, letterSize)));
        }
    }

    /**
     * @return Devuelva un String con las letras seleccionadas.
     */
    public String getLeters() {
        String string = "";
        for(char letra: letras) string += letra;
        return string;
    }

    /**
     * Dada una letra, devuelve la siguiente. Tiene en cuenta las letras del rango A-Z en una lista circular,
     * es decir, la siguiente de la Z es la A.
     * @param baseletra letra a partir de la cual calcular la siguiente.
     * @return siguiente letra a baseletra.
     */
    public char nextLetter(char baseletra) {
        if (baseletra == 'Z') return 'A';
        else return (char) (baseletra + 1);
    }

    /**
     * Dada una letra, devuelve la anterior. Tiene en cuenta las letras del rango A-Z en una lista circular,
     * es decir, la anterior de la A es la Z.
     * @param baseletra letra a partir de la cual calcular la anterior.
     * @return anterior letra a baseletra.
     */
    public char previousLetter(char baseletra) {
        if(baseletra == 'A') return 'Z';
        else return (char) (baseletra - 1);
    }

    /**
     * Dibuja el widget utilizando las clases ShapeRenderer y SpriteBatch.
     * @param shapeR
     * @param sb
     */
    public void draw(ShapeRenderer shapeR, SpriteBatch sb) {
        for(int i=0; i<this.numLeters; i++) {
            frames.get(i).draw(shapeR, sb);
            nextButtons.get(i).draw(shapeR);
            previousButtons.get(i).draw(shapeR);
        }
    }

    /**
     * Si alguna parte del widget ha sido tocada actualiza la información al respecto.
     * Las partes que pueden reaccionar son los botones.
     * @param x coordenada horizontal del punto de toque.
     * @param y coordenada vertical del punto de toque.
     */
    public void touched(float x, float y) {
        for(int i=0; i<this.numLeters; i++) {
            if (nextButtons.get(i).touched(x, y)) {
                char next = nextLetter(letras.get(i));
                frames.get(i).setText(String.valueOf(next));
                letras.set(i, next);
            } else if (previousButtons.get(i).touched(x, y)) {
                char previous = previousLetter(letras.get(i));
                frames.get(i).setText(String.valueOf(previous));
                letras.set(i, previous);
            }
        }
    }
}
