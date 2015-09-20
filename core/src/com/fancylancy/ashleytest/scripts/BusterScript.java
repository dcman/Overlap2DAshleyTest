package com.fancylancy.ashleytest.scripts;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Json;
import com.fancylancy.ashleytest.comps.ImageComponent;
import com.uwsoft.editor.renderer.components.*;
import com.uwsoft.editor.renderer.components.physics.PhysicsBodyComponent;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

/**
 * Created by SuckIt on 9/15/15.
 */
public class BusterScript implements IScript {
    private final World world;
    private Entity entity;
    private TransformComponent transformComponent;
    private DimensionsComponent dimensionsComponent;
    private PhysicsBodyComponent physicsBodyComponent;
    private ParentNodeComponent parentNodeComponent;
    private ImageComponent imageComponent;
    private String atlas, region;
    private SpriteBatch batch = new SpriteBatch();
    private Vector2 pos;
    private boolean test = true;

    public BusterScript(World world) {
        this.world = world;
        atlas = "orig/pack.atlas";
        region = "exp";
        imageComponent = new ImageComponent(atlas, region);

    }


    @Override
    public void init(Entity entity) {
        this.entity = entity;
        physicsBodyComponent = ComponentRetriever.get(entity,PhysicsBodyComponent.class);
        parentNodeComponent = ComponentRetriever.get(entity,ParentNodeComponent.class);
        System.out.println(parentNodeComponent.parentEntity.getComponents());
    }

    @Override
    public void act(float delta) {
        pos = physicsBodyComponent.body.getPosition();
        if(test){
            Json j = new Json();
            for (int i = 0; i < physicsBodyComponent.body.getFixtureList().size; i++) {
                physicsBodyComponent.body.getFixtureList().get(i).setSensor(true);

            }
            test = false;
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