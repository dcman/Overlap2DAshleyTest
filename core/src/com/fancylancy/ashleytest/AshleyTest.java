package com.fancylancy.ashleytest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.brashmonkey.spriter.Player;
import com.uwsoft.editor.renderer.SceneLoader;

public class AshleyTest extends ApplicationAdapter {

    private SceneLoader sceneLoader;
    private Viewport viewport;

	@Override
	public void create () {
        viewport = new FitViewport(480, 800);
        sceneLoader = new SceneLoader();
        sceneLoader.loadScene("MainScene", viewport);

        

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sceneLoader.getEngine().update(Gdx.graphics.getDeltaTime());

	}
}
