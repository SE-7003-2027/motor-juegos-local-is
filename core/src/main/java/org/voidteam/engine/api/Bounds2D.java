package org.voidteam.engine.api;

/**
 * Rectangulo inmutable alineado con los ejes.
 * x e y representan su esquina inferior izquierda.
 * El ancho y el alto deben ser positivos y todos los valores finitos.
 */
public record Bounds2D(float x, float y, float width, float height) {
    public Bounds2D {
        if (!Float.isFinite(x) || !Float.isFinite(y) || !Float.isFinite(width) || !Float.isFinite(height)) {
            throw new IllegalArgumentException("Los valores del rectángulo deben ser finitos");
        }

        if (width <= 0f || height <= 0f) {
            throw new IllegalArgumentException("El ancho y el alto deben ser mayores que cero");
        }
    }
}