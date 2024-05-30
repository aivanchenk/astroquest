package com.mygdx.game.item.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.game.world.TileType;

public class ComplexResource extends Resource {
    public ComplexResource(String name, float weight, String texturePath, TileType tileType, DragAndDrop dragAndDrop) {
        super(name, weight, texturePath, tileType, dragAndDrop);
    }
    @Override
    public void move() {
        //TODO implement
    }
}
