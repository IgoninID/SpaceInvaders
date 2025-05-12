package com.classig.gamespace;

/**
 * Класс для управления игровым счётом.
 * Соответствует принцип единственной ответственности(Srp), отвечая только за подсчёт и сброс очков.
 */
public class ScoreManager {

    /**
     * Текущий счёт игрока
     */
    private int score;

    /**
     * Конструктор для инициализации менеджера счёта.
     */
    public ScoreManager() {
        this.score = 0;
    }

    /**
     * Увеличивает счёт на 1.
     */
    public void incrementScore() {
        score++;
    }

    /**
     * Сбрасывает счёт до 0.
     */
    public void resetScore() {
        score = 0;
    }

    /**
     * Возвращает текущий счёт.
     * @return текущий счёт
     */
    public int getScore() {
        return score;
    }
}
