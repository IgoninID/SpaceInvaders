package com.classig.gamespace;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Rocket {
    protected int posX, posY, size;
    protected boolean exploding, destroyed;
    protected Image img;
    protected int explosionStep = 0;
    protected static final int EXPLOSION_W = 128;
    protected static final int EXPLOSION_H = 128;
    protected static final int EXPLOSION_ROWS = 3;
    protected static final int EXPLOSION_COL = 3;
    protected static final int EXPLOSION_STEPS = 15;
    protected static final Image EXPLOSION_IMG = new Image("file:src/main/resources/com/classig/gamespace/Images/explosion.png");

    public Rocket(int posX, int posY, int size, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.size = size;
        this.img = image;
    }

    public Shot shoot() {
        return new Shot(posX + size / 2 - Shot.size / 2, posY - Shot.size);
    }

    public void update() {
        if (exploding) explosionStep++;
        destroyed = explosionStep > EXPLOSION_STEPS;
    }

    public void draw(GraphicsContext gc) {
        if (exploding) {
            gc.drawImage(EXPLOSION_IMG, explosionStep % EXPLOSION_COL * EXPLOSION_W, (explosionStep / EXPLOSION_ROWS) * EXPLOSION_H + 1,
                    EXPLOSION_W, EXPLOSION_H, posX, posY, size, size);
        } else {
            gc.drawImage(img, posX, posY, size, size);
        }
    }

    public boolean collide(Rocket other) {
        int d = distance(this.posX + size / 2, this.posY + size / 2,
                other.posX + other.size / 2, other.posY + other.size / 2);
        return d < other.size / 2 + this.size / 2;
    }

    public void explode() {
        exploding = true;
        explosionStep = -1;
    }

    private int distance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    // Getters and setters
    public int getPosX() { return posX; }
    public void setPosX(int posX) { this.posX = posX; }
    public int getPosY() { return posY; }
    public boolean isDestroyed() { return destroyed; }
    public boolean isExploding() { return exploding; }
}