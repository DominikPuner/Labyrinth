package com.example.labyrinth;

import android.content.Context;
import android.graphics.Rect;
import android.widget.ImageView;

import java.util.List;

public class CharacterNew extends androidx.appcompat.widget.AppCompatImageView {
    protected enum Direction {UP, DOWN, LEFT, RIGHT}
    private Direction currentDirection = Direction.RIGHT;
    private final Rect hitbox;

    public CharacterNew(Context context) {
        super(context);
        this.hitbox = new Rect();
    }

    public void setDirection(Direction newDirection){
        this.currentDirection = newDirection;
    }

    private void updateHitbox(){
        hitbox.set((int)getX(), (int)getY(), (int)getX() + getWidth(), (int)getY() + getHeight());
    }

    private boolean checkCollision(List<ImageView> obstacles){
        for (ImageView obstacle : obstacles) {
            Rect obstacleRect = new Rect();
            obstacle.getHitRect(obstacleRect);
            this.getHitRect(hitbox);
            if (hitbox.intersect(obstacleRect)) {
                return true;
            }
        }
        return false;
    }

    public void move(List<ImageView> obstacles) {
        updateHitbox(); // Hitbox aktualisieren, bevor wir uns bewegen

        int speed = 10;
        switch (currentDirection) {
            case UP:    this.setY(this.getY() - speed); break;
            case DOWN:  this.setY(this.getY() + speed); break;
            case LEFT:  this.setX(this.getX() - speed); break;
            case RIGHT: this.setX(this.getX() + speed); break;
        }

        // Kollisionserkennung (hier musst du die Wand-ImageViews überprüfen)
        if (checkCollision(obstacles)) {
            // Bewegung rückgängig machen, wenn Kollision erkannt wird
            switch (currentDirection) {
                case UP:    this.setY(this.getY() + speed); break;
                case DOWN:  this.setY(this.getY() - speed); break;
                case LEFT:  this.setX(this.getX() + speed); break;
                case RIGHT: this.setX(this.getX() - speed); break;
            }
        }
    }
}
