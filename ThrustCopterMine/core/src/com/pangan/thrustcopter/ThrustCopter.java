package com.pangan.thrustcopter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ThrustCopter extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	FPSLogger fpsLogger;
	OrthographicCamera camera;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		fpsLogger = new FPSLogger();
		camera = new OrthographicCamera();
		background = new Texture("background.png");
	}

	@Override
	public void render () {

		//Limpieza de la pantalla
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//FPS
		fpsLogger.log();

		//Añado la cámara
		camera.setToOrtho(false, 800, 480);

		//Actualizo escena
		updateScene();

		//Dibujo la escena
		drawScene();
	}

	public void updateScene(){

	}

	public void drawScene(){

		//Preparo la cámara
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		//Dibujo
		batch.begin();
		batch.draw(background, 0, 0);
		batch.end();

	}
}
