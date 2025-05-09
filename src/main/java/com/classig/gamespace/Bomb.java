package com.classig.gamespace;

import javafx.scene.image.Image;

public class Bomb extends Rocket {
    private int speed;

    public Bomb(int posX, int posY, int size, Image image, int score) {
        super(posX, posY, size, image);
        this.speed = (score / 5) + 2;
    }

    @Override
    public void update() {
        super.update();
        if (!exploding && !destroyed) posY += speed;
        if (posY > 600) destroyed = true;
    }
}