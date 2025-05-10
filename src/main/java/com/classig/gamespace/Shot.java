package com.classig.gamespace;

/**
 * Класс для выстрелов игрока.
 * Реализует Drawable, Updatable и Collidable для отрисовки, обновления и проверки столкновений.
 */
public class Shot implements Drawable, Updatable, Collidable {

    /**
     * Координата x выстрела
     */
    private int posX;

    /**
     * Координата y выстрела
     */
    private int posY;

    /**
     * Скорость движения выстрела
     */
    private int speed = 10;

    /**
     * Флаг для удаления выстрела
     */
    public boolean toRemove;

    /**
     * Размер выстрела
     */
    public static final int size = 6;

    /**
     * Конструктор для создания выстрела.
     * @param posX X-координата
     * @param posY Y-координата
     */
    public Shot(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public void update() {
        posY -= speed; // Перемещает выстрел вверх
    }

    @Override
    public void draw(Renderer renderer) {
        renderer.drawShot(this); // Отрисовывает выстрел с использованием Renderer
    }

    @Override
    public boolean collide(Collidable other) {
        // Проверяет столкновение с другим объектом
        int distance = distance(this.posX + size / 2, this.posY + size / 2,
                other.getPosX() + other.getSize() / 2, other.getPosY() + other.getSize() / 2);
        return distance < other.getSize() / 2 + size / 2;
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

    /**
     * Возвращает Y-координату выстрела.
     * @return Y-координата
     */
    public int getPosY() { return posY; }

    /**
     * Проверяет, нужно ли удалить выстрел.
     * @return true, если выстрел помечен для удаления
     */
    public boolean isToRemove() { return toRemove; }

    /**
     * Устанавливает флаг удаления выстрела.
     * @param toRemove флаг удаления
     */
    public void setToRemove(boolean toRemove) { this.toRemove = toRemove; }

    @Override
    public int getPosX() { return posX; }

    @Override
    public int getSize() { return size; }

    /**
     * Возвращает скорость выстрела.
     * @return скорость
     */
    public int getSpeed() { return speed; }

    /**
     * Устанавливает скорость выстрела.
     * @param speed новая скорость
     */
    public void setSpeed(int speed) { this.speed = speed; }
}