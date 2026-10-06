package org.voidteam.engine.core;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import org.voidteam.engine.api.Renderer2D;

/**
 * Implementación del servicio de renderizado basada en libGDX.
 * Centraliza los recursos gráficos: se crean una sola vez aquí,
 * no en cada cuadro.
 */
public class LibGdxRenderer2D implements Renderer2D {

    private final ShapeRenderer shapeRenderer;
    private final SpriteBatch batch;
    private final BitmapFont font;

    public LibGdxRenderer2D() {
        this.shapeRenderer = new ShapeRenderer();
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
    }

    @Override
    public void clear(float red, float green, float blue, float alpha) {
        ScreenUtils.clear(red, green, blue, alpha);
    }

    @Override
    public void drawRect(float x, float y, float width, float height,
                          float red, float green, float blue, float alpha) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(red, green, blue, alpha);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
    }

    @Override
    public void drawText(String text, float x, float y,
                          float red, float green, float blue, float alpha) {
        batch.begin();
        font.setColor(red, green, blue, alpha);
        font.draw(batch, text, x, y);
        batch.end();
    }

    /**
     * Libera los recursos gráficos del motor.
     * Debe llamarse después de liberar los recursos del juego.
     */
    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose();
        font.dispose();
    }
}