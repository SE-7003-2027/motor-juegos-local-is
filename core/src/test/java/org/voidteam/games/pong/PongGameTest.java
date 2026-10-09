package org.voidteam.games.pong;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.voidteam.engine.api.GameContext;
import org.voidteam.engine.api.PlayerAction;
import org.voidteam.engine.api.PlayerInput;
import org.voidteam.engine.api.Renderer2D;

class PongGameTest {

    @Test
    void debeCrearElJuegoConLosServiciosDelMotor() {
        PlayerInput input = (playerId, action) -> false;
        Renderer2D renderer = new FakeRenderer();
        GameContext context = new GameContext(input, renderer);

        PongGame game = new PongGame();

        assertDoesNotThrow(() -> game.create(context));
    }

    @Test
    void debeActualizarSinErroresUsandoPlayerInput() {
        PlayerInput input = (playerId, action) ->
            playerId == 1 && action == PlayerAction.UP;

        Renderer2D renderer = new FakeRenderer();
        GameContext context = new GameContext(input, renderer);

        PongGame game = new PongGame();
        game.create(context);

        assertDoesNotThrow(() -> game.update(0.016f));
    }

    @Test
    void debeUtilizarElRendererDelMotor() {
        PlayerInput input = (playerId, action) -> false;
        FakeRenderer renderer = new FakeRenderer();
        GameContext context = new GameContext(input, renderer);

        PongGame game = new PongGame();
        game.create(context);

        game.render();

        assertTrue(renderer.clearCalled);
        assertTrue(renderer.drawRectCalled);
        assertTrue(renderer.drawTextCalled);
    }

    private static class FakeRenderer implements Renderer2D {

        boolean clearCalled;
        boolean drawRectCalled;
        boolean drawTextCalled;

        @Override
        public void clear(
                float red,
                float green,
                float blue,
                float alpha) {
            clearCalled = true;
        }

        @Override
        public void drawRect(
                float x,
                float y,
                float width,
                float height,
                float red,
                float green,
                float blue,
                float alpha) {
            drawRectCalled = true;
        }

        @Override
        public void drawText(
                String text,
                float x,
                float y,
                float red,
                float green,
                float blue,
                float alpha) {
            drawTextCalled = true;
        }
    }
}