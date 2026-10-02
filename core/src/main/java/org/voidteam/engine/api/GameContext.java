package org.voidteam.engine.api;

import java.util.Objects;

/**
 * Servicios del motor disponibles para un juego.
 */
public class GameContext {
    // El servicio de entrada de jugadores locales.
    private final PlayerInput input;

    /**
     * Crea un contexto de juego con los servicios del motor.
     *
     * @param input servicio de entrada de jugadores locales
     * @throws NullPointerException si el servicio de entrada es null
     */
    public GameContext(PlayerInput input) {
        this.input = Objects.requireNonNull(
            input,
            "El servicio de entrada no puede ser null"
        );
    }

    /**
     * Devuelve el servicio de entrada de jugadores locales. Sirve
     * para consultar las acciones de los jugadores locales.
     *
     * @return servicio de entrada de jugadores locales
     */
    public PlayerInput input() {
        return input;
    }
}