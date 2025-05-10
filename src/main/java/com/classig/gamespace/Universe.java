package com.classig.gamespace;

import java.util.Random;

/**
 * Класс для объектов фона (звёзд), создающих эффект звёздного неба.
 * Реализует Drawable и Updatable для отрисовки и перемещения звёзд.
 */
public class Universe implements Drawable, Updatable {

    /**
     * Координата x звезды
     */
    private int posX;

    /**
     * Координата y звезды
     */
    private int posY;

    /**
     * Высота звезды
     */
    private int h;

    /**
     * Ширина звезды
     */
    private int w;

    /**
     * Красный компонент звезды
     */
    private int r;

    /**
     * Зеленый компонент звезды
     */
    private int g;

    /**
     * Синий компонент звезды
     */
    private int b;

    /**
     * Прозрачность звезды
     */
    private double opacity;

    /**
     * Генератор случайных чисел
     */
    private static final Random RAND = new Random();

    /**
     * Конструктор для создания звезды с случайными параметрами.
     */
    public Universe() {
        posX = RAND.nextInt(800); // Случайная X-координата
        posY = 0; // Начальная Y-координата (вверху экрана)
        w = RAND.nextInt(5) + 1; // Случайная ширина
        h = RAND.nextInt(5) + 1; // Случайная высота
        r = RAND.nextInt(100) + 150; // Случайный красный компонент
        g = RAND.nextInt(100) + 150; // Случайный зелёный компонент
        b = RAND.nextInt(100) + 150; // Случайный синий компонент
        opacity = RAND.nextFloat(); // Случайная прозрачность
        if (opacity < 0) opacity *= -1;
        if (opacity > 0.5) opacity = 0.5;
    }

    @Override
    public void draw(Renderer renderer) {
        renderer.drawUniverse(posX, posY, w, h, r, g, b, opacity); // Отрисовывает звезду с заданным цветом и прозрачностью
    }

    @Override
    public void update() {
        // Изменяет прозрачность для эффекта мерцания
        if (opacity > 0.8) opacity -= 0.01;
        if (opacity < 0.1) opacity += 0.01;
        // Перемещает звезду вниз для создания эффекта движения
        posY += 20;
    }

    /**
     * Возвращает Y-координату звезды.
     * @return Y-координата
     */
    public int getPosY() { return posY; }
}