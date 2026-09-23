package org.voidteam.engine;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainGame extends ApplicationAdapter { 
    private ShapeRenderer shapeRenderer;
    private float rectX = 100f;
    private float rectY = 100f;
    private float moveX = 0f;
    private float moveY = 0f;
    private static final float SPEED = 300f; 

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        handleInput();
        update(delta);
        draw();
    }

    private void handleInput() {
        moveX = 0f;
        moveY = 0f;
        if (Gdx.input.isKeyPressed(Keys.LEFT)) moveX = -1f;
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) moveX = 1f;
        if (Gdx.input.isKeyPressed(Keys.UP)) moveY = 1f;
        if (Gdx.input.isKeyPressed(Keys.DOWN)) moveY = -1f;
    }

    private void update(float delta) {
        rectX += moveX * SPEED * delta;
        rectY += moveY * SPEED * delta;
    }

    private void draw() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(rectX, rectY, 50, 50);
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}