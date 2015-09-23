package com.fancylancy.ashleytest.scripts;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.fancylancy.ashleytest.Store;
import com.fancylancy.ashleytest.comps.CirclePhysicsBodyComponent;
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
    private final String tag = this.getClass().getSimpleName();
    private CirclePhysicsBodyComponent circlePhysicsBodyComponent;
    private TransformComponent transformComponent;
    private DimensionsComponent dimensionsComponent;
    private TextureRegionComponent textureRegionComponent;
    private MainItemComponent mainItemComponent;
    private Entity entity;
    private World world;
    private Vector2 position;
    private int width;
    private float scale = Store.physicsScale;
    private Batch batch;


    public PlayerScript(World world) {
        this.world = world;
    }

    @Override
    public void init(Entity entity) {
        this.entity = entity;
        transformComponent = ComponentRetriever.get(entity, TransformComponent.class);
        dimensionsComponent = ComponentRetriever.get(entity, DimensionsComponent.class);
        textureRegionComponent = ComponentRetriever.get(entity, TextureRegionComponent.class);
        mainItemComponent = ComponentRetriever.get(entity, MainItemComponent.class);
        width = textureRegionComponent.region.getRegionWidth();
        position = new Vector2(transformComponent.x,transformComponent.y);
        entity.add(new CirclePhysicsBodyComponent(world, position, width));
        circlePhysicsBodyComponent = entity.getComponent(CirclePhysicsBodyComponent.class);
        batch = Store.getInstance().sceneLoader.getBatch();

    }

    @Override
    public void act(float delta) {
        transformComponent.x = (circlePhysicsBodyComponent.getPositionX() / scale) - width / 2;
        transformComponent.y = (circlePhysicsBodyComponent.getPositionY() / scale) - width / 2;
        batch.begin();
        batch.end();
    }

    @Override
    public void dispose() {

    }

    public void applyForce(float screenX) {
        float temp = 0f;
        if (screenX < 190){
            temp = screenX % 240;
            temp = 240 - temp;
            temp *= 50;
        }
        if (screenX > 290){
            temp = screenX % 240;
            temp = temp * -1;
            temp *= 50;
        }
        Gdx.app.debug(tag, "Screen x " + screenX + " Force " + temp);
        circlePhysicsBodyComponent.body.applyForceToCenter(temp, 5000, true);
    }
}
