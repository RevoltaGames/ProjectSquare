package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.revoltagames.projectsquare.Entities.Tween.SpriteAccessor;
import com.revoltagames.projectsquare.Managers.GameStateManager;
import com.revoltagames.projectsquare.ProjectSquare;

import aurelienribon.tweenengine.Tween;

/**
 * Estado de juego que muestra el logo mientras cargan los assets
 */
public class LoadState extends GameState{
    SpriteBatch renderer;
    Sprite splashScreen;

    /**
     * Constructor
     * @param gsm GameStateManager
     */
    public LoadState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        Texture texSplash = ProjectSquare.resManager.getSplashScreen();
        splashScreen = new Sprite(texSplash);
        renderer = new SpriteBatch();

        Tween.registerAccessor(Sprite.class, new SpriteAccessor());

        float w = ProjectSquare.WIDTH*0.8f;
        float h = splashScreen.getHeight() * w/splashScreen.getWidth();
        splashScreen.setSize(w,h);

        splashScreen.setPosition((ProjectSquare.WIDTH - splashScreen.getWidth()) / 2,
                (ProjectSquare.HEIGTH - splashScreen.getHeight()) / 2);

        Tween.set(splashScreen, SpriteAccessor.ALPHA).target(1).start(ProjectSquare.tweenManager);
        Tween.to(splashScreen, SpriteAccessor.ALPHA,1f).target(0).delay(3f).start(ProjectSquare.tweenManager);

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void draw() {
        renderer.begin();
        splashScreen.draw(renderer);
        renderer.end();
    }

    @Override
    public void handleInput() {}

    @Override
    public void dispose() {}
}
