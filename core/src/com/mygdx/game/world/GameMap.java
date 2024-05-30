package com.mygdx.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.entities.Player;
import com.mygdx.game.inventory.Inventory;
import com.mygdx.game.inventory.InventorySlot;
import com.mygdx.game.item.Item;
import com.mygdx.game.item.resources.RawResource;
import com.mygdx.game.item.resources.Resource;
import com.mygdx.game.inventory.InventoryBackground;

import javax.swing.plaf.ViewportUI;

public abstract class GameMap {
    Stage stage;
    Player player;
    DragAndDrop dragAndDrop;

    public GameMap() {
        dragAndDrop = new DragAndDrop();
        Inventory inventory = new Inventory(dragAndDrop);

        stage = new Stage();
        player = new Player(100, 550, this, inventory);
        Gdx.input.setInputProcessor(stage);
        stage.addActor(player);

        Item resin = new RawResource("Resin", 1, "resin.png", TileType.RESIN, TileType.RESIN_RAW, dragAndDrop);
        inventory.addItem(resin);

        stage.addActor(inventory);
    }

    public void render(OrthographicCamera camera, SpriteBatch batch) {
        stage.draw();
    }
    public void update(float delta) {
        player.act(delta);
    }
    public void dipsose() {

    }
    public abstract TileType getTileTypeByLocation(int layer, float x, float y);
    public abstract TileType getTileTypeByCoordinate(int layer, int col, int row);

    public boolean doesRectCollideWithMap(float x, float y, int width, int height){
        if (x < 0 || y < 0 || x + width > getPixelWidth() || y + height > getPixelHeight()){
            return true;
        }

        for (int row = (int) (y / TileType.TILE_SIZE); row < Math.ceil((y + height) / TileType.TILE_SIZE); row++){
            for (int col = (int) (x / TileType.TILE_SIZE); col < Math.ceil((x + width) / TileType.TILE_SIZE); col++){
                for (int layer = 0; layer < getLayers(); layer++){
                    TileType type = getTileTypeByCoordinate(layer, col, row);
                    if (type != null && type.isCollidable()){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void setTilesInArea(Vector2 coordinates, int width, int height, TileType tileType){
        if (coordinates.x < 0 || coordinates.y < 0 || coordinates.x + width > getPixelWidth() || coordinates.y + height > getPixelHeight()){
            return;
        }

        for (int row = (int) (coordinates.y / TileType.TILE_SIZE); row < Math.ceil((coordinates.y + height) / TileType.TILE_SIZE); row++){
            for (int col = (int) (coordinates.x / TileType.TILE_SIZE); col < Math.ceil((coordinates.x + width) / TileType.TILE_SIZE); col++){
                for (int layer = 0; layer < getLayers(); layer++){
                    TileType type = getTileTypeByCoordinate(layer, col, row);

                    if (tileType != TileType.SKY) {
                        setTileByCoordinate(layer, col, row, tileType);
                    } else if (type != null && type.isCollidable()){
                        setTileByCoordinate(layer, col, row, tileType);
                    }
                }
            }
        }
    }

    public abstract float getWidth();
    public abstract float getHeight();
    public abstract int getLayers();

    public float getPixelWidth() {
        return this.getWidth() * TileType.TILE_SIZE;
    }

    public float getPixelHeight() {
        return this.getHeight() * TileType.TILE_SIZE;
    }

    public abstract void setTileByCoordinate(int layer, int col, int row, TileType type);
}
