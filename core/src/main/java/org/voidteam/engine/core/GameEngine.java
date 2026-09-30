package org.voidteam.engine.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import java.util.Objects;
import org.voidteam.engine.api.LocalGame;

/**
 * Ejecuta un juego mediante el contrato público del motor.
 */
public class GameEngine extends ApplicationAdapter {
    private final LocalGame game;

    public GameEngine(LocalGame game) {
        this.game = Objects.requireNonNull(game, "El juego no puede ser null");
    }

    @Override
    public void create() {
        game.create();
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        game.update(deltaTime);
        game.render();
    }

    @Override
    public void dispose() {
        game.dispose();
    }
}