package org.voidteam.engine.api;

/**
 * Permite consultar las acciones de los jugadores locales.
 */
public interface PlayerInput {

    /**
     * Devuelve true mientras la acción está activa.
     *
     * @param playerId identificador del jugador: 1 o 2
     * @param action acción que se desea consultar
     * @throws IllegalArgumentException si el jugador no está soportado
     * @throws NullPointerException si la acción es null
     */
    boolean isPressed(int playerId, PlayerAction action);
}