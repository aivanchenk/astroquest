package com.mygdx.game.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class InventoryBackground extends Actor {

    private TextureRegion textureRegion;

    public InventoryBackground() {
        Texture texture = new Texture(Gdx.files.internal("inventoryBackground.png"));
        this.textureRegion = new TextureRegion(texture);
        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        setPosition((float) Gdx.graphics.getWidth() / 2 - getWidth() / 2, (float) Gdx.graphics.getHeight() / 2 - getHeight() / 2);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}
