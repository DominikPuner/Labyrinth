package com.example.labyrinth;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SinglePlayer extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private GestureDetector gestureDetector;

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

        gestureDetector = new GestureDetector(this, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
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
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
        assert e1 != null;
        float deltaX = e2.getX() - e1.getX();
        float deltaY = e2.getY() - e1.getY();

        float absDeltaX = Math.abs(deltaX);
        float absDeltaY = Math.abs(deltaY);

        // Schwellenwerte für minimale Distanz und Geschwindigkeit
        float SWIPE_THRESHOLD = 100;
        float SWIPE_VELOCITY_THRESHOLD = 100;

        TextView textview = findViewById(R.id.richtungAusgabe);
        ImageView characterView = findViewById(R.id.characterImage);
        Character player = new Character(50, 50, 100, characterView);

        if (absDeltaX > absDeltaY) { // Horizontale Bewegung
            if (absDeltaX > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (deltaX > 0) {
                    player.moveCharacter(1);
                    textview.setText("rechts");
                } else {
                    player.moveCharacter(2);

                    textview.setText("links");
                }
            }
        } else { // Vertikale Bewegung
            if (absDeltaY > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (deltaY > 0) {
                    player.moveCharacter(3);

                    textview.setText("unten");
                } else {
                    player.moveCharacter(4);

                    textview.setText("oben");
                }
            }
        }
        return true;
    }
}
