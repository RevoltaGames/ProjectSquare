package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Entities.Button;
import com.revoltagames.projectsquare.Entities.Shapes.Rect;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.Managers.GameStateManager;
import com.revoltagames.projectsquare.ProjectSquare;
import com.revoltagames.projectsquare.widgets.OptionWidget;
import com.revoltagames.projectsquare.widgets.ToggleWidget;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by alejandro on 6/04/16.
 */
public class SettingsState extends GameState {

    private ShapeRenderer shapeR;
    private SpriteBatch spriteB;

    private OptionWidget dificultyOption;
    private ToggleWidget godMode;
    private ToggleWidget time;
    private ToggleWidget lives;

    private String message;
    private BitmapFont font;
    private BitmapFont.TextBounds bounds;
    private float textWidth;

    private Button backButton;

    public SettingsState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        shapeR = new ShapeRenderer();
        spriteB = new SpriteBatch();



        godMode = new ToggleWidget(ProjectSquare.WIDTH/2, 6*ProjectSquare.HEIGTH/10, ProjectSquare.settingsManager.godMode, "God mode");
        float widgetHeight = godMode.getBoundaries().getHeight();

        time = new ToggleWidget(ProjectSquare.WIDTH/2, 6*ProjectSquare.HEIGTH/10 - widgetHeight*1.5f, ProjectSquare.settingsManager.time, "Time");
        lives = new ToggleWidget(ProjectSquare.WIDTH/2, 6*ProjectSquare.HEIGTH/10 - widgetHeight*3f, ProjectSquare.settingsManager.lives, "Lives");
        dificultyOption = new OptionWidget(
                ProjectSquare.WIDTH/2,
                6*ProjectSquare.HEIGTH/10 - widgetHeight*4.5f,
                "Dificulty",
                new ArrayList<String>(Arrays.asList("1", "2", "3")),
                ProjectSquare.settingsManager.dificulty%3
        );
        message = "Settings";

        font = ProjectSquare.resManager.getFont("greenMediumFont.ttf");
        bounds = font.getBounds(message);
        textWidth = bounds.width;

        BitmapFont backButtonFont = ProjectSquare.resManager.getFont("whiteSmallFont.ttf");

        backButton = new Button(
                new Rect(ProjectSquare.WIDTH/2,
                        ProjectSquare.HEIGTH/5,
                        ProjectSquare.WIDTH/3,
                        ProjectSquare.WIDTH/9),
                "back",
                backButtonFont
        );
        backButton.setColor(ColorManager.NGREEN);
    }

    @Override
    public void update(float dt) {
        this.handleInput();
        this.changeSettings();
    }

    private void changeSettings() {
        ProjectSquare.settingsManager.dificulty = dificultyOption.getOption();
        ProjectSquare.settingsManager.godMode = godMode.getState();
        ProjectSquare.settingsManager.lives = lives.getState();
        ProjectSquare.settingsManager.time = time.getState();
    }

    @Override
    public void draw() {
        spriteB.begin();
        font.draw(spriteB, message, ProjectSquare.WIDTH/2 - textWidth/2, 8 * ProjectSquare.HEIGTH / 10 - bounds.height / 2);
        spriteB.end();

        dificultyOption.draw(shapeR, spriteB);
        godMode.draw(shapeR, spriteB);
        time.draw(shapeR, spriteB);
        lives.draw(shapeR, spriteB);
        backButton.draw(shapeR);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            dificultyOption.touched(Gdx.input.getX(), Gdx.input.getY());
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();
            if (godMode.touched(x,y))
                godMode.toggle();
            if (time.touched(x,y))
                time.toggle();
            if (lives.touched(x,y))
                lives.toggle();
            if (backButton.touched(Gdx.input.getX(), Gdx.input.getY()))
                gsm.pop();
        }
    }

    @Override
    public void dispose() {

    }
}
