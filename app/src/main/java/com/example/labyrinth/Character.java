package com.example.labyrinth;

import android.graphics.Path;
import android.graphics.Rect;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

public class Character {
    private ImageView imageView;
    private Rect hitbox;
    private boolean isMoving;
    private Path.Direction currentDirection;
    //TEST
    private int test;
    private int x;
    private int y;
    private final int speed;

    public Character(int x, int y, int speed, ImageView imageview) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.imageView = imageview;

        ViewTreeObserver vto = imageView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = imageView.getWidth();

                imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                hitbox = new Rect(x, y, x + width, y + width);
            }
        });
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

    public Rect getHitbox() {
        return hitbox;
    }

    public void moveCharacter(int direction) {
        if (!isMoving) {
            switch (direction) {
                case 1: {
                    this.imageView.setX(imageView.getX() + speed);
                    break;
                }
                case 2:{
                    this.imageView.setX(imageView.getX() - speed);
                    break;
                }
                case 3:{
                    this.imageView.setY(imageView.getY() + speed);
                    break;
                }
                case 4:{
                    this.imageView.setY(imageView.getY() - speed);
                    break;
                }
            }
        }
    }
}
