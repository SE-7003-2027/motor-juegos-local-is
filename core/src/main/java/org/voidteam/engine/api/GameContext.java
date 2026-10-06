package org.voidteam.engine.api;

import java.util.Objects;

/**
 * Servicios del motor disponibles para un juego.
 */
public class GameContext {
    // El servicio de entrada de jugadores locales.
    private final PlayerInput input;
    private final Renderer2D renderer;

    /**
     * Crea un contexto de juego con los servicios del motor.
     *
     * @param input servicio de entrada de jugadores locales
     * @throws NullPointerException si el servicio de entrada es null
     */
    public GameContext(PlayerInput input, Renderer2D renderer) {
        this.input = Objects.requireNonNull(
            input,
            "El servicio de entrada no puede ser null"
        );

        this.renderer = Objects.requireNonNull(
            renderer,

             "El servicio de renderizado no puede ser null"

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

    /**
     * Devuelve el servicio de renderizado 2D 
     * 
     * @return servicio renderizado 2D
     */

    public Renderer2D renderer() {
        return renderer;

    }
}