package com.mygdx.game.item.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.game.item.Item;
import com.mygdx.game.world.TileType;

public class RawResource extends Resource {
    private TileType rawTileType;
    public RawResource(String name, float weight, String texturePath, TileType tileType, TileType rawTileType, DragAndDrop dragAndDrop) {
        super(name, weight, texturePath, tileType, dragAndDrop);
        this.rawTileType = rawTileType;
        setPosition(500, 500);
    }
    @Override
    public void move() {
        //TODO implement
    }
}
