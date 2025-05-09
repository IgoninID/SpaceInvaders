package com.classig.gamespace;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class GameView {
    private Canvas canvas;
    private GraphicsContext gc;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public GameView(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }

    public void render(GameModel model) {
        // Clear canvas
        gc.setFill(Color.grayRgb(20));
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw score
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(Font.font(20));
        gc.setFill(Color.WHITE);
        gc.fillText("Score: " + model.getScore(), 60, 20);

        // Draw game over
        if (model.isGameOver()) {
            gc.setFont(Font.font(35));
            gc.setFill(Color.YELLOW);
            gc.fillText("Game Over \n Your Score is: " + model.getScore() + " \n Click to play again",
                    WIDTH / 2, HEIGHT / 2.5);
        }

        // Draw universe
        model.getUniverse().forEach(u -> u.draw(gc));

        // Draw player
        model.getPlayer().draw(gc);

        // Draw bombs
        model.getBombs().forEach(b -> b.draw(gc));

        // Draw shots
        model.getShots().forEach(s -> s.draw(gc, model.getScore()));
    }

    public Canvas getCanvas() {
        return canvas;
    }
}