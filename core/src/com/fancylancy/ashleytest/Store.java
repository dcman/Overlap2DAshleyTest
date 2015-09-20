package com.fancylancy.ashleytest;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.fancylancy.ashleytest.scripts.BusterScript;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.data.CompositeItemVO;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

/**
 * Created by SuckIt on 9/20/15.
 */
public class Store extends AssetManager implements Disposable {
    private static Store ourInstance = new Store();
    public Box2DDebugRenderer renderer;
    public String scene = "MainScene";
    public World world;
    public Engine engine;
    public SceneLoader sceneLoader;
    public ItemWrapper player, buster;
    public float delta;
    public FitViewport viewport;

    public static Store getInstance() {
        return ourInstance;
    }

    private Store() {
        sceneLoader = new SceneLoader();
        viewport = new FitViewport(480,800);
        renderer = new Box2DDebugRenderer();
        sceneLoader.loadScene(scene, viewport);
        engine = sceneLoader.engine;
        delta = Gdx.graphics.getDeltaTime();
    }

    @Override
    public void dispose() {

    }

    public void createBurst(ItemWrapper root) {
        CompositeItemVO vo = sceneLoader.getRm().getProjectVO().libraryItems.get("burster");
        Entity e = sceneLoader.entityFactory.createEntity(root.getEntity(), vo);
        e.getComponent(TransformComponent.class).x = 200;
        e.getComponent(TransformComponent.class).y = 800;
        engine.addEntity(e);
        buster = new ItemWrapper(e);
        buster.addScript(new BusterScript(world));
    }
}
