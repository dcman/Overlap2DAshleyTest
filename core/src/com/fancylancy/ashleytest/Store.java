package com.fancylancy.ashleytest;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.fancylancy.ashleytest.scripts.BusterScript;
import com.fancylancy.ashleytest.scripts.StarScript;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.data.CompositeItemVO;
import com.uwsoft.editor.renderer.data.ProjectInfoVO;
import com.uwsoft.editor.renderer.resources.IResourceRetriever;
import com.uwsoft.editor.renderer.scene2d.CompositeActor;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

/**
 * Created by SuckIt on 9/20/15.
 */
public class Store extends AssetManager implements Disposable {
    private final String tag = this.getClass().getSimpleName();
    private static Store ourInstance = new Store();
    public static float physicsScale = 0.05f;
    public Box2DDebugRenderer renderer;
    public String scene = "MainScene";
    public World world;
    public Engine engine;
    public SceneLoader sceneLoader;
    public ItemWrapper player, buster, star;
    public FitViewport viewport;
    public ItemWrapper root;
    public Integer points;
    public Sound pop, swoosh;


    public static Store getInstance() {
        return ourInstance;
    }

    private Store() {
        sceneLoader = new SceneLoader();
        viewport = new FitViewport(480, 800);
        renderer = new Box2DDebugRenderer();
        sceneLoader.loadScene(scene, viewport);
        engine = sceneLoader.engine;
        points = 0;
        pop = Gdx.audio.newSound(Gdx.files.internal("pop.ogg"));
        swoosh = Gdx.audio.newSound(Gdx.files.internal("swoosh.ogg"));
    }

    public void createBurst(ItemWrapper root) {
        CompositeItemVO vo = sceneLoader.getRm().getProjectVO().libraryItems.get("burster");
        Entity e = sceneLoader.entityFactory.createEntity(root.getEntity(), vo);
        e.getComponent(TransformComponent.class).x = MathUtils.random(10, 395);
        e.getComponent(TransformComponent.class).y = 800;
        engine.addEntity(e);
        buster = new ItemWrapper(e);
        buster.addScript(new BusterScript(world));
    }

    public void createStar(ItemWrapper root) {
        CompositeItemVO vo = sceneLoader.getRm().getProjectVO().libraryItems.get("star");
        Entity e = sceneLoader.entityFactory.createEntity(root.getEntity(), vo);
        e.getComponent(TransformComponent.class).x = MathUtils.random(10, 395);
        e.getComponent(TransformComponent.class).y = 800;
        engine.addEntity(e);
        star = new ItemWrapper(e);
        star.addScript(new StarScript());
    }

    public CompositeActor createLabe(IResourceRetriever ir) {
        ProjectInfoVO projectInfo = ir.getProjectVO();
        CompositeItemVO labelData = projectInfo.libraryItems.get("TextBox");
        CompositeActor scoreActor = new CompositeActor(labelData, ir);
        return scoreActor;
    }

    public void add(ItemWrapper root) {
        int temp = MathUtils.random(0, 2);
        if (temp == 0) {
            createBurst(root);
        } else {
            createStar(root);
        }
    }

    @Override
    public void dispose() {

    }
}
