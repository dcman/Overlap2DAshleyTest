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
import com.uwsoft.editor.renderer.components.*;
import com.uwsoft.editor.renderer.components.physics.PhysicsBodyComponent;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;


/**
 * Created by SuckIt on 9/15/15.
 */
public class BusterScript implements IScript {
    private final String tag = this.getClass().getSimpleName();
    private Entity entity;
    private PhysicsBodyComponent physicsBodyComponent;
    private ParentNodeComponent parentNodeComponent;
    private ImageComponent imageComponent;
    private String atlas, region;
    private Batch batch = Store.getInstance().sceneLoader.getBatch();
    private Vector2 pos;
    private float scale = Store.physicsScale;
    private Engine engine = Store.getInstance().engine;
    private boolean fixPhysicsBodies = true;

    public BusterScript() {
        atlas = "orig/pack.atlas";
        region = "exp";
        imageComponent = new ImageComponent(atlas, region);//TODO not really a component
        Gdx.app.debug(tag, this.hashCode() + " Im a Buster " + this.getClass());
    }


    @Override
    public void init(Entity entity) {
        this.entity = entity;
        physicsBodyComponent = ComponentRetriever.get(entity,PhysicsBodyComponent.class);
        parentNodeComponent = ComponentRetriever.get(entity,ParentNodeComponent.class);
    }

    @Override
    public void act(float delta) {
        pos = physicsBodyComponent.body.getPosition();//Get the location of the Physics body
        if(fixPhysicsBodies){//Hack physicsBodyComponent.body needs time before you can access it
            for (int i = 0; i < physicsBodyComponent.body.getFixtureList().size; i++) {
                //Convert bodies into sensors so they don't interact with other bodies
                physicsBodyComponent.body.getFixtureList().get(i).setSensor(true);
                Fixture fx = physicsBodyComponent.body.getFixtureList().get(i);
                fx.setUserData("Buster");//Used later in hit detection
            }
            fixPhysicsBodies = false;//Only loop once
        }
        batch.begin();//Hack CompositeItemVO have broken TextureRegionComponent
        batch.draw(imageComponent.region, pos.x / scale, pos.y / scale);
        batch.end();

        Gdx.app.debug(tag, this.hashCode() + " Buster: " + pos.y);

        if (pos.y <= -imageComponent.region.getRegionHeight()){//Remove the Entity when it is out of view
            engine.removeEntity(entity);
            Gdx.app.debug(tag, this.hashCode() + " Buster removed: " + pos.y);
        }
    }

    @Override
    public void dispose() {

    }
}
/*

[com.uwsoft.editor.renderer.components.DimensionsComponent@b2d01a,
        com.uwsoft.editor.renderer.components.MainItemComponent@6c85bfa4,
        com.uwsoft.editor.renderer.components.TransformComponent@437992f7,
        com.uwsoft.editor.renderer.components.TintComponent@4f6e7d66,
        com.uwsoft.editor.renderer.components.ZIndexComponent@594a5691,
        com.uwsoft.editor.renderer.components.ScriptComponent@1873aa83,
        com.uwsoft.editor.renderer.components.PolygonComponent@17127d50,
        com.uwsoft.editor.renderer.components.NodeComponent@2b2bb799,
        com.uwsoft.editor.renderer.components.ParentNodeComponent@205e82b3,
        com.uwsoft.editor.renderer.components.physics.PhysicsBodyComponent@257c1389,
        com.uwsoft.editor.renderer.components.CompositeTransformComponent@2100128b,
        com.uwsoft.editor.renderer.components.LayerMapComponent@5761ee4d]

com.uwsoft.editor.renderer.components.DimensionsComponent@32473584,
        com.uwsoft.editor.renderer.components.MainItemComponent@5b156ed6,
        com.uwsoft.editor.renderer.components.LayerMapComponent@133832f3,
        com.uwsoft.editor.renderer.components.TintComponent@42799375,
        com.uwsoft.editor.renderer.components.ZIndexComponent@491975ab,
        com.uwsoft.editor.renderer.components.ScriptComponent@390ac180,
        com.uwsoft.editor.renderer.components.NodeComponent@6bf7c3f8,
        com.uwsoft.editor.renderer.components.CompositeTransformComponent@651b17c8,
        com.uwsoft.editor.renderer.components.TransformComponent@672d82ed,
        com.uwsoft.editor.renderer.components.ViewPortComponent@8445949]
        */