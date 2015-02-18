package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Entities.CircularButton;
import com.revoltagames.projectsquare.Entities.ImageCircularButton;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.Managers.GameStateManager;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Created by caenrique93 on 17/02/15.
 */
public class GameOver extends GameState {

    String gameOverText;
    ShapeRenderer renderer;

    /*
    Estos botones son provisionales, cuanto esten las imagenes se usaran
    los ImageCircularButton
    */
    CircularButton boton1;
    CircularButton boton2;

    ImageCircularButton menuButton;
    ImageCircularButton retryButton;

    protected GameOver(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
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
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            if (boton1.touched(Gdx.input.getX(), Gdx.input.getY())) {
                gsm.setState(new Play(this.gsm));
            } else if (boton2.touched(Gdx.input.getX(), Gdx.input.getY())) {
                gsm.setState(new Menu(this.gsm));
            }
        }
    }

    @Override
    public void dispose() {

    }
}
