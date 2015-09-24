package com.fancylancy.ashleytest;


import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Application;
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
    private final String tag = this.getClass().getSimpleName();
    private Box2DDebugRenderer renderer;
    private World world;
    private Engine engine;
    private SceneLoader sceneLoader;
    private ItemWrapper root, player;
    private FitViewport viewport;
    private Store store;
    private float timeState;
    private FPSLogger fpsLogger;
    private uiHud uiHud;
    private PlayerScript playerScript;

    @Override
	public void create () {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        fpsLogger = new FPSLogger();
        store = Store.getInstance();
        sceneLoader = store.sceneLoader;
        viewport = store.viewport;
        renderer = store.renderer;
        world = sceneLoader.world;
        engine = store.engine;
        root = new ItemWrapper(sceneLoader.getRoot());
        Store.getInstance().root = root;
        player = root.getChild("ball");
        store.player = player;
        world.setContactListener(new Contact(engine));
        playerScript = new PlayerScript(world);
        uiHud = new uiHud(viewport, sceneLoader.getBatch(), playerScript);
        init();
    }

    private void init() {
        Gdx.input.setInputProcessor(uiHud);
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

        uiHud.act();
        uiHud.draw();
    }
}