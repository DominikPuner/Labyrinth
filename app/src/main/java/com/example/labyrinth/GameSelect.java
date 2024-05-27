package com.example.labyrinth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GameSelect extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_gamemode);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        Button back = findViewById(R.id.button_gamemode_back);
        back.setOnClickListener(v -> {
            Intent MainMenu = new Intent(this, MainActivity.class);
            startActivity(MainMenu);
        });

        Button singlePlayer = findViewById(R.id.singleplayer_button);
        singlePlayer.setOnClickListener(v -> {
            Intent SinglePlayer = new Intent(GameSelect.this, SinglePlayer.class);
            startActivity(SinglePlayer);

        });

    }
}