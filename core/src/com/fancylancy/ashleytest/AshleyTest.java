package com.fancylancy.ashleytest;


import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
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
    private FitViewport viewport;
    private Store store;
    private float timeState;
    private FPSLogger fpsLogger;
    private uiStage uiStage;
    private PlayerScript playerScript;

    @Override
	public void create () {
        fpsLogger = new FPSLogger();
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
        Store.getInstance().root = root;
        System.out.println(root);
        player = root.getChild("ball");
        System.out.println(player);
        store.player = player;
        world.setContactListener(new Contact(engine));
        playerScript = new PlayerScript(world);
        uiStage = new uiStage(viewport, sceneLoader.getBatch(), playerScript);
        init();
    }

    private void init() {
        Gdx.input.setInputProcessor(uiStage);
        player.addScript(playerScript);
    }
    public void addRandom(){
        timeState+=Gdx.graphics.getDeltaTime();
        if(timeState>=3f){
            store.add(root);
            timeState=0f; // reset our timer
        }

    }
    @Override
    public void render () {
        fpsLogger.log();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sceneLoader.getEngine().update(Gdx.graphics.getDeltaTime());
        renderer.render(world, viewport.getCamera().combined);

        addRandom();

        uiStage.act();
        uiStage.draw();
    }
}