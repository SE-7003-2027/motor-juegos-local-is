package org.voidteam.engine.api;

/**
 * Contrato mínimo de un juego ejecutable por el motor.
 */
public interface LocalGame {
    /** Inicializa el juego utilizando los servicios del motor. */
    void create(GameContext context);

    /** Actualiza la lógica. deltaTime se expresa en segundos. */
    void update(float deltaTime);

    /** Dibuja el estado actual del juego. */
    void render();

    /** Libera los recursos que pertenecen al juego. */
    void dispose();
}