package org.voidteam.engine.api;

/**
 * Permite consultar las acciones de los jugadores locales.
 */
public interface PlayerInput {

    /**
     * Devuelve {@code true} mientras la acción está activa, por ejemplo,
     * mientras se mantiene presionada la tecla asignada.
     * No se limita al instante en que se presiona.
     *
     * @param playerId identificador del jugador: 1 o 2
     * @param action acción que se desea consultar
     * @throws IllegalArgumentException si el jugador no está soportado
     * @throws NullPointerException si la acción es null
     */
    boolean isPressed(int playerId, PlayerAction action);
}