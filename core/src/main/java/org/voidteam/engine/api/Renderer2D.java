package org.voidteam.engine.api;

/**
 * Servicio de renderizado 2D disponible para un juego.
 * No expone tipos de libGDX: los colores se representan como
 * componentes rojo, verde, azul y transparencia entre 0 y 1.
 */
public interface Renderer2D {

    /**
     * Limpia la pantalla con el color indicado.
     */
    void clear(float red, float green, float blue, float alpha);

    /**
     * Dibuja un rectángulo relleno.
     *
     * @param x      posición horizontal de la esquina inferior izquierda
     * @param y      posición vertical de la esquina inferior izquierda
     * @param width  ancho del rectángulo
     * @param height alto del rectángulo
     */
    void drawRect(float x, float y, float width, float height,
                  float red, float green, float blue, float alpha);

    /**
     * Dibuja texto sencillo en la posición indicada.
     */
    void drawText(String text, float x, float y,
                  float red, float green, float blue, float alpha);
}