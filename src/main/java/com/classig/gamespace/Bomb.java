package com.classig.gamespace;

import javafx.scene.image.Image;

/**
 * Класс для врагов, наследуется от Rocket.
 * Поддерживает LSP, корректно заменяя Rocket, и OCP, позволяя расширять поведение.
 */
public class Bomb extends Rocket {

    /**
     * Скорость движения врага вниз
     */
    private int speed;

    /**
     *  Высота игрового поля
     */
    private static final int HEIGHT = 600;

    /**
     * Конструктор для создания врага.
     * @param posX  X-координата
     * @param posY  Y-координата
     * @param size  размер бомбы
     * @param image изображение врага
     * @param score текущий счёт игрока (влияет на скорость)
     */
    public Bomb(int posX, int posY, int size, Image image, int score) {
        super(posX, posY, size, image);
        this.speed = (score / 5) + 2;
    }

    @Override
    public void update() {
        super.update(); // Вызывает базовый метод для обработки взрыва
        if (!exploding && !destroyed) posY += speed; // Перемещает врага вниз, если она не взрывается и не уничтожена
        if (posY > HEIGHT) destroyed = true; // Уничтожает врага, если она вышла за нижнюю границу экрана
    }
}