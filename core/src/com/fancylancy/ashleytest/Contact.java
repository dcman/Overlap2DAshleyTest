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
    private String[] strings;
    private String a, b;

    public Contact(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void beginContact(com.badlogic.gdx.physics.box2d.Contact contact) {
        if (contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().toString().equals("Ball")) {
            if (contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().toString().contains("Star")) {
                a = contact.getFixtureA().getUserData().toString();
                strings = a.split(",");
                Store.getInstance().removeStar(strings[strings.length -1]);
                Store.getInstance().points++;
                Gdx.app.debug(tag,Store.getInstance().points.toString());
                //engine.removeEntity(Store.getInstance().star.getEntity());
                Store.getInstance().pop.play();
            }
            if (contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().toString().contains("Star")) {
                b = contact.getFixtureB().getUserData().toString();
                strings = b.split(",");
                Store.getInstance().removeStar(strings[strings.length -1]);
                Store.getInstance().points++;
                Gdx.app.debug(tag, Store.getInstance().points.toString());
               // engine.removeEntity(Store.getInstance().star.getEntity());
                Store.getInstance().pop.play();
            }
            if (contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().toString().contains("Buster")) {
                a = contact.getFixtureA().getUserData().toString();
                strings = a.split(",");
                Store.getInstance().removeBuster(strings[strings.length -1]);
                //engine.removeEntity(Store.getInstance().buster.getEntity());
                Store.getInstance().end.play();
            }
            if (contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().toString().contains("Buster")) {
                b = contact.getFixtureB().getUserData().toString();
                strings = b.split(",");
                Store.getInstance().removeBuster(strings[strings.length -1]);
                //engine.removeEntity(Store.getInstance().buster.getEntity());
                Store.getInstance().end.play();
            }
        }

        if (contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().toString().equals("Ball")) {
            if (contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().toString().contains("Star")) {
                a = contact.getFixtureA().getUserData().toString();
                strings = a.split(",");
                Store.getInstance().removeStar(strings[strings.length -1]);
                Store.getInstance().points++;
                Gdx.app.debug(tag, Store.getInstance().points.toString());
                //engine.removeEntity(Store.getInstance().star.getEntity());
                Store.getInstance().pop.play();
            }
            if (contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().toString().contains("Star")) {
                b = contact.getFixtureB().getUserData().toString();
                strings = b.split(",");
                Store.getInstance().removeStar(strings[strings.length -1]);
                Store.getInstance().points++;
                Gdx.app.debug(tag, Store.getInstance().points.toString());
                //engine.removeEntity(Store.getInstance().star.getEntity());
                Store.getInstance().pop.play();
            }
            if (contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().toString().contains("Buster")) {
                a = contact.getFixtureA().getUserData().toString();
                strings = a.split(",");
                Store.getInstance().removeBuster(strings[strings.length -1]);
                //engine.removeEntity(Store.getInstance().buster.getEntity());
                Store.getInstance().end.play();
            }
            if (contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().toString().contains("Buster")) {
                b = contact.getFixtureB().getUserData().toString();
                strings = b.split(",");
                Store.getInstance().removeBuster(strings[strings.length -1]);
                //engine.removeEntity(Store.getInstance().buster.getEntity());
                Store.getInstance().end.play();
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
