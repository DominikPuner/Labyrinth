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

    private GestureDetector gestureDetector;List<ImageView> viewList = new ArrayList<>();

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


        gestureDetector = new GestureDetector(this, this);

        setMauern();
    }

    private void setMauern() {
        viewList.add(findViewById(R.id.wallImageBottom));
        viewList.add(findViewById(R.id.wallImageLeft));
        viewList.add(findViewById(R.id.wallImageRight));
        viewList.add(findViewById(R.id.wallImageTop));
        viewList.add(findViewById(R.id.wallImage1));
        viewList.add(findViewById(R.id.wallImage2));
        viewList.add(findViewById(R.id.wallImage3));
        viewList.add(findViewById(R.id.wallImage4));
        viewList.add(findViewById(R.id.wallImage5));
        viewList.add(findViewById(R.id.wallImage6));
        viewList.add(findViewById(R.id.wallImage7));
        viewList.add(findViewById(R.id.wallImage8));
        viewList.add(findViewById(R.id.wallImage9));
        viewList.add(findViewById(R.id.wallImage10));
        viewList.add(findViewById(R.id.wallImage11));
        viewList.add(findViewById(R.id.wallImage12));
        viewList.add(findViewById(R.id.wallImage13));
        viewList.add(findViewById(R.id.wallImage14));
        viewList.add(findViewById(R.id.wallImage15));
        viewList.add(findViewById(R.id.wallImage16));
        viewList.add(findViewById(R.id.wallImage17));
        viewList.add(findViewById(R.id.wallImage18));
        viewList.add(findViewById(R.id.wallImage19));
        viewList.add(findViewById(R.id.wallImage20));
        viewList.add(findViewById(R.id.wallImage21));
        viewList.add(findViewById(R.id.wallImage22));
        viewList.add(findViewById(R.id.wallImage23));
        viewList.add(findViewById(R.id.wallImage24));
        viewList.add(findViewById(R.id.wallImage25));
        viewList.add(findViewById(R.id.wallImage26));
        viewList.add(findViewById(R.id.wallImage27));
        viewList.add(findViewById(R.id.wallImage28));
        viewList.add(findViewById(R.id.wallImage29));
        viewList.add(findViewById(R.id.wallImage30));
        viewList.add(findViewById(R.id.wallImage31));
        viewList.add(findViewById(R.id.wallImage32));
        viewList.add(findViewById(R.id.wallImage33));
        viewList.add(findViewById(R.id.wallImage34));
        viewList.add(findViewById(R.id.wallImage35));
        viewList.add(findViewById(R.id.wallImage36));
        viewList.add(findViewById(R.id.wallImage37));
        viewList.add(findViewById(R.id.wallImage38));
        viewList.add(findViewById(R.id.wallImage39));
        viewList.add(findViewById(R.id.wallImage40));
        viewList.add(findViewById(R.id.wallImage41));
        viewList.add(findViewById(R.id.wallImage42));
        viewList.add(findViewById(R.id.wallImage43));
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
        Character player = new Character(characterView.getWidth(), characterView.getHeight(), characterView.getWidth(), characterView);

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
