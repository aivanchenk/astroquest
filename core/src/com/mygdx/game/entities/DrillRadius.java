package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.world.GameMap;

public class DrillRadius extends Actor {
    private boolean tooFar = false;
    private TextureRegion normalTexture;
    private TextureRegion errorTexture;

    public DrillRadius() {
        Texture texture = new Texture(Gdx.files.internal("drillRadiusNormal.png"));
        normalTexture = new TextureRegion(texture);
        texture = new Texture(Gdx.files.internal("drillRadiusError.png"));
        errorTexture = new TextureRegion(texture);

        setSize(texture.getWidth(), texture.getHeight());
    }

    public boolean intersects(Vector2 coordinates, float width, float height) {
        float centerX = getX() + getWidth() / 2;
        float centerY = getY() + getHeight() / 2;

        // Add padding of 30 units to each side of the rectangle
        float rectangleX = coordinates.x - 30;
        float rectangleY = coordinates.y - 30;
        float rectangleWidth = width + 60;
        float rectangleHeight = height + 60;

        float rectangleCenterX = rectangleX + rectangleWidth / 2;
        float rectangleCenterY = rectangleY + rectangleHeight / 2;

        float dx = Math.abs(centerX - rectangleCenterX);
        float dy = Math.abs(centerY - rectangleCenterY);

        float combinedHalfWidths = getWidth() / 2 + rectangleWidth / 2;
        float combinedHalfHeights = getHeight() / 2 + rectangleHeight / 2;

        if (dx <= combinedHalfWidths && dy <= combinedHalfHeights) {
            return true;
        }

        return false;
    }



    public void setTooFar(boolean tooFar) {
        this.tooFar = tooFar;
    }

    public boolean isTooFar() {
        return tooFar;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (tooFar) {
            batch.draw(errorTexture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        } else {
            batch.draw(normalTexture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }
}