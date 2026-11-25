package org.voidteam.engine.api;

import java.util.Objects;

/** Deteccion de superposicion, sin resolver las consecuencias del contacto. */
public final class Collisions2D {
    // El constructor privado evita crear instancias innecesarias.
    private Collisions2D() {
    }

    /**
     * Devuelve true si los rectangulos comparten un area positiva.
     * Tocar unicamente bordes o esquinas no cuenta como superposicion.
     * No modifica los rectangulos ni aplica movimiento, daño o rebotes.
     */
    public static boolean overlaps(Bounds2D first, Bounds2D second) {
        Objects.requireNonNull(first, "El primer rectángulo no puede ser null");
        Objects.requireNonNull(second, "El segundo rectángulo no puede ser null");

        // double evita desbordamientos al sumar los valores float
        double firstRight = (double) first.x() + first.width();
        double firstTop = (double) first.y() + first.height();
        double secondRight = (double) second.x() + second.width();
        double secondTop = (double) second.y() + second.height();

        return first.x() < secondRight
            && firstRight > second.x()
            && first.y() < secondTop
            && firstTop > second.y();
    }
}