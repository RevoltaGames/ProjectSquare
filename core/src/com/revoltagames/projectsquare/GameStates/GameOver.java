package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.Gdx;
import com.revoltagames.projectsquare.Entities.ImageCircularButton;
import com.revoltagames.projectsquare.Managers.GameStateManager;

/**
 * Created by caenrique93 on 17/02/15.
 */
public class GameOver extends GameState {

    String gameOverText;
    ImageCircularButton menuButton;
    ImageCircularButton retryButton;

    protected GameOver(GameStateManager gsm) {
        super(gsm);
        //menuButton = new ImageCircularButton(ProjectSquare.WIDTH / 4, ProjectSquare.HEIGTH / 4, );
    }

    @Override
    public void init() {
        gameOverText = "jaja you loser!!";
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void draw() {
        // TODO
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            // TODO
        }
    }

    @Override
    public void dispose() {

    }
}
