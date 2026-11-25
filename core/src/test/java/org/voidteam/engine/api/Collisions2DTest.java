package org.voidteam.engine.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class Collisions2DTest {
    @Test
    void rectangulosSeparadosNoSeSuperponen() {
        Bounds2D first = new Bounds2D(0f, 0f, 10f, 10f);

        assertOverlap(false, first, new Bounds2D(20f, 0f, 10f, 10f));
        assertOverlap(false, first, new Bounds2D(0f, 20f, 10f, 10f));
    }

    @Test
    void detectaSuperposicionParcial() {
        assertOverlap(
            true,
            new Bounds2D(0f, 0f, 10f, 10f),
            new Bounds2D(8f, 8f, 10f, 10f)
        );
    }

    @Test
    void detectaUnRectanguloContenidoEnOtro() {
        assertOverlap(
            true,
            new Bounds2D(0f, 0f, 10f, 10f),
            new Bounds2D(2f, 2f, 2f, 2f)
        );
    }

    @Test
    void rectangulosIdenticosSeSuperponen() {
        assertOverlap(
            true,
            new Bounds2D(0f, 0f, 10f, 10f),
            new Bounds2D(0f, 0f, 10f, 10f)
        );
    }

    @Test
    void tocarSoloUnBordeNoCuentaComoSuperposicion() {
        Bounds2D first = new Bounds2D(0f, 0f, 10f, 10f);

        assertOverlap(false, first, new Bounds2D(10f, 0f, 10f, 10f));
        assertOverlap(false, first, new Bounds2D(0f, 10f, 10f, 10f));
    }

    @Test
    void tocarSoloUnaEsquinaNoCuentaComoSuperposicion() {
        assertOverlap(
            false,
            new Bounds2D(0f, 0f, 10f, 10f),
            new Bounds2D(10f, 10f, 10f, 10f)
        );
    }

    @Test
    void permitePosicionesNegativas() {
        assertOverlap(
            true,
            new Bounds2D(-10f, -10f, 8f, 8f),
            new Bounds2D(-5f, -5f, 10f, 10f)
        );
    }

    @Test
    void rechazaDimensionesNulasONegativas() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Bounds2D(0f, 0f, 0f, 10f)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> new Bounds2D(0f, 0f, 10f, 0f)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> new Bounds2D(0f, 0f, -1f, 10f)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> new Bounds2D(0f, 0f, 10f, -1f)
        );
    }

    @Test
    void rechazaValoresNoFinitos() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Bounds2D(Float.NaN, 0f, 10f, 10f)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> new Bounds2D(0f, Float.POSITIVE_INFINITY, 10f, 10f)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> new Bounds2D(0f, 0f, Float.POSITIVE_INFINITY, 10f)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> new Bounds2D(0f, 0f, 10f, Float.NaN)
        );
    }

    @Test
    void rechazaReferenciasNulas() {
        Bounds2D bounds = new Bounds2D(0f, 0f, 10f, 10f);

        assertThrows(
            NullPointerException.class,
            () -> Collisions2D.overlaps(null, bounds)
        );
        assertThrows(
            NullPointerException.class,
            () -> Collisions2D.overlaps(bounds, null)
        );
    }

    private void assertOverlap(
            boolean expected, Bounds2D first, Bounds2D second) {
        assertEquals(expected, Collisions2D.overlaps(first, second));
        assertEquals(expected, Collisions2D.overlaps(second, first));
    }
}