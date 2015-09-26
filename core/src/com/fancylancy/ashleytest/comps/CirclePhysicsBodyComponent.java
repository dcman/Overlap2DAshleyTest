package com.fancylancy.ashleytest.comps;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.fancylancy.ashleytest.Store;

/**
 * Created by Justin Johnson on 9/15/15.
 * Custom circle physics body because over lap can only work with polygons
 */
public class CirclePhysicsBodyComponent implements Component {
    private final String tag = this.getClass().getSimpleName();
    public CircleShape circleShape;
    public FixtureDef fixture;
    public BodyDef bodyDef;
    public World world;
    public Body body;
    public int radius;

    public CirclePhysicsBodyComponent(World world, Vector2 position, int width) {
        this.world = world;
        this.radius = width / 2;
        circleShape = new CircleShape();
        float scale = Store.physicsScale;
        circleShape.setRadius(radius * scale);
        bodyDef = new BodyDef();
        bodyDef.position.x = (position.x + radius) * scale;
        bodyDef.position.y = (position.y + radius) * scale;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
        fixture = new FixtureDef();
        fixture.shape = circleShape;
        fixture.restitution = 0.2f;
        fixture.density = 1f;
        fixture.friction = 0.1f;
        fixture.isSensor = false;
        Fixture fix = body.createFixture(fixture);
        fix.setUserData("Ball");//Used later for hit detection
        circleShape.dispose();
    }
    public float getPositionX(){
        return body.getPosition().x;
    }
    public float getPositionY(){
        return body.getPosition().y;
    }
}

