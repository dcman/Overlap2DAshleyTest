package com.fancylancy.ashleytest;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Json;
import com.uwsoft.editor.renderer.components.DimensionsComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;
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
    private ImageComponent imageComponent;
    private TextureAtlas a = new TextureAtlas("orig/pack.atlas");
    private TextureRegion r = a.findRegion("exp");
    private SpriteBatch batch = new SpriteBatch();
    private Vector2 pos;
    private boolean test = true;

    public BusterScript(World world) {
        this.world = world;
        imageComponent = new ImageComponent();

    }

    @Override
    public void init(Entity entity) {
        this.entity = entity;
        physicsBodyComponent = ComponentRetriever.get(entity,PhysicsBodyComponent.class);
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
        batch.begin();
        batch.draw(r,pos.x / 0.05f,pos.y / 0.05f);
        batch.end();

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
        */
