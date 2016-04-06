package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Entities.Border;
import com.revoltagames.projectsquare.Entities.Clock;
import com.revoltagames.projectsquare.Entities.Shapes.Square;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.Managers.GameStateManager;
import com.revoltagames.projectsquare.Managers.GestureManager;
import com.revoltagames.projectsquare.Managers.ResourceManager;
import com.revoltagames.projectsquare.Managers.SquaresManager;
import com.revoltagames.projectsquare.ProjectSquare;

import java.util.Vector;

import aurelienribon.tweenengine.Timeline;

/**
 * Created by caenrique93 on 17/02/15.
 */
public class Play extends GameState {

    private ShapeRenderer shapeR;
    private SpriteBatch spriteRenderer;

    private Border[] borders;

    private int swipe = 0;
    private boolean gameOver;

    private int numVidas;
    private Vector<Square> vidas;

    private static Music track;
    private static Music intro;
    private static Music lifeup;
    private Music move;

    private int timer;
    private final int timeIncrement;
    private int score;
    private final int scoreIncrement;
    private int phase;
    private int phaseScore;
    private float secondCounter = 0;

    private BitmapFont font;

    private SquaresManager squaresManager;

    private Clock clock;

    private boolean animationNotFinished = true;
    private Timeline bordersAnimation;
    private Timeline squaresAnimation;

    protected Play(GameStateManager gsm, Border[] borders, Timeline animation) {
        super(gsm);
        timeIncrement = 15;
        scoreIncrement = 20;
        this.borders = borders;
        this.bordersAnimation = animation;

        squaresAnimation = Timeline.createSequence();
        squaresManager.initialAnimation(squaresAnimation);
    }

    protected Play(GameStateManager gsm) {
        super(gsm);
        timeIncrement = 15;
        scoreIncrement = 20;
        borders = new Border[4];
        borders[GestureManager.SW_LEFT - 1] = new Border(GestureManager.SW_LEFT - 1, ColorManager.NBLUE, false);
        borders[GestureManager.SW_RIGHT - 1] = new Border(GestureManager.SW_RIGHT - 1, ColorManager.NGREEN, false);
        borders[GestureManager.SW_DOWN - 1] = new Border(GestureManager.SW_DOWN - 1, ColorManager.NYELLOW, false);
        borders[GestureManager.SW_UP - 1] = new Border(GestureManager.SW_UP - 1, ColorManager.NRED, false);
        GestureManager.clear();

        animationNotFinished = false;
    }

    @Override
    public void init() {
        shapeR = new ShapeRenderer();
        spriteRenderer = new SpriteBatch();

        intro = ProjectSquare.resManager.getSound(ResourceManager.INTROGAME);
        intro.play();

        track = ProjectSquare.resManager.getMusic(0);
        track.setLooping(true);

        lifeup = ProjectSquare.resManager.getSound(ResourceManager.LIFEUP);

        move = ProjectSquare.resManager.getSound(ResourceManager.MOVE);

        timer = 30;
        score = 0;
        phase = 0;
        phaseScore = 0;

        numVidas = 3 - ProjectSquare.settingsManager.dificulty;

        float vidaSize = ProjectSquare.WIDTH/15;
        float vidaGap = ProjectSquare.WIDTH/25;
        float vidasX = ProjectSquare.WIDTH/2 - (vidaSize + vidaGap);
        float vidaY = 3*ProjectSquare.HEIGTH/10;

        vidas = new Vector<Square>();
        for(int i=0; i<numVidas; i++) {
            Square s = new Square(vidasX + (vidaSize+vidaGap)*i, vidaY, vidaSize);
            s.setColor(ColorManager.NRED);
            vidas.add(s);
        }
        for(int i=numVidas; i<3; i++) {
            Square s = new Square(vidasX + (vidaSize+vidaGap)*i, vidaY, vidaSize);
            s.setColor(ColorManager.ColorClockBack);
            vidas.add(s);
        }

        font = ProjectSquare.resManager.getFont(4);

        gameOver = false;

        squaresManager = new SquaresManager(this);

       /* borders = new Border[4];
        borders[GestureManager.SW_LEFT - 1] = new Border(GestureManager.SW_LEFT - 1, ColorManager.NBLUE);
        borders[GestureManager.SW_RIGHT - 1] = new Border(GestureManager.SW_RIGHT - 1, ColorManager.NGREEN);
        borders[GestureManager.SW_DOWN - 1] = new Border(GestureManager.SW_DOWN - 1, ColorManager.NYELLOW);
        borders[GestureManager.SW_UP - 1] = new Border(GestureManager.SW_UP - 1, ColorManager.NRED);*/
        GestureManager.clear();

        clock = new Clock(timer);


    }

    @Override
    public void update(float dt) {

        if(!track.isPlaying()&&!intro.isPlaying()){
            track.play();
        }

        if(!intro.isPlaying()&&!lifeup.isPlaying()&&score%20==0&&score!=0){
            lifeup.play();
        }

        if (animationNotFinished) {
            if(bordersAnimation.isFinished() && !squaresAnimation.isStarted()) {
                squaresAnimation.start(ProjectSquare.tweenManager);
            }
            animationNotFinished = !bordersAnimation.isFinished() && !squaresAnimation.isFinished();
            GestureManager.clear();
            return;
        }

        handleInput();

        clock.update(timer, secondCounter);

        if (gameOver) {
            this.gsm.setState(new GameOver(this.gsm, score));
        }

        secondCounter += dt;
        if (secondCounter >= 1) {
            secondCounter = 0;
            timer--;
        }

        squaresManager.update(dt, swipe);

        // Acaba de deslizar el cuadrado
        if (swipe != 0) {
            score++;
            phaseScore++;
            if (phaseScore == scoreIncrement) {
                phase++;
                timer += timeIncrement;
                phaseScore = 0;
            }
        }

        if (timer == 0) this.setFail();
    }

    @Override
    public void draw() {

        if(!animationNotFinished) {
            drawScore();
            clock.draw(shapeR);
            drawVidas();
        }


        for (Border border : borders) {
            border.draw(shapeR);
        }

        squaresManager.draw(shapeR, spriteRenderer);


    }

    private void drawVidas() {
        for(Square s: vidas) s.draw(shapeR);
    }


    private void drawScore() {
        spriteRenderer.begin();
        BitmapFont.TextBounds timerBound =
                font.getBounds(Integer.toString(score));
        float timerX = ProjectSquare.WIDTH / 2 - timerBound.width / 2;
        float timerY = 4 * ProjectSquare.HEIGTH / 5 + timerBound.height / 2;

        Color oldColor = spriteRenderer.getColor();
        spriteRenderer.setColor(Color.LIGHT_GRAY);
        font.draw(spriteRenderer, Integer.toString(score), timerX, timerY);
        spriteRenderer.setColor(oldColor);
        spriteRenderer.end();
    }

    @Override
    public void handleInput() {
        swipe = GestureManager.getSwipe();
    }

    @Override
    public void dispose() {
        track.stop();
    }

    /*public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }*/

    public Border[] getBorders() {
        return borders;
    }

    public void setFail() {
        if (this.numVidas > 0) {
            numVidas--;
            this.vidas.get(numVidas).setColor(ColorManager.ColorClockBack);
        }
        else this.gameOver = true;
    }
}
