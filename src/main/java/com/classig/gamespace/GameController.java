package com.classig.gamespace;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class GameController {
    @FXML
    private Canvas gameCanvas;

    private GameModel model;
    private GameView view;
    private Timeline timeline;

    @FXML
    public void initialize() {
        model = new GameModel();
        view = new GameView(gameCanvas);

        // Set up game loop
        timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            model.update();
            view.render(model);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Set up input handling
        gameCanvas.setOnMouseMoved(this::handleMouseMoved);
        gameCanvas.setOnMouseClicked(this::handleMouseClicked);
    }

    private void handleMouseMoved(MouseEvent event) {
        model.setPlayerX((int) event.getX());
    }

    private void handleMouseClicked(MouseEvent event) {
        model.shoot();
        model.restart();
    }
}