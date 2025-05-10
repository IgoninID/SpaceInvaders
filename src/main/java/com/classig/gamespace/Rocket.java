package com.classig.gamespace;

import javafx.scene.image.Image;

/**
 * Базовый класс для игровых объектов типа "ракета" (игрок или враги).
 * Реализует интерфейсы Drawable, Updatable и Collidable для отрисовки, обновления и проверки столкновений.
 * Поддерживает принцип подстановки Лисков (LSP) для наследников, таких как Bomb.
 */
public class Rocket implements Drawable, Updatable, Collidable {

    /**
     * Координата x ракеты
     */
    protected int posX;

    /**
     * Координата y ракеты
     */
    protected int posY;

    /**
     * Размер ракеты
     */
    protected int size;

    /**
     * Флаг состояния взрыва
     */
    protected boolean exploding;

    /**
     * Флаг состояния уничтожения
     */
    protected boolean destroyed;

    /**
     * Изображение ракеты
     */
    protected Image img;

    /**
     * Текущий шаг анимации взрыва
     */
    protected int explosionStep = 0;

    /**
     * Ширина кадра взрыва
     */
    protected static final int EXPLOSION_W = 128;

    /**
     * Высота кадра взрыва
     */
    protected static final int EXPLOSION_H = 128;

    /**
     * Количество строк в спрайте взрыва
     */
    protected static final int EXPLOSION_ROWS = 3;

    /**
     * Количество столбцов в спрайте взрыва
     */
    protected static final int EXPLOSION_COL = 3;

    /**
     * Количество шагов анимации взрыва
     */
    protected static final int EXPLOSION_STEPS = 15;

    /**
     * Изображение взрыва
     */
    protected static final Image EXPLOSION_IMG = new Image("file:src/main/resources/com/classig/gamespace/Images/explosion.png");

    /**
     * Конструктор для создания ракеты.
     * @param posX  X-координата
     * @param posY  Y-координата
     * @param size  размер ракеты
     * @param image изображение ракеты
     */
    public Rocket(int posX, int posY, int size, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.size = size;
        this.img = image;
    }

    /**
     * Создаёт выстрел из текущей позиции ракеты.
     * @return новый объект Shot
     */
    public Shot shoot() {
        return new Shot(posX + size / 2 - Shot.size / 2, posY - Shot.size); // Выстрел создаётся из центра ракеты
    }

    @Override
    public void update() {
        if (exploding) explosionStep++; // Обновляет состояние анимации взрыва, если она активна
        destroyed = explosionStep > EXPLOSION_STEPS; // Устанавливает флаг уничтожения после завершения анимации
    }

    @Override
    public void draw(Renderer renderer) {
        // Отрисовывает взрыв или изображение ракеты в зависимости от состояния
        if (exploding) {
            renderer.drawExplosion(EXPLOSION_IMG, explosionStep % EXPLOSION_COL * EXPLOSION_W,
                    (explosionStep / EXPLOSION_ROWS) * EXPLOSION_H + 1, EXPLOSION_W, EXPLOSION_H, posX, posY, size, size);
        } else {
            renderer.drawImage(img, posX, posY, size, size);
        }
    }

    @Override
    public boolean collide(Collidable other) {
        // Проверяет столкновение с другим объектом по расстоянию между центрами
        int d = distance(this.posX + size / 2, this.posY + size / 2,
                other.getPosX() + other.getSize() / 2, other.getPosY() + other.getSize() / 2);
        return d < other.getSize() / 2 + this.size / 2;
    }

    /**
     * Запускает анимацию взрыва.
     */
    public void explode() {
        exploding = true;
        explosionStep = -1;
    }

    /**
     * Вычисляет расстояние между двумя точками.
     * @param x1 X-координата первой точки
     * @param y1 Y-координата первой точки
     * @param x2 X-координата второй точки
     * @param y2 Y-координата второй точки
     * @return расстояние между точками
     */
    private int distance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    @Override
    public int getPosX() { return posX; }
    @Override
    public int getPosY() { return posY; }
    @Override
    public int getSize() { return size; }

    /**
     * Устанавливает X-координату ракеты.
     * @param posX новая X-координата
     */
    public void setPosX(int posX) { this.posX = posX; }

    /**
     * Проверяет, уничтожена ли ракета.
     * @return true, если ракета уничтожена
     */
    public boolean isDestroyed() { return destroyed; }

    /**
     * Проверяет, находится ли ракета в состоянии взрыва.
     * @return true, если ракета взрывается
     */
    public boolean isExploding() { return exploding; }
}