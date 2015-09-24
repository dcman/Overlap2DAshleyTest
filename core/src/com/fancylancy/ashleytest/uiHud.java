package com.fancylancy.ashleytest;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fancylancy.ashleytest.scripts.PlayerScript;


/**
 * Created by SuckIt on 9/20/15.
 */
public class uiHud extends Stage {
    private final String tag = this.getClass().getSimpleName();
    PlayerScript playerScript;
    Batch batch;
    Texture tex;
    float x, y;
    Viewport viewport;
    @Override
    public void act(float delta) {
        super.act(delta);
//        batch.begin();
//        batch.draw(tex, x, y);
//        batch.end();
    }

    public uiHud(Viewport viewport, Batch batch, PlayerScript playerScript) {
        super(viewport, batch);
        this.playerScript = playerScript;
        this.batch = batch;
//        tex = new Texture("star.png");
//        x = 0;
//        y = 0;
        this.viewport = viewport;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 unProject = viewport.unproject(new Vector3(screenX,screenY,0));
        playerScript.applyForce(unProject.x);
//        x = unproject.x;
//        y = unproject.y;
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
