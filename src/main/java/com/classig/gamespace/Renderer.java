package com.classig.gamespace;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Класс для отрисовки игровых объектов на холсте.
 * Отвечает за все операции рисования, поддерживая SRP и DIP (зависит от абстракции GraphicsContext).
 */
public class Renderer {

    /**
     * Контекст рисования
     */
    private final GraphicsContext gc;

    /**
     * Текущий счёт для модификации стиля выстрелов
     */
    private int score;

    /**
     * Конструктор для инициализации Renderer.
     * @param gc контекст рисования холста
     */
    public Renderer(GraphicsContext gc) {
        this.gc = gc;
    }

    /**
     * Устанавливает текущий счёт для изменения стиля выстрелов.
     * @param score текущий счёт
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Очищает холст, заполняя его фоновым цветом.
     * @param width  ширина холста
     * @param height высота холста
     */
    public void clear(int width, int height) {
        gc.setFill(Color.grayRgb(20));
        gc.fillRect(0, 0, width, height);
    }

    /**
     * Отрисовывает изображение на холсте.
     * @param img  изображение
     * @param x    X-координата
     * @param y    Y-координата
     * @param w    ширина
     * @param h    высота
     */
    public void drawImage(Image img, double x, double y, double w, double h) {
        gc.drawImage(img, x, y, w, h);
    }

    /**
     * Отрисовывает кадр анимации взрыва.
     * @param img  изображение спрайта взрыва
     * @param sx   X-координата кадра в спрайте
     * @param sy   Y-координата кадра в спрайте
     * @param sw   ширина кадра
     * @param sh   высота кадра
     * @param dx   X-координата на холсте
     * @param dy   Y-координата на холсте
     * @param dw   ширина на холсте
     * @param dh   высота на холсте
     */
    public void drawExplosion(Image img, double sx, double sy, double sw, double sh, double dx, double dy, double dw, double dh) {
        gc.drawImage(img, sx, sy, sw, sh, dx, dy, dw, dh);
    }

    /**
     * Отрисовывает выстрел игрока, изменяя стиль в зависимости от счёта.
     * @param shot объект выстрела
     */
    public void drawShot(Shot shot) {
        if (score >= 50 && score <= 70 || score >= 120) {
            // Особый стиль выстрела при определённых значениях счёта
            gc.setFill(Color.YELLOWGREEN);
            shot.setSpeed(50);
            gc.fillRect(shot.getPosX() - 5, shot.getPosY() - 10, shot.getSize() + 10, shot.getSize() + 30);
        } else {
            // Стандартный стиль выстрела
            gc.setFill(Color.RED);
            gc.fillOval(shot.getPosX(), shot.getPosY(), shot.getSize(), shot.getSize());
        }
    }

    /**
     * Отрисовывает звезду фона.
     * @param x       X-координата
     * @param y       Y-координата
     * @param w       ширина
     * @param h       высота
     * @param r       красный компонент цвета
     * @param g       зелёный компонент цвета
     * @param b       синий компонент цвета
     * @param opacity прозрачность
     */
    public void drawUniverse(int x, int y, int w, int h, int r, int g, int b, double opacity) {
        gc.setFill(Color.rgb(r, g, b, opacity));
        gc.fillOval(x, y, w, h);
    }

    /**
     * Отрисовывает текущий счёт.
     * @param score текущий счёт
     */
    public void drawScore(int score) {
        gc.setFill(Color.WHITE);
        gc.setFont(javafx.scene.text.Font.font(20));
        gc.setTextAlign(javafx.scene.text.TextAlignment.CENTER);
        gc.fillText("Score: " + score, 60, 20);
    }

    /**
     * Отрисовывает экран окончания игры.
     * @param score текущий счёт
     * @param width ширина холста
     * @param height высота холста
     */
    public void drawGameOver(int score, int width, int height) {
        gc.setFont(javafx.scene.text.Font.font(35));
        gc.setFill(Color.YELLOW);
        gc.fillText("Game Over \n Your Score is: " + score + " \n Click to play again",
                width / 2, height / 2.5);
    }
}
