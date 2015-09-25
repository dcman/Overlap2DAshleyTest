package com.fancylancy.ashleytest;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fancylancy.ashleytest.scripts.PlayerScript;
import com.uwsoft.editor.renderer.resources.IResourceRetriever;
import com.uwsoft.editor.renderer.scene2d.CompositeActor;


/**
 * Created by SuckIt on 9/20/15.
 */
public class uiHud extends Stage {
    private final String tag = this.getClass().getSimpleName();
    PlayerScript playerScript;
    Batch batch;
    Label score;
    CompositeActor scoreActor;
//    Texture tex;
//    float x, y;
    Viewport viewport;

    @Override
    public void act(float delta) {
        super.act(delta);
//        batch.begin();
//        batch.draw(tex, x, y);
//        batch.end();
        score.setText(Store.getInstance().points.toString());
    }

    public uiHud(Viewport viewport, Batch batch, PlayerScript playerScript, IResourceRetriever ir) {
        super(viewport, batch);

        this.playerScript = playerScript;
        this.batch = batch;

//        tex = new Texture("star.png");
//        x = 0;
//        y = 0;
        this.viewport = viewport;
        scoreActor = Store.getInstance().createLabe(ir);
        init();
    }

    private void init() {

        addActor(scoreActor);

        scoreActor.setX(getWidth() / 2 - scoreActor.getWidth() / 2);
        scoreActor.setY(getHeight() - scoreActor.getHeight());
        score = (Label) scoreActor.getItemsByLayer("Default").first();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 unProject = viewport.unproject(new Vector3(screenX, screenY, 0));
        playerScript.applyForce(unProject.x);
//        x = unproject.x;
//        y = unproject.y;
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
