package com.example.labyrinth;

import android.graphics.Path;
import android.graphics.Rect;
import android.widget.ImageView;

public class Character {
    private ImageView imageView;
    private Rect hitbox;
    private boolean isMoving;
    private Path.Direction currentDirection;
    //TEST
    private int test;
    private int x, y, speed;

    public Character(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }
}
