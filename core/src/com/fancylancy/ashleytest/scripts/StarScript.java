package com.fancylancy.ashleytest.scripts;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.fancylancy.ashleytest.Store;
import com.fancylancy.ashleytest.comps.ImageComponent;
import com.uwsoft.editor.renderer.components.DimensionsComponent;
import com.uwsoft.editor.renderer.components.ParentNodeComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.physics.PhysicsBodyComponent;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

/**
 * Created by SuckIt on 9/20/15.
 */
public class StarScript implements IScript {
    private final String tag = this.getClass().getSimpleName();
    private final World world;
    private Entity entity;
    private TransformComponent transformComponent;
    private DimensionsComponent dimensionsComponent;
    private PhysicsBodyComponent physicsBodyComponent;
    private ParentNodeComponent parentNodeComponent;
    private ImageComponent imageComponent;
    private String atlas, region;
    private Batch batch = Store.getInstance().sceneLoader.getBatch();
    private Vector2 pos;
    private float scale = Store.physicsScale;
    private Engine engine = Store.getInstance().engine;
    private boolean test = true;

    public StarScript(World world) {
        this.world = world;
        atlas = "orig/pack.atlas";
        region = "star";
        imageComponent = new ImageComponent(atlas, region);
        Gdx.app.debug(tag, this.hashCode() + " " + this.getClass());
    }


    @Override
    public void init(Entity entity) {
        this.entity = entity;
        physicsBodyComponent = ComponentRetriever.get(entity, PhysicsBodyComponent.class);
        parentNodeComponent = ComponentRetriever.get(entity,ParentNodeComponent.class);
    }

    @Override
    public void act(float delta) {
        pos = physicsBodyComponent.body.getPosition();
        if(test){
            for (int i = 0; i < physicsBodyComponent.body.getFixtureList().size; i++) {
                physicsBodyComponent.body.getFixtureList().get(i).setSensor(true);
                Fixture fx = physicsBodyComponent.body.getFixtureList().get(i);
                fx.setUserData("Star");
            }
            test = false;
        }
        batch.begin();
        batch.draw(imageComponent.region, pos.x / scale, pos.y / scale);
        batch.end();

        Gdx.app.debug(tag, this.hashCode() + " Star: " + pos.y);

        if (pos.y <= -imageComponent.region.getRegionHeight()){
            engine.removeEntity(entity);
            Gdx.app.debug(tag, this.hashCode() + " Star removed: " + pos.y);
        }
    }

    @Override
    public void dispose() {

    }
}
