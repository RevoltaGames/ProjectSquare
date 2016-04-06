package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Entities.Button;
import com.revoltagames.projectsquare.Entities.Shapes.Rect;
import com.revoltagames.projectsquare.Managers.GameStateManager;
import com.revoltagames.projectsquare.Managers.ResourceManager;
import com.revoltagames.projectsquare.ProjectSquare;
import com.revoltagames.projectsquare.widgets.NameChooserWidget;

/**
 * Estado de GameOver del juego.
 */
public class GameOver extends GameState {

    private ShapeRenderer renderer;
    private SpriteBatch spriteRenderer;

    private Button backButton;

    private int score;

    private Music track;
    
    private BitmapFont font;
    private BitmapFont fontSmallWhite;
    private BitmapFont fontBig;
    private BitmapFont fontRed;

    private NameChooserWidget nameChooser;

    protected GameOver(GameStateManager gsm, int score) {
        super(gsm);
        this.score = score;
    }

    @Override
    public void init() {
        track= ProjectSquare.resManager.getSound(ResourceManager.GAMEOVER);

        if (ProjectSquare.sound)
            track.play();

        nameChooser = new NameChooserWidget(ProjectSquare.WIDTH/2, 2*ProjectSquare.HEIGTH/5, ProjectSquare.WIDTH/2, 3);


        font = ProjectSquare.resManager.getFont("greyBigFont.ttf");
        fontSmallWhite = ProjectSquare.resManager.getFont("whiteSmallFont.ttf");
        fontRed = ProjectSquare.resManager.getFont("redMediumFont.ttf");
        fontBig = ProjectSquare.resManager.getFont("greyGiantFont.ttf");


        backButton = new Button(
            new Rect(
                ProjectSquare.WIDTH/2,
                ProjectSquare.HEIGTH/8,
                ProjectSquare.WIDTH/3,
                ProjectSquare.WIDTH/9),
            "back",
            fontSmallWhite);

        renderer = new ShapeRenderer();
        spriteRenderer = new SpriteBatch();
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void draw() {
        backButton.draw(renderer);
        drawScore();
        drawGameOverText();
        nameChooser.draw(renderer, spriteRenderer);
    }

    /**
     * Dibuja el título de la pantalla de Game Over
     */
    private void drawGameOverText() {
        String gameOver = "GAME OVER";
        BitmapFont.TextBounds bounds = fontRed.getBounds(gameOver);
        float textX = ProjectSquare.WIDTH / 2 - bounds.width/2;
        float textY = 9*ProjectSquare.HEIGTH/10 + bounds.height/2;
        spriteRenderer.begin();
        fontRed.draw(spriteRenderer, gameOver, textX, textY);
        spriteRenderer.end();
    }

    /**
     * Dibuja la puntuación conseguida
     */
    private void drawScore() {
        BitmapFont.TextBounds bounds = fontBig.getBounds(Integer.toString(score));
        float textX = ProjectSquare.WIDTH / 2 - bounds.width/2;
        float textY = 7*ProjectSquare.HEIGTH/10 + bounds.height/2;
        spriteRenderer.begin();
        fontBig.draw(spriteRenderer, Integer.toString(score), textX, textY);
        spriteRenderer.end();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            nameChooser.touched(Gdx.input.getX(), Gdx.input.getY());
            if (backButton.touched(Gdx.input.getX(), Gdx.input.getY())) {
                track.stop();
                gsm.setState(new Menu(this.gsm));
                return;
            }
        }
    }

    @Override
    public void dispose() {
        ProjectSquare.settingsManager.setNewScore(this.score, this.nameChooser.getLeters());
    }
}
