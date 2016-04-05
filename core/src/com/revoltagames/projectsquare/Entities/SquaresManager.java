package com.revoltagames.projectsquare.Entities;

import aurelienribon.tweenengine.Timeline;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Entities.Shapes.Square;
import com.revoltagames.projectsquare.Entities.Tween.SquareAccessor;
import com.revoltagames.projectsquare.GameStates.Play;
import com.revoltagames.projectsquare.Managers.GestureManager;
import com.revoltagames.projectsquare.ProjectSquare;

import java.util.LinkedList;
import java.util.List;

import aurelienribon.tweenengine.Tween;

/**
 * Created by alejandro on 4/03/15.
 */
public class SquaresManager {

    private  Play play;

    //Control de imput de usuario
    private int swipe;

    //Visualizacion de puntuaciones
    private int numberOfSquares;
    private BitmapFont font;
    SpriteBatch spRenderer1;



    //Cuadrados en pantalla
    private List<Square> squares;
    private List<Square> swipedSquares;
    //private boolean onAnimation = false;

    //Para la previsualizacion
    private float[] Sizes;
    private float[] X;
    private float[] Y;

    private float animationTime = 0.2f;

    public SquaresManager(Play play) {

        this.play = play;

        numberOfSquares = 0;

        Tween.registerAccessor(Square.class, new SquareAccessor());

        spRenderer1 = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("Fonts/font1.fnt"),
                Gdx.files.internal("Fonts/font1.png"), false);
        font.scale(1.2f);

        squares = new LinkedList<Square>();
        swipedSquares = new LinkedList<Square>();

        float mainsize = ProjectSquare.WIDTH / 3;
        Sizes = new float[]{
                mainsize,
                mainsize/4,
                mainsize/6,
                mainsize/8
        };

        X = new float[4];
        Y = new float[4];

        X[0] = ProjectSquare.WIDTH / 2;
        Y[0] = ProjectSquare.HEIGTH / 2;

        X[1] = X[0] - Sizes[0] / 2 + Sizes[0] / 6 + Sizes[1]/3;
        X[2] = X[1] + Sizes[1] + Sizes[1] / 6;
        X[3] = X[2] + Sizes[2] + Sizes[1] / 6;

        // Alineados segun centro
        //Y[1] = Y[2] = Y[3] =  Y[0] + Sizes[0] / 2 + Sizes[1] / 10 + Sizes[1]/2;
        // Alineados segun borde inferior
        for (int i = 1; i < Y.length; i++)
            Y[i] = Y[0] + Sizes[0] / 2 + Sizes[1] / 10 + Sizes[i]/2;

        for (int i=0; i<4; i++)
            squares.add(new Square(X[i],Y[i],Sizes[i]));

        //Eliminamos transparencia del cuadrado principal
        squares.get(0).setAlpha(1);

    }

    public void draw(ShapeRenderer shapeR, SpriteBatch spRenderer) {

        for(Square square: squares)
            square.draw(shapeR);

        squares.get(0).draw(shapeR);

        if (!swipedSquares.isEmpty())
            for (Square swipedSquare: swipedSquares)
                swipedSquare.draw(shapeR);

    }


    public void update(float dt, int swipe) {

        this.swipe = swipe;
        if (swipe != 0) {
            float xFinal, yFinal;
            switch (swipe) {
                case GestureManager.SW_DOWN:
                    xFinal = X[0];
                    yFinal = 0 - Sizes[0];
                    break;
                case GestureManager.SW_LEFT:
                    xFinal = 0 - Sizes[0]/2;
                    yFinal = Y[0];
                    break;
                case GestureManager.SW_RIGHT:
                    xFinal = ProjectSquare.WIDTH + Sizes[0];
                    yFinal = Y[0];
                    break;
                case GestureManager.SW_UP:
                    xFinal = X[0];
                    yFinal = ProjectSquare.HEIGTH + Sizes[0];
                    break;
                default:
                    xFinal = X[0];
                    yFinal = Y[0];
            }
            Square swippedSquare = squares.get(0);
            swippedSquare.setSwipe(swipe);
            //Animacion al borde
            animateSquareTo(swippedSquare, xFinal, yFinal, Sizes[0]);

            swipedSquares.add(swippedSquare);
            squares.remove(swippedSquare);

            //Animaciones de recolocacion de previsualizacion
            for (int i = 0; i < squares.size(); i++) {
                Square actual = squares.get(i);
                animateSquareTo(actual, X[i], Y[i], Sizes[i]);
                if (i == 0)
                    animateSquareAlphaTo(actual, 1);
            }

            Square square = new Square(X[3],Y[3], 0);
            squares.add(square);
            animateSquareTo(square, X[3], Y[3], Sizes[3]);
        }
        


       if (!swipedSquares.isEmpty())
            for (Square swipedSquare : swipedSquares) {
                if (swipedSquare.atBorder()) {
                    if (swipedSquare.getColor() != play.getBorders()[swipedSquare.getSwipe() - 1].getColor())
                        play.setGameOver(true);
                    else {
                        swipedSquares.remove(swipedSquare);
                        numberOfSquares++;
                    }
                }

            }

    }

    public void initialAnimation(Timeline timeline) {

//        for(Square sq : squares){
//            timeline.push(Tween.set(sq, SquareAccessor.ALPHA).target(0));
//        }
//        timeline.pushPause(500);
//        timeline.beginParallel();
//
//        for(Square sq : squares){
//            timeline.push(prepareAnimateSquareAlphaTo(sq, 1, 1f));
//        }
//
//        timeline.end();
        for(int i=0; i<squares.size(); i++){
            squares.get(i).setSize(0);
            timeline.push(Tween.set(squares.get(i), SquareAccessor.POS_AND_SIZE).target(X[i], Y[i], 0));
        }

        timeline.beginSequence();

        for(int i=0; i<squares.size(); i++){
            Square sq = squares.get(i);
            timeline.push(prepareAnimateSquareTo(sq, X[i], Y[i], Sizes[i]));
        }

        timeline.end();
    }

    private void animateSquareTo(Square square, float x, float y, float size) {
        prepareAnimateSquareTo(square,x,y,size).start(ProjectSquare.tweenManager);
    }

    private Tween prepareAnimateSquareTo(Square square, float x, float y, float size) {
        return Tween.to(square, SquareAccessor.POS_AND_SIZE, animationTime)
                .target(x,y,size);
    }

    private void animateSquareAlphaTo(Square square, float alpha) {
        prepareAnimateSquareAlphaTo(square, alpha).start(ProjectSquare.tweenManager);
    }

    private Tween prepareAnimateSquareAlphaTo(Square square, float alpha, float time) {
        return Tween.to(square, SquareAccessor.ALPHA, animationTime)
                .target(alpha);
    }

    private Tween prepareAnimateSquareAlphaTo(Square square, float alpha) {
        return prepareAnimateSquareAlphaTo(square, alpha, animationTime);
    }



}
