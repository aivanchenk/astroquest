package com.mygdx.game.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.item.Item;

import java.util.ArrayList;

public class Inventory extends Group {
    private DragAndDrop dragAndDrop;
    private InventoryBackground inventoryBackground;
    private ArrayList<InventorySlot> inventorySlots;
    private Group inventoryContainer; // Container for inventory slots
    private ScrollPane scrollPane;

    public Inventory(DragAndDrop dragAndDrop) {
        this.inventoryBackground = new InventoryBackground();
        this.dragAndDrop = dragAndDrop;
        this.inventorySlots = new ArrayList<>();

        setup();
    }

    private void setup() {
        addActor(inventoryBackground);

        int j = 0;
        for(int i = 0; i < 8; i++){

            if(i % 2 == 0){
                j++;
            }
            InventorySlot inventorySlot = new InventorySlot(new Vector2(600 + i % 2 * 100, 225 + j * 100), dragAndDrop);
            inventorySlots.add(inventorySlot);
            addActor(inventorySlot);
        }
    }

    private void setupScrollPane(Viewport viewport) {
        scrollPane = new ScrollPane(inventoryContainer, new ScrollPane.ScrollPaneStyle());
        scrollPane.setSize(viewport.getWorldWidth(), viewport.getWorldHeight()); // Set to desired size
        scrollPane.setScrollingDisabled(false, false); // Enable scrolling in both directions as needed
        addActor(scrollPane); // Add ScrollPane to the Inventory group
    }

    public void addItem(Item item) {
        inventorySlots.get(0).add(item);
        addActor(item);
    }
}