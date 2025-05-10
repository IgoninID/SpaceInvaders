package com.classig.gamespace;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для управления выстрелами игрока.
 * Отвечает за создание, обновление и проверку столкновений выстрелов.
 * Соответствует SRP, фокусируясь только на управлении выстрелами.
 */
public class ShotManager {

    /**
     * Максимальное количество выстрелов
     */
    private static final int MAX_SHOTS = 20;

    /**
     * Список активных выстрелов
     */
    private List<Shot> shots;

    /**
     * Менеджер счёта
     */
    private ScoreManager scoreManager;

    /**
     * Конструктор для инициализации менеджера выстрелов.
     * @param scoreManager менеджер счёта
     */
    public ShotManager(ScoreManager scoreManager) {
        this.scoreManager = scoreManager;
        this.shots = new ArrayList<>();
    }

    /**
     * Добавляет новый выстрел от игрока.
     * @param player объект игрока
     */
    public void shoot(Rocket player) {
        // Проверяет, не превышено ли максимальное количество выстрелов
        if (shots.size() < MAX_SHOTS) {
            shots.add(player.shoot());
        }
    }

    /**
     * Обновляет состояние выстрелов и проверяет столкновения с врагами.
     * @param enemies список врагов
     */
    public void update(List<Rocket> enemies) {
        for (int i = shots.size() - 1; i >= 0; i--) { // Обходит выстрелы в обратном порядке для безопасного удаления
            Shot shot = shots.get(i);
            if (shot.getPosY() < 0 || shot.isToRemove()) { // Удаляет выстрелы, вышедшие за экран или помеченные для удаления
                shots.remove(i);
                continue;
            }
            shot.update(); // Обновляет позицию выстрела
            for (Rocket enemy : enemies) { // Проверяет столкновения с врагами
                if (shot.collide(enemy) && !enemy.isExploding()) {
                    scoreManager.incrementScore();
                    enemy.explode();
                    shot.setToRemove(true);
                }
            }
        }
    }

    /**
     * Возвращает список текущих выстрелов.
     * @return список объектов Shot
     */
    public List<Shot> getShots() {
        return shots;
    }
}
