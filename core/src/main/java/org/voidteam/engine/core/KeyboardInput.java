package org.voidteam.engine.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import java.util.Objects;
import org.voidteam.engine.api.PlayerAction;
import org.voidteam.engine.api.PlayerInput;

/**
 * Entrada de teclado para dos jugadores locales.
 */
public class KeyboardInput implements PlayerInput {
    /**
     * Devuelve true mientras la acción está activa.
     *
     * @param playerId identificador del jugador: 1 o 2
     * @param action acción que se desea consultar
     * @throws IllegalArgumentException si el jugador no está soportado
     * @throws NullPointerException si la acción es null
     */
    @Override
    public boolean isPressed(int playerId, PlayerAction action) {
        Objects.requireNonNull(action, "La acción no puede ser null");

        /**
         * Devuelve el código de tecla correspondiente a la acción del jugador.
         * Para el jugador 1, se utilizan las teclas W, A, S, D.
         * Para el jugador 2, se utilizan las teclas de flecha.
         * Lo cambiarémos luego para que se pueda configurar en el futuro.
         */
        int keyCode = switch (playerId) {
            case 1 -> switch (action) {
                case UP -> Keys.W;
                case DOWN -> Keys.S;
                case LEFT -> Keys.A;
                case RIGHT -> Keys.D;
            };
            case 2 -> switch (action) {
                case UP -> Keys.UP;
                case DOWN -> Keys.DOWN;
                case LEFT -> Keys.LEFT;
                case RIGHT -> Keys.RIGHT;
            };
            default -> throw new IllegalArgumentException(
                "Jugador no soportado: " + playerId
            );
        };

        return Gdx.input.isKeyPressed(keyCode);
    }
}