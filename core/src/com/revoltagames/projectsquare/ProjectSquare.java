package com.revoltagames.projectsquare;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.revoltagames.projectsquare.GameStates.Menu;
import com.revoltagames.projectsquare.Listeners.MyGestureListener;
import com.revoltagames.projectsquare.Managers.GameStateManager;

public class ProjectSquare extends ApplicationAdapter {

    private GameStateManager gameStateManager;

    public static int WIDTH;
    public static int HEIGTH;

    private OrthographicCamera camera;
	
	@Override
	public void create () {

        WIDTH = Gdx.graphics.getWidth();
        HEIGTH = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(WIDTH, HEIGTH);
        camera.translate(WIDTH/2, HEIGTH/2);
        camera.update();

        gameStateManager = new GameStateManager();
        gameStateManager.setState(new Menu(gameStateManager));

        Gdx.input.setInputProcessor(new GestureDetector(new MyGestureListener()));
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1); //White
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStateManager.update(Gdx.graphics.getDeltaTime());
        gameStateManager.draw();
	}
}
