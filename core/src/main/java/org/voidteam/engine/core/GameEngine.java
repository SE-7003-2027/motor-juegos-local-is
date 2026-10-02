package org.voidteam.engine.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import java.util.Objects;
import org.voidteam.engine.api.GameContext;
import org.voidteam.engine.api.LocalGame;

/**
 * Ejecuta un juego mediante el contrato público del motor.
 */
public class GameEngine extends ApplicationAdapter {
    /** Juego que se ejecutará mediante el motor. */
    private final LocalGame game;

    /**
     * Crea un motor de juego que ejecutará el juego dado.
     *
     * @param game juego que se ejecutará mediante el motor
     * @throws NullPointerException si el juego es null
     */
    public GameEngine(LocalGame game) {
        this.game = Objects.requireNonNull(
            game,
            "El juego no puede ser null"
        );
    }

    /** Inicializa el motor y el juego. */
    @Override
    public void create() {
        GameContext context = new GameContext(new KeyboardInput());
        game.create(context);
    }

    /** Actualiza la lógica y dibuja el juego. */
    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        game.update(deltaTime);
        game.render();
    }

    /** Libera los recursos del juego y del motor. */
    @Override
    public void dispose() {
        game.dispose();
    }
}