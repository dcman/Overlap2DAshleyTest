package com.fancylancy.ashleytest.scripts;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
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
 * Created by Justin Johnson on 9/15/15.
 * Script to modify the Player for this game.
 */
public class PlayerScript implements IScript { 
    private final String tag = this.getClass().getSimpleName();
    private CirclePhysicsBodyComponent circlePhysicsBodyComponent;
    private TransformComponent transformComponent;
    private DimensionsComponent dimensionsComponent;
    private TextureRegionComponent textureRegionComponent;
    private MainItemComponent mainItemComponent;
    private World world;
    private Vector2 position;
    private int width;
    private float scale = Store.physicsScale;

    public PlayerScript(World world) {
        this.world = world;
    }

    @Override
    public void init(Entity entity) {
        transformComponent = ComponentRetriever.get(entity, TransformComponent.class);
        dimensionsComponent = ComponentRetriever.get(entity, DimensionsComponent.class);
        textureRegionComponent = ComponentRetriever.get(entity, TextureRegionComponent.class);
        mainItemComponent = ComponentRetriever.get(entity, MainItemComponent.class);
        width = textureRegionComponent.region.getRegionWidth();
        position = new Vector2(transformComponent.x,transformComponent.y);
        //Create and add a circle physics body to the Player entity
        entity.add(new CirclePhysicsBodyComponent(world, position, width));
        circlePhysicsBodyComponent = entity.getComponent(CirclePhysicsBodyComponent.class);
    }

    @Override
    public void act(float delta) {
        //Have the Player image fallow the physics body
        transformComponent.x = (circlePhysicsBodyComponent.getPositionX() / scale) - width / 2;
        transformComponent.y = (circlePhysicsBodyComponent.getPositionY() / scale) - width / 2;
    }

    public void applyForce(float screenX) {
        float temp = 0f;
        if (screenX < 190){// If left side of the screen is touched add force from the lower right
            temp = screenX % 240;
            temp = 240 - temp;//force is larger the closer to the outside edge of the screen
            temp *= 50;
        }
        if (screenX > 290){//If right side of the screen is touched add force to the lower left
            temp = screenX % 240;
            temp = temp * -1;//force is larger the closer to the outside edge of the screen
            temp *= 50;
        }
        Gdx.app.debug(tag, "Screen x " + screenX + " Force " + temp);
        //Temp is the right/left determined above 5000 is a constant upward force
        circlePhysicsBodyComponent.body.applyForceToCenter(temp, 5000, true);
    }

    @Override
    public void dispose() {

    }
}
