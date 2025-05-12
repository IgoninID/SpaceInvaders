package com.classig.gamespace;

/**
 * Интерфейс фабрики для создания вражеских объектов.
 * Поддерживает принцип открытости/закрытости (Ocp), позволяя добавлять новые типы врагов без изменения кода.
 */
public interface EnemyFactory {

    /**
     * Создаёт новый вражеский объект с заданными параметрами.
     * @param posX  X-координата
     * @param posY  Y-координата
     * @param size  размер объекта
     * @param score текущий счёт игрока
     * @return новый объект типа Rocket
     */
    Rocket createEnemy(int posX, int posY, int size, int score);
}
