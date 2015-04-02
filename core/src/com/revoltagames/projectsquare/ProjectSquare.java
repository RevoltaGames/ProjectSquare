package com.revoltagames.projectsquare;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.revoltagames.projectsquare.GameStates.LoadState;
import com.revoltagames.projectsquare.GameStates.Menu;
import com.revoltagames.projectsquare.Listeners.MyGestureListener;
import com.revoltagames.projectsquare.Managers.DataManager;
import com.revoltagames.projectsquare.Managers.GameStateManager;
import com.revoltagames.projectsquare.Managers.ResourceManager;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

public class ProjectSquare extends ApplicationAdapter {

    private GameStateManager gameStateManager;
    private long initTime;
    private boolean ready;

    public static int WIDTH;
    public static int HEIGTH;

    public static TweenManager tweenManager = new TweenManager();

    private OrthographicCamera camera;
    public static ResourceManager resManager;
    public static DataManager dataManager;

	@Override
	public void create () {

        Tween.setCombinedAttributesLimit(8);

        WIDTH = Gdx.graphics.getWidth();
        HEIGTH = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(WIDTH, HEIGTH);
        camera.translate(WIDTH/2, HEIGTH/2);
        camera.update();

        dataManager = new DataManager();
        resManager = new ResourceManager();
        initTime = System.currentTimeMillis();
        ready = false;

        gameStateManager = new GameStateManager();
        gameStateManager.setState(new LoadState(gameStateManager));

        Gdx.input.setInputProcessor(new GestureDetector(new MyGestureListener()));
    }

    @Override
    public void pause() {
        dataManager.saveData();
    }

    @Override
    public void dispose() {
        pause();
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(1,1,1,1); //White
        Gdx.gl.glEnable(GL20.GL_BLEND);
        //Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
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
