package org.voidteam.engine.api;

/**
 * Contrato mínimo de un juego ejecutable por el motor.
 */
public interface LocalGame {
    /** Inicializa el estado y los recursos del juego. */
    void create();

    /** Actualiza la lógica. deltaTime se expresa en segundos. */
    void update(float deltaTime);

    /** Dibuja el estado actual del juego. */
    void render();

    /** Libera los recursos que pertenecen al juego. */
    void dispose();
}