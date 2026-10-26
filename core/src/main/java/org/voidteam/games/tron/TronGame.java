package org.voidteam.games.tron;

import org.voidteam.engine.api.GameContext;
import org.voidteam.engine.api.LocalGame;
import org.voidteam.engine.api.PlayerAction;
import org.voidteam.engine.api.PlayerInput;
import org.voidteam.engine.api.Renderer2D;

/** Conecta las reglas de Tron con los servicios publicos del motor. */
public final class TronGame implements LocalGame {
    private static final int COLUMNS = 40;
    private static final int ROWS = 26;
    private static final float CELL_SIZE = 15f;
    private static final float BOARD_X = 20f;
    private static final float BOARD_Y = 20f;
    private static final double STEP_SECONDS = 0.12;

    private PlayerInput input;
    private Renderer2D renderer;
    private TronMatch match;
    private double accumulator;
    private boolean previousOneUp;
    private boolean previousTwoUp;

    // Usamos los índices 1 y 2 para identificar a los jugadores.
    private final int[] previousX = new int[3];
    private final int[] previousY = new int[3];

    @Override
    public void create(GameContext context) {
        input = context.input();
        renderer = context.renderer();
        match = new TronMatch(COLUMNS, ROWS);
        accumulator = 0;
        previousOneUp = false;
        previousTwoUp = false;
        savePreviousPositions();
    }

    @Override
    public void update(float deltaTime) {
        boolean oneUp = input.isPressed(1, PlayerAction.UP);
        boolean twoUp = input.isPressed(2, PlayerAction.UP);
        boolean restartPressed = (oneUp && !previousOneUp) || (twoUp && !previousTwoUp);

        previousOneUp = oneUp;
        previousTwoUp = twoUp;

        if (match.isOver()) {
            if (restartPressed) {
                match.reset();
                accumulator = 0;
                savePreviousPositions();
            }
            return;
        }

        readDirections(1);
        readDirections(2);
        accumulator += deltaTime;

        // Pasos del juego. El ciclo principal sigue siendo del motor.
        while (accumulator >= STEP_SECONDS && !match.isOver()) {
            savePreviousPositions();
            match.step();
            accumulator -= STEP_SECONDS;
        }

        if (match.isOver()) accumulator = 0;
    }

    // Guarda las posiciones de los jugadores antes de que se mueva el juego, para poder detectar colisiones entre ellos.
    private void savePreviousPositions() {
        for (int playerId = 1; playerId <= 2; playerId++) {
            previousX[playerId] = match.playerX(playerId);
            previousY[playerId] = match.playerY(playerId);
        }
    }

    private void readDirections(int playerId) {
        // Prioridad fija: arriba, abajo, izquierda, derecha.
        // La partida acepta unicamente el primer giro valido de cada paso.
        if (input.isPressed(playerId, PlayerAction.UP)) {
            match.queueDirection(playerId, TronMatch.Direction.UP);
        }
        if (input.isPressed(playerId, PlayerAction.DOWN)) {
            match.queueDirection(playerId, TronMatch.Direction.DOWN);
        }
        if (input.isPressed(playerId, PlayerAction.LEFT)) {
            match.queueDirection(playerId, TronMatch.Direction.LEFT);
        }
        if (input.isPressed(playerId, PlayerAction.RIGHT)) {
            match.queueDirection(playerId, TronMatch.Direction.RIGHT);
        }
    }

    @Override
    public void render() {
        renderer.clear(0.05f, 0.05f, 0.08f, 1f);
        renderer.drawRect(
            BOARD_X, BOARD_Y,
            COLUMNS * CELL_SIZE, ROWS * CELL_SIZE,
            0.1f, 0.1f, 0.14f, 1f
        );

        for (int x = 0; x < COLUMNS; x++) {
            for (int y = 0; y < ROWS; y++) {
                // La celda más reciente se dibuja progresivamente.
                if (isCurrentPlayerCell(x, y)) {
                    continue;
                }

                int owner = match.cellOwner(x, y);
                if (owner == 1) {
                    drawCell(x, y, 0.1f, 0.4f, 0.65f);
                } else if (owner == 2) {
                    drawCell(x, y, 0.65f, 0.3f, 0.05f);
                }
            }
        }

        float alpha = (float) Math.min(accumulator / STEP_SECONDS, 1.0);
        drawInterpolatedPlayer(1, alpha);
        drawInterpolatedPlayer(2, alpha);

        renderer.drawText(
            "TRON - J1 azul: WASD | J2 naranja: Flechas",
            20f, 465f, 1f, 1f, 1f, 1f
        );
        renderer.drawText(
            "Evita paredes y rastros. No puedes invertir la direccion.",
            20f, 445f, 1f, 1f, 1f, 1f
        );

        String status = switch (match.result()) {
            case RUNNING -> "Partida en curso";
            case PLAYER_ONE_WINS ->
                "Gana J1. Suelta y pulsa W o ARRIBA para reiniciar.";
            case PLAYER_TWO_WINS ->
                "Gana J2. Suelta y pulsa W o ARRIBA para reiniciar.";
            case DRAW ->
                "Empate. Suelta y pulsa W o ARRIBA para reiniciar.";
        };

        renderer.drawText(status, 20f, 425f, 1f, 1f, 1f, 1f);
    }

    // Verifica si la celda especificada es la posicion actual de alguno de los jugadores.
    private boolean isCurrentPlayerCell(int x, int y) {
        return (x == match.playerX(1) && y == match.playerY(1))
            || (x == match.playerX(2) && y == match.playerY(2));
    }

    // Dibuja la posicion visual de un jugador, interpolando entre su posicion anterior y la actual.
    private void drawInterpolatedPlayer(int playerId, float alpha) {
        float visualX = previousX[playerId]
            + (match.playerX(playerId) - previousX[playerId]) * alpha;
        float visualY = previousY[playerId]
            + (match.playerY(playerId) - previousY[playerId]) * alpha;

        float red = playerId == 1 ? 0.1f : 0.65f;
        float green = playerId == 1 ? 0.4f : 0.3f;
        float blue = playerId == 1 ? 0.65f : 0.05f;

        // Extiende el rastro hasta la posicion visual, sin dejar huecos.
        renderer.drawRect(
            BOARD_X + Math.min(previousX[playerId], visualX) * CELL_SIZE,
            BOARD_Y + Math.min(previousY[playerId], visualY) * CELL_SIZE,
            (Math.abs(visualX - previousX[playerId]) + 1f) * CELL_SIZE,
            (Math.abs(visualY - previousY[playerId]) + 1f) * CELL_SIZE,
            red, green, blue, 1f
        );

        // Dibuja el jugador en su posicion visual.
        if (playerId == 1) {
            drawCell(visualX, visualY, 0.2f, 0.8f, 1f);
        } else {
            drawCell(visualX, visualY, 1f, 0.6f, 0.15f);
        }
    }

    private void drawCell(
            float x, float y, float red, float green, float blue) {
        renderer.drawRect(
            BOARD_X + x * CELL_SIZE,
            BOARD_Y + y * CELL_SIZE,
            CELL_SIZE, CELL_SIZE,
            red, green, blue, 1f
        );
    }

    @Override
    public void dispose() {
        // Entrada y renderizado pertenecen al motor.
    }
}