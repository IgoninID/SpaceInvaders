package com.classig.gamespace;

import javafx.scene.canvas.Canvas;

/**
 * Класс представления, отвечающий за отрисовку игрового состояния.
 * Соответствует SRP, фокусируясь только на координации отрисовки.
 */
public class GameView {

    /**
     * Объект для выполнения отрисовки
     */
    private final Renderer renderer;

    /**
     * Игровой холст
     */
    private final Canvas canvas;

    /**
     * Ширина холста
     */
    private static final int WIDTH = 800;

    /**
     * Высота холста
     */
    private static final int HEIGHT = 600;

    /**
     * Конструктор для инициализации представления.
     * @param canvas   игровой холст
     * @param renderer объект для отрисовки
     */
    public GameView(Canvas canvas, Renderer renderer) {
        this.canvas = canvas;
        this.renderer = renderer;
    }

    /**
     * Отрисовывает текущее состояние игры на холсте.
     * @param model игровая модель
     */
    public void render(GameModel model) {
        renderer.clear(WIDTH, HEIGHT); // Очищает холст
        renderer.setScore(model.getScore()); // Устанавливает счёт для стиля выстрелов
        renderer.drawScore(model.getScore()); // Отрисовывает счёт

        if (model.isGameOver()) { // Отрисовывает экран окончания игры, если игра завершена
            renderer.drawGameOver(model.getScore(), WIDTH, HEIGHT);
        }

        // Отрисовывает все игровые объекты
        model.getUniverse().forEach(u -> u.draw(renderer));
        model.getPlayer().draw(renderer);
        model.getEnemies().forEach(e -> e.draw(renderer));
        model.getShots().forEach(s -> s.draw(renderer));
    }

    /**
     * Возвращает игровой холст.
     * @return объект Canvas
     */
    public Canvas getCanvas() {
        return canvas;
    }
}