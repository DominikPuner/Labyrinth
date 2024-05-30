package com.example.labyrinth;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import java.util.List;

public class Character {
    private final ImageView imageView;
    private Rect hitbox = new Rect();
    private boolean isMoving = false;
    private final int speed;

    public Character(int x, int y, int speed, ImageView imageview) {
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

    private boolean checkCollision(List<ImageView> obstacles){
        for (ImageView obstacle : obstacles) {
            Rect obstacleRect = new Rect();
            obstacle.getHitRect(obstacleRect);
            imageView.getHitRect(hitbox);
            if (hitbox.intersect(obstacleRect)) {
                isMoving = false;
                return true;
            }
        }
        return false;
    }

    private ImageView checkCollisionInDirection(int direction, List<ImageView> obstacles){
        Rect projectedHitbox = new Rect(hitbox); // Copy current hitbox
        for(int i = 0; i < 400; i++){
            switch (direction) {
                case 1: // Right
                    projectedHitbox.offset(speed, 0);
                    break;
                case 2: // Left
                    projectedHitbox.offset(-speed, 0);
                    break;
                case 3: // Down
                    projectedHitbox.offset(0, speed);
                    break;
                case 4: // Up
                    projectedHitbox.offset(0, -speed);
                    break;
            }

            for (ImageView obstacle : obstacles) {
                Rect obstacleRect = new Rect();
                obstacle.getHitRect(obstacleRect);
                if (projectedHitbox.intersect(obstacleRect)) {
                    return obstacle; // Collision detected in the given direction
                }
            }
        }

        return null; // No collision
    }

    private boolean lookingRigth = true;
    public void moveCharacter(int direction, List<ImageView> obstacles) {

        if (!isMoving) {
            ImageView iv = checkCollisionInDirection(direction, obstacles);
            if(iv != null){
                float targetX, targetY;
                switch(direction){
                    case 1:{
                        targetX = iv.getX() - imageView.getWidth();
                        targetY = imageView.getY();
                    }
                }
            }

            switch (direction) {
                case 1:
                    targetX += speed;
                    imageView.setImageResource(R.drawable.dashing_looking_right);
                    lookingRigth = true;
                    break;
                case 2:
                    targetX -= speed;
                    imageView.setImageResource(R.drawable.dashing_looking_left);
                    lookingRigth = false;
                    break;
                case 3:
                    targetY += speed;
                    if(lookingRigth){
                        imageView.setImageResource(R.drawable.dashing_down_looking_right);
                    }else{
                        imageView.setImageResource(R.drawable.dashing_down_looking_left);
                    }
                    break;
                case 4:
                    targetY -= speed;
                    if(lookingRigth){
                        imageView.setImageResource(R.drawable.dashing_up_looking_right);
                    }else{
                        imageView.setImageResource(R.drawable.dashing_up_looking_left);
                    }
                    break;
            }

            if(checkCollision(obstacles))
                return;

            isMoving = true;

            ObjectAnimator animatorX = ObjectAnimator.ofFloat(imageView, "x", targetX);
            ObjectAnimator animatorY = ObjectAnimator.ofFloat(imageView, "y", targetY);
            animatorX.setDuration(speed);
            animatorY.setDuration(speed);

            animatorX.setInterpolator(null); // Use linear interpolation for constant speed
            animatorY.setInterpolator(null);

            animatorX.start();
            animatorY.start();

            animatorX.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    imageView.getHitRect(hitbox);
                    if(lookingRigth){
                        imageView.setImageResource(R.drawable.standing_looking_right);
                    }
                    else {
                        imageView.setImageResource(R.drawable.standing_looking_left);
                    }
                }
            });
        }
    }
}