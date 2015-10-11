package com.fancylancy.ashleytest.scripts;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Json;
import com.fancylancy.ashleytest.Store;
import com.fancylancy.ashleytest.comps.ImageComponent;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.MainItemComponent;
import com.uwsoft.editor.renderer.components.ZIndexComponent;
import com.uwsoft.editor.renderer.components.physics.PhysicsBodyComponent;
import com.uwsoft.editor.renderer.data.MainItemVO;
import com.uwsoft.editor.renderer.data.SimpleImageVO;
import com.uwsoft.editor.renderer.factory.component.SimpleImageComponentFactory;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

import java.io.File;

/**
 * Created by Justin Johnson on 9/20/15.
 * Work with the Star
 */
public class StarScript implements IScript {
    private final String tag = this.getClass().getSimpleName();
    private Entity entity;
    private PhysicsBodyComponent physicsBodyComponent;
    private ImageComponent imageComponent;
    private String atlas, region;
    private Batch batch = Store.getInstance().sceneLoader.getBatch();
    private Vector2 pos;
    private float scale = Store.physicsScale;
    private Engine engine = Store.getInstance().engine;
    private boolean fixPhysicsBodies = true;

    public StarScript() {
        atlas = "orig/pack.atlas";
        region = "star";
        imageComponent = new ImageComponent(atlas, region);//TODO not really a component
        Gdx.app.debug(tag, this.hashCode() + " " + this.getClass());
    }


    @Override
    public void init(Entity entity) {
        this.entity = entity;
        physicsBodyComponent = ComponentRetriever.get(entity, PhysicsBodyComponent.class);
        System.out.println(entity.getId());
    }

    @Override
    public void act(float delta) {
        pos = physicsBodyComponent.body.getPosition();//Get the location of the Physics body
        if(fixPhysicsBodies){//Hack physicsBodyComponent.body needs time before you can access it
            for (int i = 0; i < physicsBodyComponent.body.getFixtureList().size; i++) {
                //Convert bodies into sensors so they don't interact with other bodies
                physicsBodyComponent.body.getFixtureList().get(i).setSensor(true);
                Fixture fx = physicsBodyComponent.body.getFixtureList().get(i);
                fx.setUserData("Star," + entity.getId());//Used later in hit detection
            }
            fixPhysicsBodies = false;//Only loop once
        }
        batch.begin();//Hack CompositeItemVO have broken TextureRegionComponent
        batch.draw(imageComponent.region, pos.x / scale, pos.y / scale);
        batch.end();

        Gdx.app.debug(tag, this.hashCode() + " Star: " + pos.y);

        if (pos.y <= -imageComponent.region.getRegionHeight()){//Remove the Entity when it is out of view
            engine.removeEntity(entity);
            Gdx.app.debug(tag, this.hashCode() + " Star removed: " + pos.y);
        }
    }

    @Override
    public void dispose() {

    }
}
/*
			uniqueId: 5
			itemIdentifier: ball
			tags: []
			x: 188
			y: 434
			originX: 43.5
			originY: 43
			zIndex: 3
			layerName: Default
			imageName: sun

					uniqueId: 6
					tags: []
					originX: 36
					originY: 35.5
					layerName: Default
					imageName: exp
 */