package com.example.test_quizz_genshin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout levelButtonsLayout;
    private boolean isLevelButtonsVisible = false; // to track if visible

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        levelButtonsLayout = findViewById(R.id.levelButtonsLayout);
    }

    // public void startGame(View view) {
    //     Intent intent = new Intent(MainActivity.this, StartGameActivity.class);
    //     startActivity(intent);
    //     finish();
    // }

    public void toggleLevelButtons(View view) {
    if (isLevelButtonsVisible) {
        // Hide level buttons
        levelButtonsLayout.setVisibility(View.GONE);
        isLevelButtonsVisible = false;
    } else {
        // Show level buttons
        levelButtonsLayout.setVisibility(View.VISIBLE);
        isLevelButtonsVisible = true;
    }
}

    public void startGameEasy(View view) {
        startGameWithLevel("easy");
    }

    public void startGameMedium(View view) {
        startGameWithLevel("medium");
    }

    public void startGameHard(View view) {
        startGameWithLevel("hard");
    }

    private void startGameWithLevel(String level) {
        Intent intent = new Intent(MainActivity.this, StartGameActivity.class);
        intent.putExtra("level", level);
        startActivity(intent);
        finish();
    }
}