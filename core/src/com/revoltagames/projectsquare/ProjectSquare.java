package com.revoltagames.projectsquare;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.revoltagames.projectsquare.GameStates.LoadState;
import com.revoltagames.projectsquare.GameStates.Menu;
import com.revoltagames.projectsquare.Listeners.MyGestureListener;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.Managers.GameStateManager;
import com.revoltagames.projectsquare.Managers.ResourceManager;
import com.revoltagames.projectsquare.Managers.SettingsManager;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

public class ProjectSquare extends ApplicationAdapter {

    private GameStateManager gameStateManager;
    private long initTime;
    private boolean ready;
    public static boolean sound;

    public static int WIDTH;
    public static int HEIGTH;

    public static TweenManager tweenManager = new TweenManager();

    private OrthographicCamera camera;
    public static ResourceManager resManager;
    public static SettingsManager settingsManager;

	@Override
	public void create () {

        Tween.setCombinedAttributesLimit(8);

        WIDTH = Gdx.graphics.getWidth();
        HEIGTH = Gdx.graphics.getHeight();

        sound = true;

        camera = new OrthographicCamera(WIDTH, HEIGTH);
        camera.translate(WIDTH/2, HEIGTH/2);
        camera.update();

        settingsManager = new SettingsManager();
        resManager = new ResourceManager();
        initTime = System.currentTimeMillis();
        ready = false;

        gameStateManager = new GameStateManager(this);
        gameStateManager.setState(new LoadState(gameStateManager));

        Gdx.input.setInputProcessor(new GestureDetector(new MyGestureListener()));
    }

    @Override
    public void pause() {
        settingsManager.save();
    }

    @Override
    public void dispose() {
        pause();
        Gdx.app.exit();
    }

	@Override
	public void render () {
        Color colorFondo = ColorManager.WHITE;
		Gdx.gl.glClearColor(colorFondo.r,colorFondo.g,colorFondo.b,colorFondo.a); //White
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(!ready) {
            long dTime = System.currentTimeMillis() - initTime;
            if(resManager.update() && (dTime >= 4000)) {
                gameStateManager.setState(new Menu(gameStateManager));
                ready = true;
            }
        }

        float dt = Gdx.graphics.getDeltaTime();
        tweenManager.update(dt);
        gameStateManager.update(dt);
        gameStateManager.draw();
	}
}
