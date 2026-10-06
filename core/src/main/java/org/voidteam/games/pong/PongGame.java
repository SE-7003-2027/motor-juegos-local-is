package org.voidteam.games.pong;

import org.voidteam.engine.api.GameContext;
import org.voidteam.engine.api.LocalGame;
import org.voidteam.engine.api.PlayerAction;
import org.voidteam.engine.api.PlayerInput;
import org.voidteam.engine.api.Renderer2D;

public class PongGame implements LocalGame {
    private GameContext context;
    
    // Dimensiones que coinciden con el lanzador (640x480)
    private static final float WIDTH = 640f;
    private static final float HEIGHT = 480f;
    private static final float PADDLE_W = 15f;
    private static final float PADDLE_H = 80f;
    private static final float BALL_SIZE = 15f;
    private static final float PADDLE_SPEED = 300f;
    private static final float BALL_SPEED = 300f;

    private float p1Y, p2Y;
    private float ballX, ballY, ballVX, ballVY;
    private int p1Score, p2Score;
    private boolean gameOver;

    @Override
    public void create(GameContext context) {
        this.context = context;
        this.p1Score = 0;
        this.p2Score = 0;
        resetRound();
    }

    private void resetRound() {
        p1Y = HEIGHT / 2f - PADDLE_H / 2f;
        p2Y = HEIGHT / 2f - PADDLE_H / 2f;
        ballX = WIDTH / 2f - BALL_SIZE / 2f;
        ballY = HEIGHT / 2f - BALL_SIZE / 2f;
        
        ballVX = (Math.random() > 0.5 ? 1 : -1) * BALL_SPEED;
        ballVY = (Math.random() > 0.5 ? 1 : -1) * BALL_SPEED;
        gameOver = false;
    }

    @Override
    public void update(float deltaTime) {
        PlayerInput input = context.input();

        if (gameOver) {
            // Verifica a los jugadores 1 y 2
            if (input.isPressed(1, PlayerAction.UP) || input.isPressed(2, PlayerAction.UP)) {
                p1Score = 0;
                p2Score = 0;
                resetRound();
            }
            return;
        }

        // Controles J1 (Teclas W / S según KeyboardInput)
        if (input.isPressed(1, PlayerAction.UP)) p1Y += PADDLE_SPEED * deltaTime;
        if (input.isPressed(1, PlayerAction.DOWN)) p1Y -= PADDLE_SPEED * deltaTime;
        
        // Controles J2 (Flechas Arriba / Abajo según KeyboardInput)
        if (input.isPressed(2, PlayerAction.UP)) p2Y += PADDLE_SPEED * deltaTime;
        if (input.isPressed(2, PlayerAction.DOWN)) p2Y -= PADDLE_SPEED * deltaTime;

        // Límites paletas
        p1Y = Math.max(0, Math.min(HEIGHT - PADDLE_H, p1Y));
        p2Y = Math.max(0, Math.min(HEIGHT - PADDLE_H, p2Y));

        // Movimiento pelota
        ballX += ballVX * deltaTime;
        ballY += ballVY * deltaTime;

        // Rebotes verticales
        if (ballY <= 0) { ballY = 0; ballVY = -ballVY; }
        else if (ballY >= HEIGHT - BALL_SIZE) { ballY = HEIGHT - BALL_SIZE; ballVY = -ballVY; }

        // Rebotes paletas
        float p1X = 30f;
        if (ballX < p1X + PADDLE_W && ballX + BALL_SIZE > p1X && ballY < p1Y + PADDLE_H && ballY + BALL_SIZE > p1Y) {
            ballX = p1X + PADDLE_W; 
            ballVX = -ballVX;
        }
        float p2X = WIDTH - 30f - PADDLE_W;
        if (ballX < p2X + PADDLE_W && ballX + BALL_SIZE > p2X && ballY < p2Y + PADDLE_H && ballY + BALL_SIZE > p2Y) {
            ballX = p2X - BALL_SIZE; 
            ballVX = -ballVX;
        }

        // Puntuación
        if (ballX < 0) { p2Score++; checkWin(); }
        else if (ballX > WIDTH) { p1Score++; checkWin(); }
    }

    private void checkWin() {
        if (p1Score >= 5 || p2Score >= 5) gameOver = true;
        else {
            ballX = WIDTH / 2f - BALL_SIZE / 2f;
            ballY = HEIGHT / 2f - BALL_SIZE / 2f;
            ballVX = -ballVX;
        }
    }

    @Override
    public void render() {
        Renderer2D renderer = context.renderer();
        
        // Requiere: rojo, verde, azul, alpha
        renderer.clear(0.1f, 0.1f, 0.1f, 1f);

        // Paleta 1, Paleta 2 y Pelota (color blanco puro sin transparencia)
        renderer.drawRect(30f, p1Y, PADDLE_W, PADDLE_H, 1f, 1f, 1f, 1f); 
        renderer.drawRect(WIDTH - 30f - PADDLE_W, p2Y, PADDLE_W, PADDLE_H, 1f, 1f, 1f, 1f); 
        renderer.drawRect(ballX, ballY, BALL_SIZE, BALL_SIZE, 1f, 1f, 1f, 1f); 

        // Marcador y textos
        if (gameOver) {
            String winText = "Gana J" + (p1Score >= 5 ? "1" : "2") + " - Presiona ARRIBA para reiniciar";
            renderer.drawText(winText, WIDTH / 2f - 150f, HEIGHT / 2f, 1f, 1f, 1f, 1f);
        } else {
            renderer.drawText("P1: " + p1Score, 100f, HEIGHT - 30f, 1f, 1f, 1f, 1f);
            renderer.drawText("P2: " + p2Score, WIDTH - 100f, HEIGHT - 30f, 1f, 1f, 1f, 1f);
        }
    }

    @Override
    public void dispose() {
        // Nada que liberar aquí, el motor maneja los recursos de Renderer2D.
    }
}