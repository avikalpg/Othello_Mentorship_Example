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
    private Slot[][] slots;

    public Board(int dimension, float size, Color color) {
        super(size, size, color);

        if (dimension%2 != 0) {
            throw new InvalidParameterException("Board dimensions should always be an even number");
        }

        this.dimension = dimension;

        this.slot_size = size/((float) dimension);
        slots = new Slot[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                slots[i][j] = new Slot(this.slot_size, Color.BLACK);
            }
        }
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
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                slots[i][j].setPos(this.getPos().x + (i * this.slot_size), this.getPos().y + (j * this.slot_size));
                slots[i][j].draw(batch);
            }
        }
    }

    public ArrayList<Coin> getStartingCoins() {
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Coin new_coin =  new Coin((this.slot_size - 0.2f) / 2f, Color.BLACK);
                if (i + j == 1) {
                    new_coin.setColor(Color.WHITE);
                } // else let it remain black

                new_coin.setPos(this.getPos().x + this.getSize().x/2f + (2*i - 1) * this.slot_size/2f,
                        this.getPos().y + this.getSize().y/2f + (2*j - 1) * this.slot_size/2f);

                coins.add(new_coin);
            }
        }
        return coins;
    }
}
