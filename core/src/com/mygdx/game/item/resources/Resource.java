package com.mygdx.game.item.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.game.item.Item;
import com.mygdx.game.world.TileType;

public abstract class Resource extends Item {

    public Resource(String name, float weight, String texturePath, TileType tileType, DragAndDrop dragAndDrop) {
        super(name, weight, texturePath, tileType, dragAndDrop);
    }
    public abstract void move();
}
