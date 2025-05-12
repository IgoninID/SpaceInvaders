package com.classig.gamespace;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Основной класс модели игры, координирующий состояние игровых объектов.
 * Использует менеджеры (EnemyManager, ShotManager, ScoreManager) для соответствия принципа единственной ответственности (Srp).
 * Зависит от абстракции EnemyFactory, поддерживая принцип инверсии зависимостей (Dip).
 */
public class GameModel {

    /**
     * Генератор случайных чисел
     */
    private static final Random RAND = new Random();

    /**
     * Ширина игрового поля
     */
    private static final int WIDTH = 800;

    /**
     * Высота игрового поля
     */
    private static final int HEIGHT = 600;

    /**
     * Размер игрока
     */
    private static final int PLAYER_SIZE = 60;

    /**
     * Изображение игрока
     */
    private static final Image PLAYER_IMG = new Image("file:src/main/resources/com/classig/gamespace/Images/Player.png");

    /**
     * Объект игрока
     */
    private Rocket player;

    /**
     * Список звёзд фона
     */
    private List<Universe> universe;

    /**
     * Менеджер врагов
     */
    private EnemyManager enemyManager;

    /**
     * Менеджер выстрелов
     */
    private ShotManager shotManager;

    /**
     * Менеджер счёта
     */
    private ScoreManager scoreManager;

    /**
     * Флаг окончания игры
     */
    private boolean gameOver;

    /**
     * Конструктор для инициализации модели игры.
     * @param enemyFactory фабрика для создания врагов
     */
    public GameModel(EnemyFactory enemyFactory) {
        this.scoreManager = new ScoreManager();
        this.enemyManager = new EnemyManager(enemyFactory, scoreManager);
        this.shotManager = new ShotManager(scoreManager);
        this.universe = new ArrayList<>();
        setup(); // Инициализирует начальное состояние игры
    }

    /**
     * Инициализирует начальное состояние игры.
     */
    private void setup() {
        player = new Rocket(WIDTH / 2, HEIGHT - PLAYER_SIZE, PLAYER_SIZE, PLAYER_IMG);
        universe.clear();
        scoreManager.resetScore();
        gameOver = false;
    }

    /**
     * Обновляет состояние игры в каждом цикле.
     */
    public void update() {
        if (gameOver) return;

        // Обновляет игрока
        player.update();
        if (player.isDestroyed()) gameOver = true;

        // Обновляет врагов и выстрелы
        enemyManager.update(player);
        shotManager.update(enemyManager.getEnemies());

        // Обновляет звёзды фона (фикс для движения вниз)
        universe.forEach(Universe::update);

        // Случайно добавляет новые звёзды
        if (RAND.nextInt(10) > 2) {
            universe.add(new Universe());
        }
        // Удаляет звёзды, вышедшие за нижнюю границу экрана
        universe.removeIf(u -> u.getPosY() > HEIGHT);
    }

    /**
     * Выполняет выстрел игрока.
     */
    public void shoot() {
        shotManager.shoot(player);
    }

    /**
     * Перезапускает игру, если она завершена.
     */
    public void restart() {
        if (gameOver) {
            gameOver = false;
            setup();
        }
    }

    /**
     * Возвращает объект игрока.
     * @return объект Rocket
     */
    public Rocket getPlayer() { return player; }

    /**
     * Возвращает список звёзд фона.
     * @return список объектов Universe
     */
    public List<Universe> getUniverse() { return universe; }

    /**
     * Возвращает список врагов.
     * @return список объектов Rocket
     */
    public List<Rocket> getEnemies() { return enemyManager.getEnemies(); }

    /**
     * Возвращает список выстрелов.
     * @return список объектов Shot
     */
    public List<Shot> getShots() { return shotManager.getShots(); }

    /**
     * Проверяет, завершена ли игра.
     * @return true, если игра завершена
     */
    public boolean isGameOver() { return gameOver; }

    /**
     * Возвращает текущий счёт.
     * @return текущий счёт
     */
    public int getScore() { return scoreManager.getScore(); }

    /**
     * Устанавливает X-координату игрока.
     * @param x новая X-координата
     */
    public void setPlayerX(int x) { player.setPosX(x); }
}