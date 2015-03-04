package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Entities.CircularButton;
import com.revoltagames.projectsquare.Entities.ImageCircularButton;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.Managers.GameStateManager;
import com.revoltagames.projectsquare.Managers.ResourceManager;
import com.revoltagames.projectsquare.ProjectSquare;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
    private CircularButton boton1;
    private CircularButton boton2;

    private int score;
    private BitmapFont font;

    private FileHandle scoreHandle;

    private Music track;

    private ImageCircularButton menuButton;
    private ImageCircularButton retryButton;
    private BitmapFont font70;

    protected GameOver(GameStateManager gsm, int score) {
        super(gsm);
        this.score = score;
    }

    @Override
    public void init() {
        track= ProjectSquare.rm.getSound(ResourceManager.GAMEOVER);
        track.play();
        gameOverText = "jaja you loser!!";
        int unitStep = ProjectSquare.WIDTH/8;
        boton1 = new CircularButton(unitStep*2,
                ProjectSquare.HEIGTH/4,
                unitStep,
                ColorManager.LIGHT_GREEN);
        boton2 = new CircularButton(unitStep*6,
                ProjectSquare.HEIGTH/4,
                unitStep,
                ColorManager.LIGHT_BLUE);
        renderer = new ShapeRenderer();
        spriteRenderer = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("Fonts/font1.fnt"),
                Gdx.files.internal("Fonts/font1.png"),
                false);
        font70 = ProjectSquare.rm.getFont(2, Color.BLACK);
        font.scale(1.2f);
    }

    private void saveScore() {
        scoreHandle = Gdx.files.local("highScores");
        if (!scoreHandle.exists()) {
            try {
                scoreHandle.file().createNewFile();
                scoreHandle.writeString("-1 -1 -1 -1 -1", false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String stringScore = scoreHandle.readString();
        String[] scores = stringScore.split(" ");
        LinkedList<Integer> sortedScore = (LinkedList<Integer>) toIntegerList(scores);
        int next; int old=0;
        boolean inserted = false;
        for(int i=0;i<sortedScore.size();i++) {
            if(inserted) {
                next = sortedScore.get(i);
                sortedScore.set(i, old);
                old = next;
            } else {
                old = sortedScore.get(i);
                if(score > old) {
                    inserted = true;
                    sortedScore.set(i, score);
                }
            }
        }
        String finalScore = toStringList(sortedScore);
        scoreHandle.writeString(finalScore, false);
        System.out.println(finalScore);
    }

    private String toStringList(List<Integer> theList) {
        String scoreString = "";
        for(int score : theList) {
            scoreString = scoreString + score + " ";
        }
        return  scoreString;
    }

    private List<Integer> toIntegerList(String[] theList) {
        LinkedList<Integer> IntegerList = new LinkedList<Integer>();
        for(String score : theList) {
            IntegerList.add(Integer.parseInt(score));
        }
        return  IntegerList;
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void draw() {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        boton1.draw(renderer);
        boton2.draw(renderer);
        renderer.end();
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
        saveScore();
    }
}
