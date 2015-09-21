package com.fancylancy.ashleytest.comps;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.fancylancy.ashleytest.Store;

/**
 * Created by SuckIt on 9/15/15.
 */
public class CirclePhysicsBodyComponent implements Component {
    public CircleShape circleShape;
    public FixtureDef fixture;
    public BodyDef bodyDef;
    public World world;
    public Body body;
    public int radius;
    private float scale = Store.physicsScale;


    public CirclePhysicsBodyComponent(World world, Vector2 position, int width) {
        this.world = world;
        this.radius = width / 2;
        circleShape = new CircleShape();
        circleShape.setRadius(radius * scale);
        bodyDef = new BodyDef();
        bodyDef.position.x = (position.x + radius)* scale;
        bodyDef.position.y = (position.y + radius) * scale;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
        fixture = new FixtureDef();
        fixture.shape = circleShape;
        fixture.restitution = 0.00001f;
        fixture.density = 1f;
        fixture.friction = 1f;
        fixture.isSensor = false;
        Fixture fix = body.createFixture(fixture);
        fix.setUserData("Ball");
        circleShape.dispose();
    }
    public float getPositionX(){
        return body.getPosition().x;
    }
    public float getPositionY(){
        return body.getPosition().y;
    }
}

