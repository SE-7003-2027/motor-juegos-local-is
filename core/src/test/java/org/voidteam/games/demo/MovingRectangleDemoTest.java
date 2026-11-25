package org.voidteam.games.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.voidteam.engine.api.GameContext;
import org.voidteam.engine.api.Renderer2D;

class MovingRectangleDemoTest {
    @Test
    void laDemoSolicitaLasCuatroRotaciones() {
        RecordingRenderer renderer = renderDemo();

        List<Float> angles = renderer.images.stream()
            .filter(image -> image.width() == 100f && image.height() == 40f)
            .map(ImageCall::rotationDegrees)
            .toList();

        assertEquals(List.of(0f, 90f, 180f, 270f), angles);
    }

    @Test
    void lasLlamadasSinAnguloConservanPosicionTamanoYRotacionCero() {
        RecordingRenderer renderer = renderDemo();

        assertTrue(renderer.images.contains(
            new ImageCall("libgdx.png", 100f, 100f, 50f, 50f, 0f)));
        assertTrue(renderer.images.contains(
            new ImageCall("pelota.png", 400f, 100f, 50f, 50f, 0f)));
    }

    private RecordingRenderer renderDemo() {
        RecordingRenderer renderer = new RecordingRenderer();
        MovingRectangleDemo demo = new MovingRectangleDemo();

        demo.create(new GameContext((playerId, action) -> false, renderer));
        demo.render();

        return renderer;
    }

    private record ImageCall(
        String path,
        float x,
        float y,
        float width,
        float height,
        float rotationDegrees
    ) { }

    private static final class RecordingRenderer implements Renderer2D {
        final List<ImageCall> images = new ArrayList<>();
        final List<String> texts = new ArrayList<>();
        int rectangles;

        @Override
        public void clear(
                float red, float green, float blue, float alpha) {
            images.clear();
            texts.clear();
            rectangles = 0;
        }

        @Override
        public void drawRect(
                float x, float y, float width, float height,
                float red, float green, float blue, float alpha) {
            rectangles++;
        }

        @Override
        public void drawText(
                String text, float x, float y,
                float red, float green, float blue, float alpha) {
            texts.add(text);
        }

        @Override
        public void drawImage(
                String path, float x, float y,
                float width, float height, float rotationDegrees) {
            images.add(new ImageCall(
                path, x, y, width, height, rotationDegrees
            ));
        }
    }
}