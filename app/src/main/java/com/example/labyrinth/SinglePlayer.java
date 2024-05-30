package com.example.labyrinth;


import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SinglePlayer extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private GestureDetector gestureDetector;
    List<ImageView> viewList = new ArrayList<>();

    @Override
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleplayer_activity);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        ImageView characterImageView = findViewById(R.id.characterImage);

        int characterImageResource = R.drawable.standing_looking_right;
        characterImageView.setImageResource(characterImageResource);
        characterImageView.setX(10);
        characterImageView.setY(10);

        gestureDetector = new GestureDetector(this, this);

        setMauern();
    }

    private void setMauern() {
        viewList.add(findViewById(R.id.wallImageBottom));
        viewList.add(findViewById(R.id.wallImageLeft));
        viewList.add(findViewById(R.id.wallImageRight));
        viewList.add(findViewById(R.id.wallImageTop));

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(@NonNull MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        assert e1 != null;
        float deltaX = e2.getX() - e1.getX();
        float deltaY = e2.getY() - e1.getY();

        float absDeltaX = Math.abs(deltaX);
        float absDeltaY = Math.abs(deltaY);

        // Schwellenwerte für minimale Distanz und Geschwindigkeit
        float SWIPE_THRESHOLD = 100;
        float SWIPE_VELOCITY_THRESHOLD = 100;

        ImageView characterView = findViewById(R.id.characterImage);
        Character player = new Character(characterView.getWidth(), characterView.getHeight(), characterView.getWidth(), characterView, characterView.getWidth());

        if (absDeltaX > absDeltaY) { // Horizontale Bewegung
            if (absDeltaX > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (deltaX > 0) {
                    player.moveCharacter(1, viewList);
                } else {
                    player.moveCharacter(2, viewList);
                }
            }
        } else { // Vertikale Bewegung
            if (absDeltaY > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (deltaY > 0) {
                    player.moveCharacter(3, viewList);
                } else {
                    player.moveCharacter(4, viewList);
                }
            }
        }

        return true;
    }
}
