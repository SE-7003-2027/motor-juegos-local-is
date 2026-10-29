package org.voidteam.games.tron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.voidteam.engine.api.GameContext;
import org.voidteam.engine.api.PlayerAction;
import org.voidteam.engine.api.Renderer2D;

class TronGameTest {
    @Test
    void dibujaUnaPosicionIntermediaYElRastroCreceConElJugador() {
        RecordingRenderer renderer = new RecordingRenderer();
        TronGame game = createGame(renderer);

        game.update(0.18f);
        game.render();

        // Inicio: x=170. Siguiente celda: x=185. Mitad: x=177.5.
        assertEquals(177.5f, renderer.playerOneX, 0.01f);
        assertEquals(192.5f, renderer.playerOneRightEdge, 0.01f);
    }

    @Test
    void dibujaAmbosJugadoresConImagenesDistinguiblesAjustadasALaCelda() {
        RecordingRenderer renderer = new RecordingRenderer();
        TronGame game = createGame(renderer);

        game.render();

        assertEquals(TronGame.PLAYER_ONE_IMAGE, renderer.playerOneAsset);
        assertEquals(TronGame.PLAYER_TWO_IMAGE, renderer.playerTwoAsset);
        assertNotEquals(renderer.playerOneAsset, renderer.playerTwoAsset);
        assertEquals(15f, renderer.playerOneWidth, 0.01f);
        assertEquals(15f, renderer.playerOneHeight, 0.01f);
    }

    @Test
    void repartirElTiempoEntreCuadrosConservaLaPosicionVisual() {
        RecordingRenderer first = new RecordingRenderer();
        RecordingRenderer second = new RecordingRenderer();
        TronGame firstGame = createGame(first);
        TronGame secondGame = createGame(second);

        firstGame.update(0.18f);
        for (int i = 0; i < 6; i++) {
            secondGame.update(0.03f);
        }
        firstGame.render();
        secondGame.render();

        assertEquals(first.playerOneX, second.playerOneX, 0.01f);
    }

    @Test
    void laPartidaTerminadaPermaneceDetenida() {
        RecordingRenderer renderer = new RecordingRenderer();
        TronGame game = createGame(renderer);
        game.update(2f);
        game.render();
        float finalX = renderer.playerOneX;

        game.update(1f);
        game.render();

        assertEquals(finalX, renderer.playerOneX, 0.01f);
        assertEquals(
            "Empate. Suelta y pulsa W o ARRIBA para reiniciar.",
            renderer.status
        );
    }

    @Test
    void reiniciaSoloConUnaNuevaPulsacionYRestableceLaPosicionVisual() {
        RecordingRenderer renderer = new RecordingRenderer();
        boolean[] upHeld = {true};
        TronGame game = new TronGame();

        game.create(new GameContext(
            (playerId, action) ->
                playerId == 1 && action == PlayerAction.UP && upHeld[0],
            renderer
        ));

        for (int i = 0; i < 100; i++) {
            game.update(0.03f);
        }
        game.render();

        assertEquals(
            "Gana J2. Suelta y pulsa W o ARRIBA para reiniciar.",
            renderer.status
        );

        upHeld[0] = false;
        game.update(0.01f);
        upHeld[0] = true;
        game.update(0.01f);
        game.render();

        assertEquals("Partida en curso", renderer.status);
        assertEquals(170f, renderer.playerOneX, 0.01f);
    }

    private TronGame createGame(RecordingRenderer renderer) {
        TronGame game = new TronGame();
        game.create(new GameContext((playerId, action) -> false, renderer));
        return game;
    }

    private static final class RecordingRenderer implements Renderer2D {
        float playerOneX;
        float playerOneRightEdge;
        float playerOneWidth;
        float playerOneHeight;
        String playerOneAsset;
        String playerTwoAsset;
        String status;

        @Override
        public void clear(
                float red, float green, float blue, float alpha) {
            playerOneRightEdge = 0;
        }

        @Override
        public void drawRect(
                float x, float y, float width, float height,
                float red, float green, float blue, float alpha) {
            boolean trail =
                red == 0.1f && green == 0.4f && blue == 0.65f;

            if (trail) {
                playerOneRightEdge = Math.max(
                    playerOneRightEdge, x + width
                );
            }
        }

        @Override
        public void drawText(
                String text, float x, float y,
                float red, float green, float blue, float alpha) {
            status = text;
        }

        @Override
        public void drawImage(
                String assetPath, float x, float y,
                float width, float height) {
            if (TronGame.PLAYER_ONE_IMAGE.equals(assetPath)) {
                playerOneAsset = assetPath;
                playerOneX = x;
                playerOneWidth = width;
                playerOneHeight = height;
                playerOneRightEdge = Math.max(
                    playerOneRightEdge, x + width
                );
            } else if (TronGame.PLAYER_TWO_IMAGE.equals(assetPath)) {
                playerTwoAsset = assetPath;
            }
        }
    }
}