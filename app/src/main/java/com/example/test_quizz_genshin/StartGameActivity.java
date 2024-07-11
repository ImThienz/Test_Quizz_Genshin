package com.example.test_quizz_genshin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class StartGameActivity extends AppCompatActivity {
    TextView tvTimer;
    TextView tvResult;
    ImageView ivShowImage;
    // Instantiate two HashMaps to store technology names with correct and wrong image resource ids
    HashMap<String, Integer> map = new HashMap<>();
    HashMap<String, Integer> correctImageMap = new HashMap<>();
    HashMap<String, Integer> wrongImageMap = new HashMap<>();

    // An ArrayList for storing technology names only
    ArrayList<String> techList = new ArrayList<>();
    int index;
    Button btn1, btn2, btn3, btn4;
    TextView tvPoints;
    int points;
    CountDownTimer countDownTimer;

    long millisUntilFinished;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        tvTimer = findViewById(R.id.tvTimer);
        tvResult = findViewById(R.id.tvResult);
        ivShowImage = findViewById(R.id.ivShowImage);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        tvPoints = findViewById(R.id.tvPoints);
        // Initialize index with 0
        index = 0;
        // Populate techList with all the technology names
        techList.add("Furina De Fontaine");
        techList.add("Clorinde");
        techList.add("Navia");
        techList.add("Raiden Ei");
        techList.add("Hu Tao");
        techList.add("Kamisato Ayaka");
        techList.add("Yelan");
        techList.add("Arlecchino");
        techList.add("Naganohara Yoimiya");
        techList.add("Chiori");



        // Put all the technology names with technology image resource ids in map.
        map.put(techList.get(0), R.drawable.furina);
        map.put(techList.get(1), R.drawable.clorinde);
        map.put(techList.get(2), R.drawable.navia);
        map.put(techList.get(3), R.drawable.ei);
        map.put(techList.get(4), R.drawable.hutao);
        map.put(techList.get(5), R.drawable.ayaka);
        map.put(techList.get(6), R.drawable.yelan);
        map.put(techList.get(7), R.drawable.arlecchino);
        map.put(techList.get(8), R.drawable.yoimiya);
        map.put(techList.get(9), R.drawable.chiori);




        correctImageMap.put(techList.get(0), R.drawable.furina_right);
        correctImageMap.put(techList.get(1), R.drawable.clorinde_right);
        correctImageMap.put(techList.get(2), R.drawable.navia_right);
        correctImageMap.put(techList.get(3), R.drawable.ei_right);
        correctImageMap.put(techList.get(4), R.drawable.hutao_right);
        correctImageMap.put(techList.get(5), R.drawable.ayaka_right);
        correctImageMap.put(techList.get(6), R.drawable.yelan_right);
        correctImageMap.put(techList.get(7), R.drawable.arlecchino_right);
        correctImageMap.put(techList.get(8), R.drawable.yoimiya_right);
        correctImageMap.put(techList.get(9), R.drawable.chiori_right);




        wrongImageMap.put(techList.get(0), R.drawable.furina_wrong);
        wrongImageMap.put(techList.get(1), R.drawable.clorinde_wrong);
        wrongImageMap.put(techList.get(2), R.drawable.navia_wrong);
        wrongImageMap.put(techList.get(3), R.drawable.ei_wrong);
        wrongImageMap.put(techList.get(4), R.drawable.hutao_wrong);
        wrongImageMap.put(techList.get(5), R.drawable.ayaka_wrong);
        wrongImageMap.put(techList.get(6), R.drawable.yelan_wrong);
        wrongImageMap.put(techList.get(7), R.drawable.arlecchino_wrong);
        wrongImageMap.put(techList.get(8), R.drawable.yoimiya_wrong);
        wrongImageMap.put(techList.get(9), R.drawable.chiori);




        // a random question
        Collections.shuffle(techList);
        millisUntilFinished = 10000;
        points = 0;
        startGame();
    }

    private void startGame() {
        // Initialize millisUntilFinished with 10 seconds.
        millisUntilFinished = 10000;
        tvTimer.setText("" + (millisUntilFinished / 1000) + "s");
        tvPoints.setText(points + " / " + techList.size());
        generateQuestions(index);
        countDownTimer = new CountDownTimer(millisUntilFinished, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update tvTimer every 1 second to show the number of seconds remaining.
                tvTimer.setText("" + (millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                index++;
                // When timer is finished check if all questions are being asked.
                if (index > techList.size() - 1){
                    // If true, hide the ImageView and Buttons.
                    ivShowImage.setVisibility(View.GONE);
                    btn1.setVisibility(View.GONE);
                    btn2.setVisibility(View.GONE);
                    btn3.setVisibility(View.GONE);
                    btn4.setVisibility(View.GONE);
                    // Go to GameOver screen with points using an Intent
                    Intent intent = new Intent(StartGameActivity.this, GameOverActivity.class);
                    intent.putExtra("points", points);
                    startActivity(intent);
                    finish();
                } else {
                    countDownTimer = null;
                    startGame();
                }
            }
        }.start();
    }

    private void generateQuestions(int index) {
        // Clone techList to a new ArrayList called techListTemp.
        ArrayList<String> techListTemp = (ArrayList<String>) techList.clone();
        String correctAnswer = techList.get(index);

        // Shuffle it and get first three elements from it.
        techListTemp.remove(correctAnswer);
        Collections.shuffle(techListTemp);

        ArrayList<String> newList = new ArrayList<>();
        // Get first three elements from techListTemp and add into newList.
        newList.add(techListTemp.get(0));
        newList.add(techListTemp.get(1));
        newList.add(techListTemp.get(2));
        newList.add(correctAnswer);

        Collections.shuffle(newList);
        btn1.setText(newList.get(0));
        btn2.setText(newList.get(1));
        btn3.setText(newList.get(2));
        btn4.setText(newList.get(3));
        ivShowImage.setImageResource(map.get(techList.get(index)));
    }

    public void nextQuestion(View view) {
        btn1.setBackgroundColor(Color.parseColor("#2196f3"));
        btn2.setBackgroundColor(Color.parseColor("#2196f3"));
        btn3.setBackgroundColor(Color.parseColor("#2196f3"));
        btn4.setBackgroundColor(Color.parseColor("#2196f3"));
        // Enable the buttons
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        // Cancel the countDownTimer
        countDownTimer.cancel();
        index++;
        // Check if all questions have been asked.
        if (index > techList.size() - 1){
            // If true, hide the ImageView and Buttons.
            ivShowImage.setVisibility(View.GONE);
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
            btn4.setVisibility(View.GONE);
            // Go to GameOver screen with points
            Intent intent = new Intent(StartGameActivity.this, GameOverActivity.class);
            intent.putExtra("points", points);
            startActivity(intent);
            finish();
        } else {
            countDownTimer = null;
            startGame();
        }
    }

    public void answerSelected(View view) {
        view.setBackgroundColor(Color.parseColor("#17243e"));
        // Disable all four Buttons
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        // The user has selected an answer, so, cancel the countDownTimer
        countDownTimer.cancel();
        String answer = ((Button) view).getText().toString().trim();

        String correctAnswer = techList.get(index);
        if(answer.equals(correctAnswer)){
            points++;
            tvPoints.setText(points + " / " + techList.size());
            tvResult.setText("Good! You did such a great job !!");
            ivShowImage.setImageResource(correctImageMap.get(correctAnswer));
        } else {
            tvResult.setText("Wrong Answer! You don't remember me ?");
            ivShowImage.setImageResource(wrongImageMap.get(correctAnswer));
        }
    }
}