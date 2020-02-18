package com.mega.games.gamestartingkit.core.gameObjects.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mega.games.gamestartingkit.core.gameObjects.baseObjects.Box;
import com.mega.games.gamestartingkit.core.gameObjects.baseObjects.Slot;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class Board extends Box {

    private int dimension;
    private float slot_size;
    private int[][] state;
    private int turn;
    private ArrayList<Coin> coins;

    public Board(int dimension, float size, Color color) {
        super(size, size, color);

        if (dimension%2 != 0) {
            throw new InvalidParameterException("Board dimensions should always be an even number");
        }

        this.dimension = dimension;
        this.slot_size = size/((float) dimension);

        state = new int[dimension][dimension];
        state[dimension/2 - 1][dimension/2 - 1] = -1;
        state[dimension/2 - 1][dimension/2] = 1;
        state[dimension/2][dimension/2 - 1] = 1;
        state[dimension/2][dimension/2] = -1;

        turn = -1;
    }

    public int getTurn() {
        return turn;
    }

    @Override
    public void onTouchDown(float x, float y) {
        super.onTouchDown(x, y);

        int i, j;

        x = x - this.getPos().x;
        y = y - this.getPos().y;

        if ((x < 0) || (x > this.getSize().x) || (y < 0) || (y > this.getSize().y)) {
            return;
        }

        i = (int) (x / this.slot_size);
        j = (int) (y / this.slot_size);
        System.out.println(i + " " + j);

        int score_inc = move(turn, i, j);

        if (score_inc > 0) {
            turn *= -1;
        }
    }

    @Override
    public void onTouchUp(float x, float y) {
        super.onTouchUp(x, y);
    }

    @Override
    public void onTouchDragged(float x, float y) {
        super.onTouchDragged(x, y);
    }

    @Override
    public void update(float dt) {
        coins = this.getCoins();
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);

        Slot[][] slots = new Slot[dimension][dimension];
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                slots[i][j] = new Slot(this.slot_size, Color.BLACK);
                slots[i][j].setPos(this.getPos().x + (i * this.slot_size), this.getPos().y + (j * this.slot_size));
                slots[i][j].draw(batch);
            }
        }

        for (Coin coin:coins){
            coin.draw(batch);
        }
    }

    private ArrayList<Coin> getCoins() {
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (state[i][j] == 0) {
                    continue;
                }
                Coin new_coin =  new Coin((this.slot_size - 0.2f) / 2f, Color.BLACK);
                if (state[i][j] == 1) {
                    new_coin.setColor(Color.WHITE);
                } // else let it remain black

                new_coin.setPos(this.getPos().x + i * this.slot_size + this.slot_size/2f,
                        this.getPos().y + j * this.slot_size + this.slot_size/2f);

                coins.add(new_coin);
            }
        }
        return coins;
    }

    private int move(int turn, int i, int j) {
        if (turn != 1 && turn != -1) {
            throw new InvalidParameterException("turn should only be -1 (Black) or 1 (White)");
        }

        int[][] directions = {{1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}};
        int score = 0;
        for (int[] direction:directions) {
            int x = i, y = j;
            int score_inc = 0;
            System.out.println("\tDirection changed to " + direction[0] + ":" + direction[1]);
            x += direction[0];
            y += direction[1];
            while (x < dimension && x >= 0 && y >= 0 && y < dimension) {
                System.out.println("\t" + x + ":" + y + "\t\tstate:" + state[x][y] + "\tscore_inc:" + score_inc);
                if (state[x][y] == 0) {
                    break;
                } else if (state[x][y] == turn) {
                    if (score_inc > 0) {
                        while (x != i || y != j) {
                            x -= direction[0];
                            y -= direction[1];
                            state[x][y] = turn;
                        }
                        score += score_inc;
                    }
                    break;
                } else if (state[x][y] == -1 * turn) {
                    score_inc++;
                }
                x += direction[0];
                y += direction[1];
            }
        }

        return score;
    }
}
