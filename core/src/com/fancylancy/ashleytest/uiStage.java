package com.fancylancy.ashleytest;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;


/**
 * Created by SuckIt on 9/20/15.
 */
public class uiStage extends Stage {
    public uiStage(Viewport viewport, Batch batch) {
        super(viewport, batch);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println(screenX + " " + screenY);
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
