package com.fancylancy.ashleytest;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Created by SuckIt on 9/20/15.
 */
public class Contact implements ContactListener {
    private final String tag = this.getClass().getSimpleName();
    private Engine engine;

    public Contact(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void beginContact(com.badlogic.gdx.physics.box2d.Contact contact) {
        if (contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("Ball")) {
            if (contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("Star")) {
                Store.getInstance().points++;
                Gdx.app.debug(tag,Store.getInstance().points.toString());
                engine.removeEntity(Store.getInstance().star.getEntity());
                Store.getInstance().pop.play();
            }
            if (contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("Star")) {
                Store.getInstance().points++;
                Gdx.app.debug(tag, Store.getInstance().points.toString());
                engine.removeEntity(Store.getInstance().star.getEntity());
                Store.getInstance().pop.play();
            }
            if (contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("Buster")) {
                engine.removeEntity(Store.getInstance().buster.getEntity());
            }
            if (contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("Buster")) {
                engine.removeEntity(Store.getInstance().buster.getEntity());
            }
        }

        if (contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("Ball")) {
            if (contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("Star")) {
                Store.getInstance().points++;
                Gdx.app.debug(tag, Store.getInstance().points.toString());
                engine.removeEntity(Store.getInstance().star.getEntity());
                Store.getInstance().pop.play();
            }
            if (contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("Star")) {
                Store.getInstance().points++;
                Gdx.app.debug(tag, Store.getInstance().points.toString());
                engine.removeEntity(Store.getInstance().star.getEntity());
                Store.getInstance().pop.play();
            }
            if (contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("Buster")) {
                engine.removeEntity(Store.getInstance().buster.getEntity());
            }
            if (contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("Buster")) {
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
