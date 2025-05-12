package com.classig.gamespace;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс для управления вражескими объектами (bomb).
 * Отвечает за создание, обновление и проверку столкновений врагов.
 * Соответствует принципу единой ответственности (Srp).
 */
public class EnemyManager {

    /**
     * Генератор случайных чисел
     */
    private static final Random RAND = new Random();

    /**
     * Ширина игрового поля
     */
    private static final int WIDTH = 800;

    /**
     * Размер объектов
     */
    private static final int PLAYER_SIZE = 60;

    /**
     * Максимальное количество врагов
     */
    private static final int MAX_BOMBS = 10;

    /**
     * Список вражеских объектов
     */
    private final List<Rocket> enemies;

    /**
     * Фабрика для создания врагов
     */
    private final EnemyFactory enemyFactory;

    /**
     * Менеджер счёта
     */
    private final ScoreManager scoreManager;

    /**
     * Конструктор для инициализации менеджера врагов.
     * @param enemyFactory фабрика для создания врагов
     * @param scoreManager менеджер счёта
     */
    public EnemyManager(EnemyFactory enemyFactory, ScoreManager scoreManager) {
        this.enemyFactory = enemyFactory;
        this.scoreManager = scoreManager;
        this.enemies = new ArrayList<>();
        // Заполняет список врагов начальными врагами
        for (int i = 0; i < MAX_BOMBS; i++) {
            enemies.add(createEnemy());
        }
    }

    /**
     * Обновляет состояние всех врагов и проверяет столкновения с игроком.
     * @param player объект игрока
     */
    public void update(Rocket player) {
        enemies.forEach(enemy -> {
            enemy.update(); // Обновляет состояние врага (позиция, взрыв)
            if (player.collide(enemy) && !player.isExploding() && !enemy.isExploding()) { // Проверяет столкновение с игроком
                player.explode();
            }
        });
        for (int i = enemies.size() - 1; i >= 0; i--) {
            if (enemies.get(i).isDestroyed()) { // Заменяет уничтоженные бомбы новыми
                enemies.set(i, createEnemy());
            }
        }
    }

    /**
     * Создаёт нового врага с использованием фабрики.
     * @return новый объект Rocket
     */
    private Rocket createEnemy() {
        return enemyFactory.createEnemy(50 + RAND.nextInt(WIDTH - 100), 0, PLAYER_SIZE, scoreManager.getScore());
    }

    /**
     * Возвращает список текущих врагов.
     * @return список объектов Rocket
     */
    public List<Rocket> getEnemies() {
        return enemies;
    }
}
