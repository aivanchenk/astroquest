package com.mygdx.game.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.game.item.Item;

public class InventorySlot extends Actor {

    private TextureRegion texture;
    private Item item;
    private DragAndDrop dragAndDrop;


    public InventorySlot(Vector2 position, DragAndDrop dragAndDrop) {
        Texture texture = new Texture(Gdx.files.internal("inventorySlot.png"));
        this.texture = new TextureRegion(texture);

        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        setPosition(position.x, position.y);

        this.dragAndDrop = dragAndDrop;
        initializeDragAndDrop();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    public void add(Item actor) {
        this.item = actor;
        actor.setInventoryPosition(this);
        actor.setPosition(getX() + getWidth() / 2 - actor.getWidth() / 2, getY() + getHeight() / 2 - actor.getHeight() / 2);
    }

    private void initializeDragAndDrop() {
        dragAndDrop.addTarget(new DragAndDrop.Target(this) {
            @Override
            public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                return true;
            }

            @Override
            public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                Actor actor = payload.getDragActor();
                if (actor instanceof Item) {
                    add((Item) actor);
                }
            }
        });
    }
}