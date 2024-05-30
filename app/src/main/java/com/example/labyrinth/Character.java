package com.example.labyrinth;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import java.util.List;

public class Character {
    private int gridSize;
    private ImageView imageView;
    private Rect hitbox = new Rect();
    private boolean isMoving = false;
    private final int speed;

    public Character(int x, int y, int speed, ImageView imageview, int gridSize) {
        this.speed = speed;
        this.imageView = imageview;

        this.gridSize = gridSize;

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

    public Rect getHitbox() {
        return hitbox;
    }

    private boolean lookingRigth = true;
    public void moveCharacter(int direction, List<ImageView> obstacles) {
        if (!isMoving) {
            float targetX = imageView.getX();
            float targetY = imageView.getY();

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

            Rect targetRect = new Rect((int) targetX, (int) targetY, (int) targetX + imageView.getWidth(), (int) targetY + imageView.getHeight());
            for (ImageView obstacle : obstacles) {
                Rect obstacleRect = new Rect();
                obstacle.getHitRect(obstacleRect);
                if (targetRect.intersect(obstacleRect)) {
                    isMoving = false;
                    return;
                }
            }

            isMoving = true;

            ObjectAnimator animatorX = ObjectAnimator.ofFloat(imageView, "x", targetX);
            ObjectAnimator animatorY = ObjectAnimator.ofFloat(imageView, "y", targetY);
            animatorX.setDuration(speed/3);
            animatorY.setDuration(speed/3);

            animatorX.start();
            animatorY.start();

            animatorX.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    imageView.getHitRect(hitbox);
                }
            });
        }
    }
}