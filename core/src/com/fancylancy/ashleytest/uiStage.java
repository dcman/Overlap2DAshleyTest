package com.fancylancy.ashleytest;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fancylancy.ashleytest.scripts.PlayerScript;


/**
 * Created by SuckIt on 9/20/15.
 */
public class uiStage extends Stage {
    PlayerScript playerScript;
    public uiStage(Viewport viewport, Batch batch, PlayerScript playerScript) {
        super(viewport, batch);
        this.playerScript = playerScript;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        playerScript.applyForce(screenX);
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
