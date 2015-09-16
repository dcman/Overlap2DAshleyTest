package com.fancylancy.ashleytest;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Json;
import com.uwsoft.editor.renderer.components.DimensionsComponent;
import com.uwsoft.editor.renderer.components.MainItemComponent;
import com.uwsoft.editor.renderer.components.TextureRegionComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

/**
 * Created by SuckIt on 9/15/15.
 */
public class PlayerScript implements IScript {
    private CirclePhysicsBodyComponent circlePhysicsBodyComponent;
    private TransformComponent transformComponent;
    private DimensionsComponent dimensionsComponent;
    private TextureRegionComponent textureRegionComponent;
    private MainItemComponent mainItemComponent;
    private Entity entity;
    private World world;
    private Texture image;
    private Vector2 position;
    private int width;

    public PlayerScript(World world) {
        this.world = world;
    }

    @Override
    public void init(Entity entity) {
        this.entity = entity;
        transformComponent = ComponentRetriever.get(entity, TransformComponent.class);
        dimensionsComponent = ComponentRetriever.get(entity, DimensionsComponent.class);
        textureRegionComponent = ComponentRetriever.get(entity, TextureRegionComponent.class);
        mainItemComponent = ComponentRetriever.get(entity,MainItemComponent.class);
        width = textureRegionComponent.region.getRegionWidth();
        position = new Vector2(transformComponent.x,transformComponent.y);
        image = textureRegionComponent.region.getTexture();
        entity.add(new CirclePhysicsBodyComponent(world, position, width));
        System.out.println(entity.getComponents());
        circlePhysicsBodyComponent = entity.getComponent(CirclePhysicsBodyComponent.class);
        //mainItemComponent.visible = false;

    }

    @Override
    public void act(float delta) {
        transformComponent.x = (circlePhysicsBodyComponent.getPositionX() / 0.05f) - width / 2;
        transformComponent.y = (circlePhysicsBodyComponent.getPositionY() / 0.05f) - width / 2;
    }

    @Override
    public void dispose() {

    }
}
