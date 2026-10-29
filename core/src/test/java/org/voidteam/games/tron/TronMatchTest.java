package org.voidteam.games.tron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.voidteam.games.tron.TronMatch.Direction;
import org.voidteam.games.tron.TronMatch.Result;

class TronMatchTest {
    // Verifica que al iniciar un juego, los jugadores comienzan en celdas distintas y que el juego no ha terminado.
    @Test
    void iniciaConDosJugadoresEnCeldasDistintas() {
        TronMatch match = new TronMatch(12, 8);

        assertNotEquals(match.playerX(1), match.playerX(2));
        assertEquals(
            1, match.cellOwner(match.playerX(1), match.playerY(1))
        );
        assertEquals(
            2, match.cellOwner(match.playerX(2), match.playerY(2))
        );
        assertFalse(match.isOver());
    }

    // Verifica que al avanzar una celda, el jugador deja un rastro en la celda anterior y se mueve a la nueva posición.
    @Test
    void avanzaUnaCeldaYConservaElRastro() {
        TronMatch match = new TronMatch(12, 8);
        int initialX = match.playerX(1);
        int initialY = match.playerY(1);

        match.step();

        assertEquals(initialX + 1, match.playerX(1));
        assertEquals(1, match.cellOwner(initialX, initialY));
        assertEquals(1, match.cellOwner(initialX + 1, initialY));
    }

    // Verifica que el juego no permite que un jugador gire directamente en la dirección opuesta a la que se está moviendo.
    @Test
    void rechazaElGiroDirectamenteOpuesto() {
        TronMatch match = new TronMatch(12, 8);
        int initialX = match.playerX(1);

        match.queueDirection(1, Direction.LEFT);
        match.step();

        assertEquals(initialX + 1, match.playerX(1));
    }

    // Verifica que el juego solo permite un giro por paso, ignorando giros adicionales hasta el siguiente paso.
    @Test
    void aceptaSoloUnGiroPorPaso() {
        TronMatch match = new TronMatch(12, 8);
        int initialX = match.playerX(1);
        int initialY = match.playerY(1);

        match.queueDirection(1, Direction.UP);
        match.queueDirection(1, Direction.LEFT);
        match.step();

        assertEquals(initialX, match.playerX(1));
        assertEquals(initialY + 1, match.playerY(1));
    }

    // Verifica que un jugador que choca con una pared pierde y el otro jugador gana.
    @Test
    void chocarConUnaParedDaLaVictoriaAlOtroJugador() {
        TronMatch match = new TronMatch(12, 8);
        match.queueDirection(1, Direction.UP);

        for (int i = 0; i < 4; i++) {
            match.step();
        }

        assertEquals(Result.PLAYER_TWO_WINS, match.result());
    }

    // Verifica que si ambos jugadores chocan al mismo tiempo, el resultado es un empate.
    @Test
    void llegarALaMismaCeldaProduceEmpate() {
        TronMatch match = new TronMatch(9, 6);

        match.step();
        match.step();

        assertEquals(Result.DRAW, match.result());
    }

    // Verifica que si ambos jugadores intentan intercambiar posiciones, el resultado es un empate.
    @Test
    void intentarIntercambiarPosicionesProduceEmpate() {
        TronMatch match = new TronMatch(8, 6);

        match.step();
        match.step();

        assertEquals(Result.DRAW, match.result());
    }

    // Verifica que un jugador que choca con su propio rastro pierde y el otro jugador gana.
    @Test
    void chocarConElPropioRastroProvocaLaDerrota() {
        TronMatch match = new TronMatch(20, 12);
        Direction[] turns = {
            Direction.UP,
            Direction.RIGHT,
            Direction.DOWN,
            Direction.LEFT
        };

        for (Direction direction : turns) {
            match.queueDirection(1, direction);
            match.step();
        }

        assertEquals(Result.PLAYER_TWO_WINS, match.result());
    }

    // Verifica que un jugador que choca con el rastro del otro jugador pierde y el otro jugador gana.
    @Test
    void chocarConElRastroContrarioProvocaLaDerrota() {
        TronMatch match = new TronMatch(12, 8);

        match.queueDirection(2, Direction.UP);
        match.step();
        match.queueDirection(2, Direction.RIGHT);
        match.step();
        match.queueDirection(2, Direction.DOWN);
        match.step();
        match.queueDirection(2, Direction.RIGHT);
        match.step();
        match.step();

        assertEquals(Result.PLAYER_TWO_WINS, match.result());
    }

    // Verifica que después de que el juego termina, los jugadores no pueden moverse y que al reiniciar el juego, el tablero se limpia y los jugadores vuelven a sus posiciones iniciales.
    @Test
    void noAvanzaDespuesDeTerminarYResetLimpiaElTablero() {
        TronMatch match = new TronMatch(9, 6);
        match.step();
        match.step();
        int finalX = match.playerX(1);

        match.step();

        assertEquals(finalX, match.playerX(1));

        match.reset();

        assertEquals(Result.RUNNING, match.result());
        assertEquals(2, match.playerX(1));
        assertEquals(0, match.cellOwner(3, 3));
    }
}