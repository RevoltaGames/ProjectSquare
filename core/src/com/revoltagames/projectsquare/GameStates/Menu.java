package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Entities.Border;
import com.revoltagames.projectsquare.Entities.Button;
import com.revoltagames.projectsquare.Entities.Shapes.Circle;
import com.revoltagames.projectsquare.Entities.Shapes.Square;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.Managers.GameStateManager;
import com.revoltagames.projectsquare.Managers.GestureManager;
import com.revoltagames.projectsquare.Managers.ResourceManager;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Created by caenrique93 on 17/02/15.
 */
public class Menu extends GameState {

    private ShapeRenderer shapeR;
    private SpriteBatch renderer;
    private Button playB;
    private Music track;

    private Button soundB;
    private Button scoresB;
    private Button settingsB;
    private Button exitB;

    private Border[] borders;
    private BitmapFont font;

    private Texture tittle;
    private Texture touchtoplay;
    private Texture b_exit;
    private Texture b_settings;
    private Texture b_soundon;
    private Texture b_soundoff;
    private Texture b_scores;
    private Texture b_shadow;

    float buttonsr;
    float buttonsx;
    float buttonsy;

    private boolean soundChanged;

    public Menu(GameStateManager gsm) {
        super(gsm);
    }


    @Override
    public void init() {
        track= ProjectSquare.resManager.getSound(ResourceManager.MENU);

        track.play();

        soundChanged = true;
        shapeR = new ShapeRenderer();

        b_exit = ProjectSquare.resManager.getImage(ProjectSquare.resManager.B_EXIT);
        b_scores = ProjectSquare.resManager.getImage(ProjectSquare.resManager.B_SCORES);
        b_settings = ProjectSquare.resManager.getImage(ProjectSquare.resManager.B_SETTINGS);
        b_soundoff = ProjectSquare.resManager.getImage(ProjectSquare.resManager.B_VOLUME_OFF);
        b_soundon = ProjectSquare.resManager.getImage(ProjectSquare.resManager.B_VOLUME_ON);
        tittle = ProjectSquare.resManager.getImage(ProjectSquare.resManager.TITTLE);
        touchtoplay = ProjectSquare.resManager.getImage(ProjectSquare.resManager.TOUCH);
        b_shadow = ProjectSquare.resManager.getImage(ProjectSquare.resManager.SHADOW);

        buttonsr = ProjectSquare.WIDTH/13;
        buttonsx = ProjectSquare.WIDTH/5;
        buttonsy = ProjectSquare.HEIGTH/8;

        soundB = new Button(new Circle(1*buttonsx,buttonsy, buttonsr, Color.RED), b_soundon);
        scoresB = new Button(new Circle(2*buttonsx,buttonsy, buttonsr, Color.RED), b_scores);
        settingsB = new Button(new Circle(3*buttonsx,buttonsy, buttonsr, Color.RED), b_settings);
        exitB = new Button(new Circle(4*buttonsx,buttonsy, buttonsr, Color.RED), b_exit);

        playB = new Button(new Square(ProjectSquare.WIDTH/2, ProjectSquare.HEIGTH/2, ProjectSquare.WIDTH/2));

        borders = new Border[4];
        borders[GestureManager.SW_LEFT - 1] = new Border(GestureManager.SW_LEFT - 1, ColorManager.NBLUE);
        borders[GestureManager.SW_RIGHT - 1] = new Border(GestureManager.SW_RIGHT - 1, ColorManager.NGREEN);
        borders[GestureManager.SW_DOWN - 1] = new Border(GestureManager.SW_DOWN - 1, ColorManager.NYELLOW);
        borders[GestureManager.SW_UP - 1] = new Border(GestureManager.SW_UP - 1, ColorManager.NRED);

        font = ProjectSquare.resManager.getFont(3);
        renderer = new SpriteBatch();
    }

    @Override
    public void update(float dt) {
        handleInput();
        if(soundChanged) {
            if(ProjectSquare.sound) {
                soundB.setImage(b_soundon);
                track.play();
            } else {
                soundB.setImage(b_soundoff);
                track.pause();
            }
            soundChanged = false;

        }

    }

    @Override
    public void draw() {
        //playB.draw(shapeR);
        float width = 3*ProjectSquare.WIDTH/4;
        float height = width/1.9f;

        float twith = ProjectSquare.WIDTH/4;
        float theight = twith/2.62f;

        renderer.begin();
        renderer.draw(tittle,
                      ProjectSquare.WIDTH/2 - width/2,
                      ProjectSquare.HEIGTH/2 + height/1.5f,
                      width,
                      height
                      );
        renderer.draw(touchtoplay,
                      ProjectSquare.WIDTH/2 - twith/2,
                      ProjectSquare.HEIGTH/3 - theight/2,
                      twith,
                      theight);
        for (int i=1; i<5; i++) {
            renderer.draw(b_shadow, i*buttonsx-buttonsr*1.4f, buttonsy-buttonsr*1.7f, 3*buttonsr, 3*buttonsr);
        }
        renderer.end();

        soundB.draw(shapeR);
        scoresB.draw(shapeR);
        settingsB.draw(shapeR);
        exitB.draw(shapeR);

        for (Border border : borders) {
            border.draw(shapeR);
        }

        int score = ProjectSquare.dataManager.getCoins();
        String puntuacion = "puntos: " + score;
        BitmapFont.TextBounds bounds = font.getBounds(puntuacion);

        /*renderer.begin();
        font.draw(renderer,puntuacion, ProjectSquare.WIDTH/2 - bounds.width/2, ProjectSquare.HEIGTH - ProjectSquare.HEIGTH/12 - bounds.height/2);
        renderer.end();*/
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (playB.touched(Gdx.input.getX(), Gdx.input.getY())) {
                track.stop();
                for (Border b: borders)
                    b.startAnimation();
                gsm.setState(new Play(this.gsm, borders));
            }
            if (scoresB.touched(Gdx.input.getX(), Gdx.input.getY())) {
                track.stop();
                gsm.push(new HighScores(gsm));
            }
            if (soundB.touched(Gdx.input.getX(), Gdx.input.getY())) {
                ProjectSquare.sound = !ProjectSquare.sound;
                soundChanged = true;
            }
            if (exitB.touched(Gdx.input.getX(), Gdx.input.getY())) {
                dispose();
                System.exit(0);
            }
        }
    }

    @Override
    public void dispose() {

    }
}

