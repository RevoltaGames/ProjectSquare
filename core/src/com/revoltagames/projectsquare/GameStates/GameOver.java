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
 * Created by caenrique93 on 17/02/15.
 */
public class GameOver extends GameState {

    private ShapeRenderer renderer;
    private SpriteBatch spriteRenderer;

    /*
    Estos botones son provisionales, cuanto esten las imagenes se usaran
    los ImageCircularButton
    */
    private Button boton2;

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


        boton2 = new Button(
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
        boton2.draw(renderer);
        drawScore();
        drawGameOverText();
        nameChooser.draw(renderer, spriteRenderer);
    }

    private void drawGameOverText() {
        String gameOver = "GAME OVER";
        BitmapFont.TextBounds bounds = fontRed.getBounds(gameOver);
        float textX = ProjectSquare.WIDTH / 2 - bounds.width/2;
        float textY = 9*ProjectSquare.HEIGTH/10 + bounds.height/2;
        spriteRenderer.begin();
        fontRed.draw(spriteRenderer, gameOver, textX, textY);
        spriteRenderer.end();
    }

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
            if (boton2.touched(Gdx.input.getX(), Gdx.input.getY())) {
                track.stop();
                gsm.setState(new Menu(this.gsm));
            }
        }
    }

    @Override
    public void dispose() {
        ProjectSquare.settingsManager.setNewScore(this.score, this.nameChooser.getLeters());
    }
}
