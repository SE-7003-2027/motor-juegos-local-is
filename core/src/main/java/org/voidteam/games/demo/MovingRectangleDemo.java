package org.voidteam.games.demo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import org.voidteam.engine.api.GameContext;
import org.voidteam.engine.api.LocalGame;
import org.voidteam.engine.api.PlayerAction;
import org.voidteam.engine.api.PlayerInput;

/**
 * Demostración de entrada independiente para dos jugadores.
 */
public class MovingRectangleDemo implements LocalGame {

    private static final float SPEED = 300f;
    private static final float SIZE = 50f;

    private PlayerInput input;
    private ShapeRenderer shapeRenderer;

    private float player1X;
    private float player1Y;
    private float player2X;
    private float player2Y;

    @Override
    public void create(GameContext context) {
        input = context.input();

        player1X = 100f;
        player1Y = 100f;
        player2X = 400f;
        player2Y = 100f;

        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void update(float deltaTime) {
        player1X += horizontalMovement(1) * SPEED * deltaTime;
        player1Y += verticalMovement(1) * SPEED * deltaTime;

        player2X += horizontalMovement(2) * SPEED * deltaTime;
        player2Y += verticalMovement(2) * SPEED * deltaTime;
    }

    private float horizontalMovement(int playerId) {
        float movement = 0f;

        if (input.isPressed(playerId, PlayerAction.RIGHT)) {
            movement += 1f;
        }
        if (input.isPressed(playerId, PlayerAction.LEFT)) {
            movement -= 1f;
        }

        return movement;
    }

    private float verticalMovement(int playerId) {
        float movement = 0f;

        if (input.isPressed(playerId, PlayerAction.UP)) {
            movement += 1f;
        }
        if (input.isPressed(playerId, PlayerAction.DOWN)) {
            movement -= 1f;
        }

        return movement;
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(player1X, player1Y, SIZE, SIZE);

        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(player2X, player2Y, SIZE, SIZE);

        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}