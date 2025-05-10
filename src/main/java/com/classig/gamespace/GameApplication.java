package com.classig.gamespace;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Главный класс приложения, запускающий игру Space Invaders.
 * Загружает FXML и настраивает сцену.
 */
public class GameApplication extends Application {

    /**
     * Запускает приложение, создавая окно и загружая FXML.
     * @param stage главная сцена приложения
     * @throws Exception при ошибке загрузки FXML
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("Game.fxml")); // Загружает FXML-файл
        Scene scene = new Scene(fxmlLoader.load(), 800, 600); // Создаёт сцену
        stage.setTitle("Space invaders"); // Настраивает окно
        stage.getIcons().add(new Image("file:src/main/resources/com/classig/gamespace/Images/icon.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}