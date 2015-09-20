package com.fancylancy.ashleytest;


import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.fancylancy.ashleytest.scripts.PlayerScript;
import com.uwsoft.editor.renderer.utils.ItemWrapper;
import com.uwsoft.editor.renderer.SceneLoader;


public class AshleyTest extends ApplicationAdapter {
    private Box2DDebugRenderer renderer;
    private World world;
    private Engine engine;
    private SceneLoader sceneLoader;
    private ItemWrapper root, player;
    private float delta;
    private FitViewport viewport;
    private Store store;

    @Override
	public void create () {
        store = Store.getInstance();
        System.out.println(store);
        sceneLoader = store.sceneLoader;
        System.out.println(sceneLoader);
        viewport = store.viewport;
        System.out.println(viewport);
        renderer = store.renderer;
        System.out.println(renderer);
        world = sceneLoader.world;
        System.out.println(world);
        engine = store.engine;
        System.out.println(engine);
        root = new ItemWrapper(sceneLoader.getRoot());
        System.out.println(root);
        player = root.getChild("ball");
        System.out.println(player);
        delta = store.delta;
        init();
    }

    private void init() {
        player.addScript(new PlayerScript(world));
        store.createBurst(root);
    }

    @Override
    public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sceneLoader.getEngine().update(Gdx.graphics.getDeltaTime());
        renderer.render(world, viewport.getCamera().combined);
    }
}