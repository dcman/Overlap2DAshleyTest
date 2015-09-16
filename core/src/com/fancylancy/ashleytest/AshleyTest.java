package com.fancylancy.ashleytest;


import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.utils.ItemWrapper;


public class AshleyTest extends ApplicationAdapter {

    private SceneLoader sceneLoader;
    private Viewport viewport;
    private Box2DDebugRenderer renderer;
    private World world;
    private Engine engine;
    private ItemWrapper root, player, lef;
    private float delta;
    private uistage uistage;

    @Override
	public void create () {
        viewport = new FitViewport(480, 800);
        sceneLoader = new SceneLoader();
        sceneLoader.loadScene("MainScene", viewport);
        renderer = new Box2DDebugRenderer();
        world = sceneLoader.world;
        world.setGravity(new Vector2(0f,-9.8f));
        engine = sceneLoader.engine;
        root = new ItemWrapper(sceneLoader.getRoot());
        player = root.getChild("ball");
        sceneLoader.getRm();
        delta = Gdx.graphics.getDeltaTime();
        init();
    }

    private void init() {
        player.addScript(new PlayerScript(world));
        createLef();
    }

    private void createLef(){
        //TODO clean this up
        Entity e = sceneLoader.entityFactory.createEntity(sceneLoader.rootEntity, sceneLoader.getRm().getProjectVO().libraryItems.get("lef"));
        e.getComponent(TransformComponent.class).x = 200;
        e.getComponent(TransformComponent.class).y = 200;
        engine.addEntity(e);
        lef = new ItemWrapper(e);
        lef.addScript(new LefScript(world));
        //Json j = new Json();
        //System.out.println(j.prettyPrint(j.toJson(sceneLoader.getRm().getProjectVO().libraryItems.get("lef"))));
    }

    @Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sceneLoader.getEngine().update(Gdx.graphics.getDeltaTime());
        renderer.render(world, viewport.getCamera().combined);
        world.step(1 / 60f, 8, 3);
        engine.update(delta);
    }
}