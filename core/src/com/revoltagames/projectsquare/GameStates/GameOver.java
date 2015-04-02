package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Entities.Button;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.Managers.GameStateManager;
import com.revoltagames.projectsquare.Managers.ResourceManager;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Created by caenrique93 on 17/02/15.
 */
public class GameOver extends GameState {

    private String gameOverText;
    private ShapeRenderer renderer;
    private SpriteBatch spriteRenderer;

    /*
    Estos botones son provisionales, cuanto esten las imagenes se usaran
    los ImageCircularButton
    */
    private Button boton1;
    private Button boton2;

    private int score;
    private BitmapFont font;

    private FileHandle scoreHandle;

    private Music track;
    
    private BitmapFont font70;

    protected GameOver(GameStateManager gsm, int score) {
        super(gsm);
        this.score = score;
    }

    @Override
    public void init() {
        track= ProjectSquare.resManager.getSound(ResourceManager.GAMEOVER);
        track.play();
        gameOverText = "GAME OVER";
        int unitStep = ProjectSquare.WIDTH/8;
        boton1 = new Button(unitStep*2, ProjectSquare.HEIGTH/4);
        boton2 = new Button(unitStep*6, ProjectSquare.HEIGTH/4);
        boton1.setColor(ColorManager.LIGHT_GREEN);
        renderer = new ShapeRenderer();
        spriteRenderer = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("Fonts/font1.fnt"),
                Gdx.files.internal("Fonts/font1.png"),
                false);
        font70 = ProjectSquare.resManager.getFont(2);
        font.scale(1.2f);
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void draw() {
        boton1.draw(renderer);
        boton2.draw(renderer);
        drawScore();
        drawGameOverText();
    }

    private void drawGameOverText() {
        BitmapFont.TextBounds bounds = font70.getBounds(gameOverText);
        float textX = ProjectSquare.WIDTH / 2 - bounds.width/2;
        float textY = 3 * ProjectSquare.HEIGTH / 4 + bounds.height/2;
        spriteRenderer.begin();
        font70.draw(spriteRenderer,gameOverText, textX, textY);
        spriteRenderer.end();
    }

    private void drawScore() {
        BitmapFont.TextBounds bounds = font70.getBounds(Integer.toString(score));
        float textX = ProjectSquare.WIDTH / 2 - bounds.width/2;
        float textY = ProjectSquare.HEIGTH / 2 + bounds.height/2;
        spriteRenderer.begin();
        font70.draw(spriteRenderer,Integer.toString(score), textX, textY);
        spriteRenderer.end();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            track.stop();
            if (boton1.touched(Gdx.input.getX(), Gdx.input.getY())) {
                gsm.setState(new Play(this.gsm));
            } else if (boton2.touched(Gdx.input.getX(), Gdx.input.getY())) {
                gsm.setState(new Menu(this.gsm));
            }
        }
    }

    @Override
    public void dispose() {
        ProjectSquare.dataManager.saveScore(score);
        ProjectSquare.dataManager.addCoins(score);
    }
}
