package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.inventory.Inventory;
import com.mygdx.game.world.GameMap;
import com.mygdx.game.world.TileType;

public class Player extends Actor {
    private TextureRegion textureRegion;
    private static final int SPEED = 80;
    private static final int JUMP_VELOCITY = 300;
    private static final int GRAVITY = -600;
    private boolean grounded = false;
    private boolean drillMode = false;
    private float velocityY = 0;
    private GameMap map;
    private Inventory inventory;

    public Player(float x, float y, GameMap map, Inventory inventory) {
        this.map = map;
        this.inventory = inventory;

        Texture texture = new Texture(Gdx.files.internal("player.png"));
        textureRegion = new TextureRegion(texture);

        setPosition(x, y);
        setSize(texture.getWidth(), texture.getHeight());
    }

    @Override
    public void act(float delta) {
        handleInput(delta);
        applyGravity(delta);
        updatePosition(delta);

        if (drillMode) {
            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
            float distance = (float) Math.sqrt(Math.pow(mouseX - getX(), 2) + Math.pow(mouseY - getY(), 2));


            drillRadius.setTooFar(distance > 400 ||
                    drillRadius.intersects(new Vector2(getX(), getY()), getWidth(), getHeight()));


            drillRadius.setPosition(mouseX - drillRadius.getWidth() / 2, mouseY - drillRadius.getHeight() / 2);
        }
    }

    private void handleInput(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            moveX(-SPEED * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            moveX(SPEED * delta);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && grounded) {
            jump();
        }

        if (drillMode && !drillRadius.isTooFar()) {
            if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                map.setTilesInArea(new Vector2(drillRadius.getX(), drillRadius.getY()), (int) drillRadius.getWidth(), (int)drillRadius.getHeight(), TileType.DIRT);
            } else if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                map.setTilesInArea(new Vector2(drillRadius.getX(), drillRadius.getY()), (int) drillRadius.getWidth(), (int)drillRadius.getHeight(), TileType.SKY);
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            drillMode = !drillMode;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            inventory.setVisible(!inventory.isVisible());
        }
    }


    private void moveX(float amount) {
        float newX = getX() + amount;
        if (!map.doesRectCollideWithMap(newX, getY(), (int) getWidth(), (int) getHeight())) {
            setX(newX);
        }
    }

    private void jump() {
        velocityY = JUMP_VELOCITY;
        grounded = false;
    }

    private void applyGravity(float delta) {
        velocityY += GRAVITY * delta;
    }

    private void updatePosition(float delta) {
        float newY = getY() + velocityY * delta;
        if (!map.doesRectCollideWithMap(getX(), newY, (int) getWidth(), (int) getHeight())) {
            setY(newY);
        } else {
            grounded = true;
            velocityY = 0;
        }
    }

    DrillRadius drillRadius = new DrillRadius();
    public DrillRadius getDrillRadius() {
        return drillRadius;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());


        if (drillMode) {
            drillRadius.draw(batch, parentAlpha);
        }

    }
}