package com.mygdx.game.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.game.inventory.InventorySlot;
import com.mygdx.game.item.interfaces.Movable;
import com.mygdx.game.world.TileType;

public abstract class Item extends Actor implements Movable {
    private TextureRegion texture; // for rendering in inventory
    private TileType tileType;     // for rendering in world
    private String name;
    private float weight;
    private DragAndDrop dragAndDrop;
    private InventorySlot inventorySlot;

    public Item(String name, float weight, String texturePath, TileType tileType, DragAndDrop dragAndDrop) {
        this.name = name;
        this.weight = weight;
        this.texture = new TextureRegion(new Texture(Gdx.files.internal(texturePath)));
        this.tileType = tileType;
        inventorySlot = null;

        setSize(texture.getRegionWidth(), texture.getRegionHeight());

        this.dragAndDrop = dragAndDrop;
        initializeDragAndDrop();
        System.out.println(dragAndDrop.getDragSource());
    }

    private void initializeDragAndDrop() {
        dragAndDrop.addSource(new DragAndDrop.Source(this) {
            @Override
            public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                DragAndDrop.Payload payload = new DragAndDrop.Payload();
                payload.setDragActor(Item.this);
                dragAndDrop.setDragActorPosition(getActor().getWidth() / 2, -getActor().getHeight() / 2);

                return payload;
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
                super.dragStop(event, x, y, pointer, payload, target);
                if(target == null && inventorySlot != null) {
                    inventorySlot.add(Item.this);
                }
            }
        });
    }

    public void setInventoryPosition(InventorySlot slot) {
        this.inventorySlot = slot;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

}
