package org.voidteam.engine.core;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import org.voidteam.engine.api.Renderer2D;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import java.util.HashMap;
import java.util.Map;
import com.badlogic.gdx.graphics.Texture;

/**
 * Implementación del servicio de renderizado basada en libGDX.
 * Centraliza los recursos gráficos: se crean una sola vez aquí,
 * no en cada cuadro.
 */
public class LibGdxRenderer2D implements Renderer2D {

    private final ShapeRenderer shapeRenderer;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final Map<String, Texture> images = new HashMap<>();

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
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(red, green, blue, alpha);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    @Override
    public void drawText(String text, float x, float y,
                          float red, float green, float blue, float alpha) {
        batch.begin();
        font.setColor(red, green, blue, alpha);
        font.draw(batch, text, x, y);
        batch.end();
    }

    @Override
    public void drawImage(String assetPath, float x, float y, float width, float height) {

        if (assetPath == null || assetPath.isBlank()) {
            throw new IllegalArgumentException(
                "La ruta de la imagen está vacía."
            );
        }

        Texture texture = images.get(assetPath);

        if (texture == null) {
            com.badlogic.gdx.files.FileHandle file =
                Gdx.files.internal(assetPath);

            if (!file.exists()) {
                throw new IllegalArgumentException(
                    "No existe la imagen '" + assetPath
                    + "'. Comprueba que el archivo esté dentro de assets/."
                );
            }

            try {
                texture = new Texture(file);
                images.put(assetPath, texture);
            } catch (RuntimeException e) {
                throw new IllegalArgumentException(
                    "No se pudo cargar la imagen '" + assetPath
                    + "'. Comprueba que sea un archivo de imagen válido.",
                    e
                );
            }
        }

        batch.begin();
        batch.draw(texture, x, y, width, height);
        batch.end();
    }

    /**
    * Libera los recursos gráficos del motor.
    * Debe llamarse después de liberar los recursos del juego.
    */
    public void dispose() {
        for (Texture texture : images.values()) {
            texture.dispose();
        }

        images.clear();

        shapeRenderer.dispose();
        batch.dispose();
        font.dispose();
    }
}