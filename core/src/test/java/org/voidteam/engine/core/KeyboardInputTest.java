package org.voidteam.engine.core;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.badlogic.gdx.Input.Keys;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.voidteam.engine.api.PlayerAction;

/**
 * Pruebas unitarias de la entrada de teclado para dos jugadores locales.
 */
class KeyboardInputTest {
    @Test
    void espacioActivaDisparoSoloParaElJugadorUno() {
        KeyboardInput input = new KeyboardInput(key -> key == Keys.SPACE);

        assertTrue(input.isPressed(1, PlayerAction.FIRE));
        assertFalse(input.isPressed(2, PlayerAction.FIRE));
    }

    @Test
    void enterActivaDisparoSoloParaElJugadorDos() {
        KeyboardInput input = new KeyboardInput(key -> key == Keys.ENTER);

        assertFalse(input.isPressed(1, PlayerAction.FIRE));
        assertTrue(input.isPressed(2, PlayerAction.FIRE));
    }

    @Test
    void losDisparosSimultaneosSeMantienenActivosHastaSoltarSuTecla() {
        Set<Integer> pressedKeys = new HashSet<>(Set.of(Keys.SPACE, Keys.ENTER));
        KeyboardInput input = new KeyboardInput(pressedKeys::contains);

        assertTrue(input.isPressed(1, PlayerAction.FIRE));
        assertTrue(input.isPressed(2, PlayerAction.FIRE));

        pressedKeys.remove(Keys.SPACE);
        assertFalse(input.isPressed(1, PlayerAction.FIRE));
        assertTrue(input.isPressed(2, PlayerAction.FIRE));

        pressedKeys.clear();
        assertFalse(input.isPressed(1, PlayerAction.FIRE));
        assertFalse(input.isPressed(2, PlayerAction.FIRE));
    }

    @Test
    void conservaLasTeclasDeMovimiento() {
        assertMappedTo(1, PlayerAction.UP, Keys.W);
        assertMappedTo(1, PlayerAction.DOWN, Keys.S);
        assertMappedTo(1, PlayerAction.LEFT, Keys.A);
        assertMappedTo(1, PlayerAction.RIGHT, Keys.D);

        assertMappedTo(2, PlayerAction.UP, Keys.UP);
        assertMappedTo(2, PlayerAction.DOWN, Keys.DOWN);
        assertMappedTo(2, PlayerAction.LEFT, Keys.LEFT);
        assertMappedTo(2, PlayerAction.RIGHT, Keys.RIGHT);
    }

    private void assertMappedTo(int playerId, PlayerAction action, int expectedKey) {
        KeyboardInput input = new KeyboardInput(key -> key == expectedKey);
        assertTrue(input.isPressed(playerId, action),
            () -> "Asignación incorrecta de " + action + " para J" + playerId);
    }
}