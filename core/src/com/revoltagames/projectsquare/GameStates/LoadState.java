package com.revoltagames.projectsquare.GameStates;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.revoltagames.projectsquare.Entities.Shapes.Square;
import com.revoltagames.projectsquare.Entities.Tween.SpriteAccessor;
import com.revoltagames.projectsquare.Entities.Tween.SquareAccessor;
import com.revoltagames.projectsquare.Managers.GameStateManager;
import com.revoltagames.projectsquare.ProjectSquare;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by caenrique93 on 18/02/15.
 */
public class LoadState extends GameState{
    SpriteBatch renderer;
    Sprite splashScreen;

    TweenManager tweenManager;

    public LoadState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        Texture texSplash = ProjectSquare.resManager.getSplashScreen();
        splashScreen = new Sprite(texSplash);
        renderer = new SpriteBatch();

        tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());

        splashScreen.setPosition((ProjectSquare.WIDTH - splashScreen.getWidth()) / 2,
                (ProjectSquare.HEIGTH - splashScreen.getHeight()) / 2);

        Tween.set(splashScreen, SpriteAccessor.ALPHA).target(0).start(tweenManager);
        Tween.to(splashScreen, SpriteAccessor.ALPHA,0.6f).target(1).start(tweenManager);
        Tween.to(splashScreen, SpriteAccessor.ALPHA,0.6f).target(0).delay(2.8f).start(tweenManager);

    }

    @Override
    public void update(float dt) {
        tweenManager.update(dt);
        handleInput();
    }

    @Override
    public void draw() {
        renderer.begin();
            splashScreen.draw(renderer);

        renderer.end();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void dispose() {

    }
}
