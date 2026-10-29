package org.voidteam.games.demo;

import org.voidteam.engine.api.GameContext;
import org.voidteam.engine.api.LocalGame;
import org.voidteam.engine.api.PlayerAction;
import org.voidteam.engine.api.PlayerInput;
import org.voidteam.engine.api.Renderer2D;

/**
 * Demostración de entrada independiente para dos jugadores.
 */
public class MovingRectangleDemo implements LocalGame {

    private static final float SPEED = 300f;
    private static final float SIZE = 50f;

/**
 * Dimensiones de la ventana de la demo.
 */

    private static final float WINDOW_WIDTH = 640f;
    private static final float WINDOW_HEIGHT = 480f;

    private PlayerInput input;
    private Renderer2D renderer;

    private float player1X;
    private float player1Y;
    private float player2X;
    private float player2Y;

    @Override
    public void create(GameContext context) {
        input = context.input();
        renderer = context.renderer();

        player1X = 100f;
        player1Y = 100f;
        player2X = 400f;
        player2Y = 100f;
    }

    @Override
    public void update(float deltaTime) {
    //Movimiento de los jugadores
        player1X += horizontalMovement(1) * SPEED * deltaTime;
        player1Y += verticalMovement(1) * SPEED * deltaTime;

        player2X += horizontalMovement(2) * SPEED * deltaTime;
        player2Y += verticalMovement(2) * SPEED * deltaTime;

    // Evita que los jugadores salgan de la ventana.
        player1X = limit(player1X, 0f, WINDOW_WIDTH - SIZE);
        player1Y = limit(player1Y, 0f, WINDOW_HEIGHT - SIZE);

        player2X = limit(player2X, 0f, WINDOW_WIDTH - SIZE);
        player2Y = limit(player2Y, 0f, WINDOW_HEIGHT - SIZE);
    }

    private float limit(float value, float min, float max) {
        return Math.max(min, Math.min(value, max));
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
        renderer.clear(0.15f, 0.15f, 0.2f, 1f);

        renderer.drawText(
        "DEMO DEL MOTOR 2D",
        20f, 455f, 1f, 1f, 1f, 1f
        );

        renderer.drawText(
            "J1: WASD",
            20f, 425f, 0.4f, 1f, 0.4f, 1f
        );

        renderer.drawText(
            "J2: Flechas",
            180f, 425f, 0.4f, 0.6f, 1f, 1f
        );

        renderer.drawText(
            "Imagenes y movimiento independiente",
            20f, 395f, 1f, 1f, 1f, 1f
        );

                // Rectángulos de distintos colores.
        renderer.drawRect(
            50f, 100f, 80f, 45f,
            0f, 1f, 0f, 1f
        );

        renderer.drawRect(
            180f, 100f, 80f, 45f,
            1f, 0f, 0f, 1f
        );

        renderer.drawRect(
            310f, 100f, 80f, 45f,
            0f, 0f, 1f, 1f
        );
        // Rectangulo azul de fondo.
        renderer.drawRect(
            435f, 100f, 100f, 65f,
            0f, 0f, 1f, 1f
        );

        // Rectangulo dorado semitransparente encima.
        renderer.drawRect(
            475f, 100f, 80f, 65f,
            1f, 0.8f, 0f, 0.45f
        );
        // Los dos jugadores se representan mediante imágenes.
        renderer.drawImage(
            "libgdx.png",
            player1X, player1Y, SIZE, SIZE
        );

        renderer.drawImage(
            "pelota.png",
            player2X, player2Y, SIZE, SIZE
        );
    }

    @Override
    public void dispose() {
        // Los recursos gráficos pertenecen al motor: el juego no los libera.
    }
}