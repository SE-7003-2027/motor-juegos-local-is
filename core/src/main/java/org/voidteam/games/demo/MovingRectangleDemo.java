package org.voidteam.games.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import org.voidteam.engine.api.LocalGame;

/**
 * Demostración del contrato del motor con un rectángulo móvil.
 */
public class MovingRectangleDemo implements LocalGame {
    private static final float SPEED = 300f;
    private static final float SIZE = 50f;

    private ShapeRenderer shapeRenderer;
    private float rectX;
    private float rectY;

    @Override
    public void create() {
        rectX = 100f;
        rectY = 100f;
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void update(float deltaTime) {
        float moveX = 0f;
        float moveY = 0f;

        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            moveX = -1f;
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            moveX = 1f;
        }
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            moveY = 1f;
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            moveY = -1f;
        }

        rectX += moveX * SPEED * deltaTime;
        rectY += moveY * SPEED * deltaTime;
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(rectX, rectY, SIZE, SIZE);
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}