package com.pangan.thrustcopter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class ThrustCopter extends ApplicationAdapter {

	SpriteBatch batch;
	TextureRegion background;
	FPSLogger fpsLogger;
	OrthographicCamera camera;
	TextureRegion terrainBelow, terrainAbove;
	TextureAtlas atlas;
//	TextureRegion bgRegion;
//	Sprite backgroundSprite;
	float terrainOffset;

	// Variables del avión
	Animation plane;
	float planeAnimTime;
	Vector2 planeVelocity;
	Vector2 planePosition;
	Vector2 planeDefaultPosition;
	Vector2 gravity;
	private static final Vector2 damping = new Vector2(0.99f, 0.99f);
	
	@Override
	public void create () {

		batch = new SpriteBatch();
		fpsLogger = new FPSLogger();
		camera = new OrthographicCamera();
		atlas = new TextureAtlas(Gdx.files.internal("thrustcopterassets.txt"));
//		background = new Texture("background.png");
		background = atlas.findRegion("background");
//		terrainBelow = new TextureRegion(new Texture("groundGrass.png"));
		terrainBelow = atlas.findRegion("groundGrass");
		terrainAbove = new TextureRegion(terrainBelow);
		terrainAbove.flip(true, true); //Le damos la vuelta a la de arriba
//		bgRegion = new TextureRegion(background, 800, 480);
//		backgroundSprite = new Sprite(background);
//		backgroundSprite.setPosition(0, 0);
		terrainOffset = 0;

		// Animación del avión
//		plane = new Animation(0.05f, new TextureRegion(new Texture("planeRed1.png")), new TextureRegion(new Texture("planeRed2.png")));
		plane = new Animation(0.05f,
				atlas.findRegion("planeRed1"),
				atlas.findRegion("planeRed2"),
				atlas.findRegion("planeRed3"),
				atlas.findRegion("planeRed2"));
		plane.setPlayMode(Animation.PlayMode.LOOP);

		// Movimiento del avión
		planeVelocity = new Vector2();
		planePosition = new Vector2();
		planeDefaultPosition = new Vector2();
		gravity = new Vector2();

		resetScene();

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

		float deltaTime = Gdx.graphics.getDeltaTime();
		terrainOffset -= 200*deltaTime;
		planeAnimTime += deltaTime;

		// Aplicamos gravedad al avión
		planeVelocity.scl(damping);
		planeVelocity.add(gravity);
		planePosition.mulAdd(planeVelocity, deltaTime);
		planePosition.x = planeDefaultPosition.x;

		if (terrainOffset * -1 > terrainBelow.getRegionWidth()){
			terrainOffset = 0;
		}
		if (terrainOffset > 0){
			terrainOffset -= terrainBelow.getRegionWidth();
		}

	}

	public void drawScene(){

		//Preparo la cámara
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		//Dibujo
		batch.begin();
		batch.disableBlending();
		batch.draw(background, 0, 0);
//		backgroundSprite.draw(batch);
		batch.enableBlending();

		// Dibujamos el terreno de abajo
		batch.draw(terrainBelow, terrainOffset, 0);
		batch.draw(terrainBelow, terrainOffset + terrainBelow.getRegionWidth(), 0); //Movemos la textura

		// Dibujamos el terreno de arriba
		batch.draw(terrainAbove, terrainOffset, 480 - terrainAbove.getRegionHeight()); // Metemos la textura arriba
		batch.draw(terrainAbove, terrainOffset + terrainAbove.getRegionWidth(), 480 - terrainAbove.getRegionHeight()); //Movemos la textura

		// Dibujamos la animación del avión
		batch.draw(plane.getKeyFrame(planeAnimTime), planePosition.x, planePosition.y);

		batch.end();

	}

	/**
	 * Método para reiniciar la escena
	 */
	private void resetScene(){

		terrainOffset = 0;
		planeAnimTime = 0;
		planeVelocity.set(0, 0);
		gravity.set(0, -2);
		planeDefaultPosition.set(400 - 88/2, 240 - 73/2); // El avión mide 88 * 73
		planePosition.set(planeDefaultPosition.x, planeDefaultPosition.y);

	}
}
