package com.fancylancy.ashleytest;

import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.ashley.core.Engine;

/**
 * Created by SuckIt on 9/20/15.
 */
public class Contact implements ContactListener {
    private Engine engine;
    public Contact(Engine engine) {
        this.engine = engine;
        System.out.println(engine.getEntities());
    }

    @Override
    public void beginContact(com.badlogic.gdx.physics.box2d.Contact contact) {
        if (contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("Ball")){
            if (contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("Star")){
                engine.removeEntity(Store.getInstance().star.getEntity());
            }
            if (contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("Star")){
                engine.removeEntity(Store.getInstance().star.getEntity());
            }
            if (contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("Buster")){
                engine.removeEntity(Store.getInstance().buster.getEntity());
            }
            if (contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("Buster")){
                engine.removeEntity(Store.getInstance().buster.getEntity());
            }
        }

        if (contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("Ball")){
            if (contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("Star")){
                engine.removeEntity(Store.getInstance().star.getEntity());
            }
            if (contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("Star")){
                engine.removeEntity(Store.getInstance().star.getEntity());
            }
            if (contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("Buster")){
                engine.removeEntity(Store.getInstance().buster.getEntity());
            }
            if (contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("Buster")){
                engine.removeEntity(Store.getInstance().buster.getEntity());
            }
        }


    }

    @Override
    public void endContact(com.badlogic.gdx.physics.box2d.Contact contact) {

    }

    @Override
    public void preSolve(com.badlogic.gdx.physics.box2d.Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(com.badlogic.gdx.physics.box2d.Contact contact, ContactImpulse impulse) {

    }
}
