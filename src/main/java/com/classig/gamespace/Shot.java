package com.classig.gamespace;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Shot {
    private int posX, posY;
    private int speed = 10;
    public boolean toRemove;
    public static final int size = 6;

    public Shot(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void update() {
        posY -= speed;
    }

    public void draw(GraphicsContext gc, int score) {
        if (score >= 50 && score <= 70 || score >= 120) {
            gc.setFill(Color.YELLOWGREEN);
            speed = 50;
            gc.fillRect(posX - 5, posY - 10, size + 10, size + 30);
        } else {
            gc.setFill(Color.RED);
            gc.fillOval(posX, posY, size, size);
        }
    }

    public boolean collide(Rocket rocket) {
        int distance = distance(this.posX + size / 2, this.posY + size / 2,
                rocket.getPosX() + rocket.size / 2, rocket.getPosY() + rocket.size / 2);
        return distance < rocket.size / 2 + size / 2;
    }

    private int distance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    // Getters
    public int getPosY() { return posY; }
    public boolean isToRemove() { return toRemove; }
    public void setToRemove(boolean toRemove) { this.toRemove = toRemove; }
}