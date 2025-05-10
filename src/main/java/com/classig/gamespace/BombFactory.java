package com.classig.gamespace;

import javafx.scene.image.Image;

import java.util.Random;

/**
 * Реализация фабрики для создания объектов типа Bomb(враг).
 */
public class BombFactory implements EnemyFactory {
    /**
     * Генератор случайных чисел
     */
    private static final Random RAND = new Random();

    /**
     * Массив изображений для врагов
     */
    private static final Image[] BOMBS_IMG = {
            new Image("file:src/main/resources/com/classig/gamespace/Images/alien1.png"), new Image("file:src/main/resources/com/classig/gamespace/Images/alien2.png"),
            new Image("file:src/main/resources/com/classig/gamespace/Images/alien3.png"), new Image("file:src/main/resources/com/classig/gamespace/Images/alien4.png"),
            new Image("file:src/main/resources/com/classig/gamespace/Images/alien5.png"), new Image("file:src/main/resources/com/classig/gamespace/Images/alien6.png"),
            new Image("file:src/main/resources/com/classig/gamespace/Images/alien7.png"), new Image("file:src/main/resources/com/classig/gamespace/Images/alien8.png"),
            new Image("file:src/main/resources/com/classig/gamespace/Images/alien9.png"), new Image("file:src/main/resources/com/classig/gamespace/Images/alien10.png")
    };

    @Override
    public Rocket createEnemy(int posX, int posY, int size, int score) {
        return new Bomb(posX, posY, size, BOMBS_IMG[RAND.nextInt(BOMBS_IMG.length)], score); // Создаёт нового врага с случайным изображением из массива
    }
}
