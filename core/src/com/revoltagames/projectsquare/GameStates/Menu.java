package com.revoltagames.projectsquare.GameStates;

import aurelienribon.tweenengine.Timeline;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Entities.Border;
import com.revoltagames.projectsquare.Entities.Button;
import com.revoltagames.projectsquare.Entities.Shapes.Square;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.Managers.GameStateManager;
import com.revoltagames.projectsquare.Managers.GestureManager;
import com.revoltagames.projectsquare.Managers.ResourceManager;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Pantalla de Menu del juego
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
        track.setLooping(true);
        if (ProjectSquare.sound) {
            track.play();
        }

        soundChanged = true;
        shapeR = new ShapeRenderer();

        b_exit = ProjectSquare.resManager.getImage(ProjectSquare.resManager.B_EXIT);
        b_scores = ProjectSquare.resManager.getImage(ProjectSquare.resManager.B_SCORES);
        b_settings = ProjectSquare.resManager.getImage(ProjectSquare.resManager.B_SETTINGS);
        b_soundoff = ProjectSquare.resManager.getImage(ProjectSquare.resManager.B_VOLUME_OFF);
        b_soundon = ProjectSquare.resManager.getImage(ProjectSquare.resManager.B_VOLUME_ON);
        tittle = ProjectSquare.resManager.getImage(ProjectSquare.resManager.TITTLE);
        touchtoplay = ProjectSquare.resManager.getImage(ProjectSquare.resManager.TOUCH);

        buttonsr = ProjectSquare.WIDTH/7;
        buttonsx = ProjectSquare.WIDTH/5;
        buttonsy = ProjectSquare.HEIGTH/8;
        float offset = ProjectSquare.HEIGTH/2 - (buttonsy + buttonsr)/2 + buttonsr/2;


        scoresB = new Button(new Square(1*buttonsx,buttonsy + offset, buttonsr), b_scores);
        settingsB = new Button(new Square(1*buttonsx, offset, buttonsr), b_settings);
        soundB = new Button(new Square(4*buttonsx,buttonsy + offset, buttonsr), b_soundon);
        exitB = new Button(new Square(4*buttonsx,offset, buttonsr), b_exit);

        playB = new Button(new Square(ProjectSquare.WIDTH/2, ProjectSquare.HEIGTH/2, ProjectSquare.WIDTH/2));

        borders = new Border[4];
        borders[GestureManager.SW_LEFT - 1] = new Border(GestureManager.SW_LEFT - 1, ColorManager.NBLUE, true);
        borders[GestureManager.SW_RIGHT - 1] = new Border(GestureManager.SW_RIGHT - 1, ColorManager.NGREEN, true);
        borders[GestureManager.SW_DOWN - 1] = new Border(GestureManager.SW_DOWN - 1, ColorManager.NYELLOW, true);
        borders[GestureManager.SW_UP - 1] = new Border(GestureManager.SW_UP - 1, ColorManager.NRED, true);

        font = ProjectSquare.resManager.getFont(4);
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

        /*renderer.begin();
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
        renderer.end();*/

        drawTitle();
        drawButtons();

        for (Border border : borders) {
            border.draw(shapeR);
        }

        /*renderer.begin();
        font.draw(renderer,puntuacion, ProjectSquare.WIDTH/2 - bounds.width/2, ProjectSquare.HEIGTH - ProjectSquare.HEIGTH/12 - bounds.height/2);
        renderer.end();*/
    }

    private void drawButtons() {
        soundB.draw(shapeR);
        scoresB.draw(shapeR);
        settingsB.draw(shapeR);
        exitB.draw(shapeR);
    }

    private void drawTitle() {
        BitmapFont.TextBounds bounds = font.getBounds("Project");
        float textX = ProjectSquare.WIDTH / 2 - bounds.width/2;
        float textY = 4*ProjectSquare.HEIGTH/5 + bounds.height/2;
        BitmapFont.TextBounds bounds2 = font.getBounds("Square");
        float text2X = ProjectSquare.WIDTH / 2 - bounds2.width/2;
        float text2Y = 1*ProjectSquare.HEIGTH/5 + bounds.height/2;
        renderer.begin();
        font.draw(renderer, "Project", textX, textY);
        font.draw(renderer,"Square", text2X, text2Y);
        renderer.end();
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (playB.touched(Gdx.input.getX(), Gdx.input.getY())) {
                track.stop();

                Timeline animation = Timeline.createParallel();
                for (Border b: borders)
                    b.addAnimation(animation);
                animation.start(ProjectSquare.tweenManager);
                gsm.setState(new Play(this.gsm, borders, animation));

            }
            if (scoresB.touched(Gdx.input.getX(), Gdx.input.getY())) {
                gsm.push(new HighScores(gsm));
            }
            if (soundB.touched(Gdx.input.getX(), Gdx.input.getY())) {
                ProjectSquare.sound = !ProjectSquare.sound;
                soundChanged = true;
            }
            if (exitB.touched(Gdx.input.getX(), Gdx.input.getY())) {
                dispose();
                gsm.projectSquare.dispose();
            }
            if (settingsB.touched(Gdx.input.getX(), Gdx.input.getY())){
                gsm.push(new SettingsState(gsm));
            }
        }
    }

    @Override
    public void dispose() {
        System.out.println("saliendo");
    }
}

