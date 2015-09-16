package com.fancylancy.ashleytest;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.uwsoft.editor.renderer.data.CompositeItemVO;
import com.uwsoft.editor.renderer.resources.IResourceRetriever;
import com.uwsoft.editor.renderer.scene2d.CompositeActor;


/**
 * Created by SuckIt on 9/14/15.
 */
public class uistage extends Stage {

    public uistage(CompositeItemVO lef, IResourceRetriever ir) {
        System.out.println("UI stage");
        CompositeActor buttonActor = new CompositeActor(lef, ir);
        //addActor(buttonActor);
        buttonActor.setPosition(200, 200);

    }

}
