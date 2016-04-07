package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Entities.Border;
import com.revoltagames.projectsquare.Entities.Button;
import com.revoltagames.projectsquare.Entities.Clock;
import com.revoltagames.projectsquare.Entities.Shapes.Rect;
import com.revoltagames.projectsquare.Entities.Shapes.Square;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.Managers.GameStateManager;
import com.revoltagames.projectsquare.Managers.GestureManager;
import com.revoltagames.projectsquare.Managers.ResourceManager;
import com.revoltagames.projectsquare.Managers.SquaresManager;
import com.revoltagames.projectsquare.ProjectSquare;
import java.util.Vector;
import aurelienribon.tweenengine.Timeline;

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
    private static Music lifelost;

    private Music move;
    private Music select;

    private int timer;
    private int timeIncrement;
    private int score;
    private int scoreIncrement;
    private int phaseScore;
    private float secondCounter = 0;

    private BitmapFont font;

    private SquaresManager squaresManager;

    private Clock clock;
    private Button godButton;

    private boolean animationNotFinished = true;
    private Timeline bordersAnimation;
    private Timeline squaresAnimation;

    protected Play(GameStateManager gsm, Border[] borders, Timeline animation) {
        super(gsm);
        this.borders = borders;
        this.bordersAnimation = animation;
    }

    @Override
    public void init() {
        shapeR = new ShapeRenderer();
        select= ProjectSquare.resManager.getSound(ResourceManager.SELECT);
        select.setVolume(0.5f);
        spriteRenderer = new SpriteBatch();
        squaresManager = new SquaresManager(this);
        clock = new Clock(timer);
        BitmapFont godButtonFont = ProjectSquare.resManager.getFont("whiteSmallFont.ttf");
        godButton = new Button(
                new Rect(ProjectSquare.WIDTH/2,
                        ProjectSquare.HEIGTH/5,
                        ProjectSquare.WIDTH/3,
                        ProjectSquare.WIDTH/9),
                "exit",
                godButtonFont
        );

        timer = 30;
        score = 0;
        phaseScore = 0;
        timeIncrement = 20;
        switch (ProjectSquare.settingsManager.dificulty) {
            case 0:
                scoreIncrement = 20;
                break;
            case 1:
                scoreIncrement = 30;
                break;
            case 2:
                scoreIncrement = 50;
                break;
        }
        gameOver = false;

        squaresAnimation = Timeline.createSequence();
        squaresManager.initialAnimation(squaresAnimation);

        track = ProjectSquare.resManager.getMusic(0);
        move = ProjectSquare.resManager.getSound(ResourceManager.MOVE);
        lifeup = ProjectSquare.resManager.getSound(ResourceManager.LIFEUP);
        intro = ProjectSquare.resManager.getSound(ResourceManager.INTROGAME);
        lifelost = ProjectSquare.resManager.getSound(ResourceManager.LIFELOST);

        lifelost.setVolume(0.25f);

        if(ProjectSquare.sound)
            intro.play();

        track.setLooping(true);

        initVidas();

        font = ProjectSquare.resManager.getFont("greyBigFont.ttf");

        GestureManager.clear();
    }

    private void initVidas() {
        numVidas = 3 - ProjectSquare.settingsManager.dificulty%3;

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
    }

    @Override
    public void update(float dt) {
        if (gameOver) {
            this.gsm.setState(new GameOver(this.gsm, score));
            return;
        }

        if(!track.isPlaying()&&!intro.isPlaying()&&ProjectSquare.sound){
            track.play();
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

        if(!ProjectSquare.settingsManager.godMode && ProjectSquare.settingsManager.time) {
            clock.update(timer, secondCounter);

            secondCounter += dt;
            if (secondCounter >= 1) {
                secondCounter = 0;
                timer--;
            }
        }

        squaresManager.update(dt, swipe);

        // Acaba de deslizar el cuadrado
        if (swipe != 0) {
            if (correctSwipe(swipe, squaresManager.getLastSwiped())){
                score++;
                phaseScore++;
                if (phaseScore == scoreIncrement) {
                    if(!ProjectSquare.settingsManager.godMode && ProjectSquare.settingsManager.time)
                        timer += timeIncrement;
                    phaseScore = 0;
                    if(ProjectSquare.sound)
                        lifeup.play();
                }
            } else {
                phaseScore = 0;
            }
        }

        if (timer == 0) this.setFail();
    }

    @Override
    public void draw() {
        if(!animationNotFinished) {
            drawScore();
            if (!ProjectSquare.settingsManager.godMode) {
                if(ProjectSquare.settingsManager.time)
                    clock.draw(shapeR);
                if(ProjectSquare.settingsManager.lives)
                    drawVidas();
            } else {
                godButton.draw(shapeR);
            }
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
        if (ProjectSquare.settingsManager.godMode && Gdx.input.justTouched()) {
            if (godButton.touched(Gdx.input.getX(), Gdx.input.getY())) {
                if(ProjectSquare.sound)
                    select.play();
                this.gameOver =true;
            }
        }
    }

    @Override
    public void dispose() {
        track.stop();
    }

    public Border[] getBorders() {
        return borders;
    }

    public void setFail() {
        if (!ProjectSquare.settingsManager.godMode) {
            if(!ProjectSquare.settingsManager.lives)
                this.gameOver = true;
            else if (this.numVidas > 1) {
                numVidas--;
                this.vidas.get(numVidas).setColor(ColorManager.ColorClockBack);
                if(ProjectSquare.sound)
                    lifelost.play();
            }
            else this.gameOver = true;
        }
    }

    private boolean correctSwipe(int swipe, Square square) {
        Color b_color = borders[swipe-1].getColor();
        return square.getColor() == b_color;
    }
}
