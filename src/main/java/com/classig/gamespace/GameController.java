package com.classig.gamespace;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * Контроллер игры, связывающий модель и представление через FXML.
 * Отвечает за обработку ввода и координацию игрового цикла.
 * Соответствует SRP, фокусируясь на управлении взаимодействием.
 */
public class GameController {

    /**
     * Игровой холст
     */
    @FXML
    private Canvas gameCanvas;

    /**
     * Игровая модель
     */
    private GameModel model;

    /**
     * Игровое представление
     */
    private GameView view;

    /**
     * Игровой цикл
     */
    private Timeline timeline;

    /**
     * Конструктор по умолчанию для FXML.
     */
    public GameController() {
        this.model = new GameModel(new BombFactory());
    }

    /**
     * Конструктор для внедрения зависимостей (для тестирования).
     * @param model игровая модель
     * @param view  игровое представление
     */
    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Инициализирует контроллер после загрузки FXML.
     * Настраивает игровой цикл и обработчики ввода.
     */
    @FXML
    public void initialize() {
        // Проверяет, что холст был инжектирован
        if (gameCanvas == null) {
            throw new IllegalStateException("gameCanvas is not injected by FXML");
        }

        // Инициализирует представление и рендерер после инъекции холста
        Renderer renderer = new Renderer(gameCanvas.getGraphicsContext2D());
        this.view = new GameView(gameCanvas, renderer);

        // Настраивает игровой цикл (обновление каждые 100 мс)
        timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            model.update();
            view.render(model);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Настраивает обработчики ввода мыши
        gameCanvas.setOnMouseMoved(this::handleMouseMoved);
        gameCanvas.setOnMouseClicked(this::handleMouseClicked);
    }

    /**
     * Обрабатывает движение мыши для перемещения игрока.
     * @param event событие мыши
     */
    private void handleMouseMoved(MouseEvent event) {
        model.setPlayerX((int) event.getX());
    }

    /**
     * Обрабатывает клик мыши для выстрела или перезапуска игры.
     * @param event событие мыши
     */
    private void handleMouseClicked(MouseEvent event) {
        model.shoot();
        model.restart();
    }
}