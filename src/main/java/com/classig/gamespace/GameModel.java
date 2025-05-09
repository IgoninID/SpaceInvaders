package com.classig.gamespace;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class GameModel {
    private static final Random RAND = new Random();
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PLAYER_SIZE = 60;
    private static final int MAX_BOMBS = 10;
    private static final int MAX_SHOTS = MAX_BOMBS * 2;
    private static final Image PLAYER_IMG = new Image("file:src/main/resources/com/classig/gamespace/Images/Player.png");
    private static final Image[] BOMBS_IMG = {
            new Image("file:src/main/resources/com/classig/gamespace/Images/alien1.png"), new Image("file:src/main/resources/com/classig/gamespace/Images/alien2.png"),
            new Image("file:src/main/resources/com/classig/gamespace/Images/alien3.png"), new Image("file:src/main/resources/com/classig/gamespace/Images/alien4.png"),
            new Image("file:src/main/resources/com/classig/gamespace/Images/alien5.png"), new Image("file:src/main/resources/com/classig/gamespace/Images/alien6.png"),
            new Image("file:src/main/resources/com/classig/gamespace/Images/alien7.png"), new Image("file:src/main/resources/com/classig/gamespace/Images/alien8.png"),
            new Image("file:src/main/resources/com/classig/gamespace/Images/alien9.png"), new Image("file:src/main/resources/com/classig/gamespace/Images/alien10.png")
    };

    private Rocket player;
    private List<Shot> shots;
    private List<Universe> universe;
    private List<Bomb> bombs;
    private boolean gameOver;
    private int score;

    public GameModel() {
        setup();
    }

    private void setup() {
        universe = new ArrayList<>();
        shots = new ArrayList<>();
        bombs = new ArrayList<>();
        player = new Rocket(WIDTH / 2, HEIGHT - PLAYER_SIZE, PLAYER_SIZE, PLAYER_IMG);
        score = 0;
        gameOver = false;
        IntStream.range(0, MAX_BOMBS).mapToObj(i -> newBomb()).forEach(bombs::add);
    }

    public void update() {
        if (gameOver) return;

        // Update player
        player.update();
        if (player.isDestroyed()) gameOver = true;

        // Update bombs
        bombs.forEach(bomb -> {
            bomb.update();
            if (player.collide(bomb) && !player.isExploding() && !bomb.isExploding()) {
                player.explode();
            }
        });

        // Update shots
        for (int i = shots.size() - 1; i >= 0; i--) {
            Shot shot = shots.get(i);
            if (shot.getPosY() < 0 || shot.isToRemove()) {
                shots.remove(i);
                continue;
            }
            shot.update();
            for (Bomb bomb : bombs) {
                if (shot.collide(bomb) && !bomb.isExploding()) {
                    score++;
                    bomb.explode();
                    shot.setToRemove(true);
                }
            }
        }

        // Replace destroyed bombs
        for (int i = bombs.size() - 1; i >= 0; i--) {
            if (bombs.get(i).isDestroyed()) {
                bombs.set(i, newBomb());
            }
        }

        // Update universe
        if (RAND.nextInt(10) > 2) {
            universe.add(new Universe());
        }
        universe.removeIf(u -> u.getPosY() > HEIGHT);
    }

    public Bomb newBomb() {
        return new Bomb(50 + RAND.nextInt(WIDTH - 100), 0, PLAYER_SIZE, BOMBS_IMG[RAND.nextInt(BOMBS_IMG.length)], score);
    }

    public void shoot() {
        if (shots.size() < MAX_SHOTS) {
            shots.add(player.shoot());
        }
    }

    public void restart() {
        if (gameOver) {
            gameOver = false;
            setup();
        }
    }

    // Getters and setters
    public Rocket getPlayer() { return player; }
    public List<Shot> getShots() { return shots; }
    public List<Universe> getUniverse() { return universe; }
    public List<Bomb> getBombs() { return bombs; }
    public boolean isGameOver () { return gameOver; }
    public int getScore() { return score; }
    public void setPlayerX(int x) { player.setPosX(x); }
}