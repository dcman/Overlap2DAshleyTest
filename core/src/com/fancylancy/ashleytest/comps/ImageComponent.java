package com.fancylancy.ashleytest.comps;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by SuckIt on 9/15/15.
 */
public class ImageComponent implements Component {
    private final String tag = this.getClass().getSimpleName();
    public TextureAtlas atlas;
    public TextureRegion region;

    public ImageComponent(String atl, String reg) {
        atlas = new TextureAtlas(atl);
        region = atlas.findRegion(reg);

    }
}
