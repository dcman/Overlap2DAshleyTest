package com.fancylancy.ashleytest;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

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


    public CirclePhysicsBodyComponent(World world, Vector2 position, int width) {
        this.world = world;
        this.radius = width / 2;
        circleShape = new CircleShape();
        circleShape.setRadius(radius * 0.05f);
        bodyDef = new BodyDef();
        bodyDef.position.x = (position.x + radius)* 0.05f;
        bodyDef.position.y = (position.y + radius) * 0.05f;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
        fixture = new FixtureDef();
        fixture.isSensor = false;
        fixture.shape = circleShape;
        fixture.density = 1;
        fixture.restitution = 1;
        body.createFixture(fixture);
        circleShape.dispose();
    }
    public float getPositionX(){
        return body.getPosition().x;
    }
    public float getPositionY(){
        return body.getPosition().y;
    }
}

