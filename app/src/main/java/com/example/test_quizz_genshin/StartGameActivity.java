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
import java.util.List;
import java.util.Arrays;
import java.util.Random;
import android.util.Log;

public class StartGameActivity extends AppCompatActivity {
    TextView tvTimer;
    TextView tvResult;
    ImageView ivShowImage;
    // Instantiate two HashMaps to store technology names with correct and wrong image resource ids
    HashMap<String, Integer> map = new HashMap<>();
    HashMap<String, Integer> correctImageMap = new HashMap<>();
    HashMap<String, Integer> wrongImageMap = new HashMap<>();
    HashMap<String, List<String>> correctDialogues = new HashMap<>();
    HashMap<String, List<String>> wrongDialogues = new HashMap<>();

    // An ArrayList for storing technology names only
    ArrayList<String> techList = new ArrayList<>();
    int index;
    Button btn1, btn2, btn3, btn4;
    TextView tvPoints;
    int points;
    CountDownTimer countDownTimer;

    long millisUntilFinished;
    String level = "easy"; // default

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
        wrongImageMap.put(techList.get(9), R.drawable.chiori_wrong);

        // Furina
        correctDialogues.put("Furina De Fontaine", Arrays.asList(
                "✅ Oui oui~ You truly admire me, don't you?",
                "✅ Ah, a perfect fan—you clearly have taste!",
                "✅ Hehe~ You remembered your Archon well!"
        ));
        wrongDialogues.put("Furina De Fontaine", Arrays.asList(
                "❌ Hmph! How dare you forget the great Furina!",
                "❌ Tsk! I'm the star of Fontaine, you know!",
                "❌ You wound me! Surely you jest..."
        ));

        // Clorinde
        correctDialogues.put("Clorinde", Arrays.asList(
                "✅ Sharp eyes, well done.",
                "✅ That was precise. Just like a good duel.",
                "✅ You recognize discipline when you see it."
        ));
        wrongDialogues.put("Clorinde", Arrays.asList(
                "❌ I expected more precision from you.",
                "❌ Careless mistake. That won't do.",
                "❌ You're not ready for the battlefield."
        ));

        // Navia
        correctDialogues.put("Navia", Arrays.asList(
                "✅ You've got a good eye for detail!",
                "✅ Hehe~ You got it, mon ami!",
                "✅ Knew you'd get it right. You're clever!"
        ));
        wrongDialogues.put("Navia", Arrays.asList(
                "❌ Oops! I guess I'm more forgettable than I thought?",
                "❌ Wrong? Come on! I'm not that obscure.",
                "❌ You missed the mark, partner!"
        ));

        // Raiden Ei
        correctDialogues.put("Raiden Ei", Arrays.asList(
                "✅ Your mind is as sharp as Musou Isshin.",
                "✅ You honor the Shogun with your memory.",
                "✅ That was a decisive strike of knowledge."
        ));
        wrongDialogues.put("Raiden Ei", Arrays.asList(
                "❌ Your answer lacks eternity.",
                "❌ Even lightning misfires, it seems.",
                "❌ A disappointment. Reflect and grow."
        ));

        // Hu Tao
        correctDialogues.put("Hu Tao", Arrays.asList(
                "✅ Aha! You're fun~",
                "✅ Ding ding ding~ Correct answer!",
                "✅ You're not scared of me, are ya?"
        ));
        wrongDialogues.put("Hu Tao", Arrays.asList(
                "❌ Wrong~ Maybe I'll haunt your dreams tonight!",
                "❌ Aww, you forgot me already?",
                "❌ Boo~ That's not the answer!"
        ));

        // Kamisato Ayaka
        correctDialogues.put("Kamisato Ayaka", Arrays.asList(
                "✅ Thank you for remembering me.",
                "✅ Your gracefulness is noted.",
                "✅ Such elegance in your choice."
        ));
        wrongDialogues.put("Kamisato Ayaka", Arrays.asList(
                "❌ Oh... I suppose I am easy to forget.",
                "❌ Let us strive for better together.",
                "❌ It's alright. Everyone makes mistakes."
        ));

        // Yelan
        correctDialogues.put("Yelan", Arrays.asList(
                "✅ Heh. You're sharper than you look.",
                "✅ Good. You're paying attention.",
                "✅ Nice call. You've done your homework."
        ));
        wrongDialogues.put("Yelan", Arrays.asList(
                "❌ Tsk. I expected more from you.",
                "❌ You fell for a bluff.",
                "❌ You'll need better instincts next time."
        ));

        // Arlecchino
        correctDialogues.put("Arlecchino", Arrays.asList(
                "✅ Heh. You know who you're dealing with.",
                "✅ Wise choice. Very wise.",
                "✅ You're not completely useless, I see."
        ));
        wrongDialogues.put("Arlecchino", Arrays.asList(
                "❌ You dare misidentify me?",
                "❌ Tsk... pathetic.",
                "❌ Wrong. I should burn this quiz down."
        ));

        // Naganohara Yoimiya
        correctDialogues.put("Naganohara Yoimiya", Arrays.asList(
                "✅ Yay~ You got it right!",
                "✅ Haha! You're good at this!",
                "✅ Boom! That's the right answer!"
        ));
        wrongDialogues.put("Naganohara Yoimiya", Arrays.asList(
                "❌ Oh no! Wrong firework!",
                "❌ Oopsie! That wasn't me!",
                "❌ Missed it! Better luck next time!"
        ));

        // Chiori
        correctDialogues.put("Chiori", Arrays.asList(
                "✅ Impeccable taste, darling~",
                "✅ You noticed the details. Très chic!",
                "✅ That's correct—fashion never lies."
        ));
        wrongDialogues.put("Chiori", Arrays.asList(
                "❌ Wrong. That look is a fashion disaster.",
                "❌ Mon dieu! Do you not recognize beauty?",
                "❌ Sigh... perhaps you need a makeover."
        ));

        // Get level from intent
        if (getIntent() != null && getIntent().hasExtra("level")) {
            level = getIntent().getStringExtra("level");
        }

        // Set millisUntilFinished based on level
        switch (level) {
            case "easy":
                millisUntilFinished = 10000;
                break;
            case "medium":
                millisUntilFinished = 6000;
                break;
            case "hard":
                millisUntilFinished = 4000;
                break;
            default:
                millisUntilFinished = 10000;
        }

        // a random question
        Collections.shuffle(techList);
        points = 0;
        startGame();
    }

    private void startGame() {
        // Use millisUntilFinished as set in onCreate
        tvTimer.setText(getString(R.string.timer_seconds, millisUntilFinished / 1000));
        tvPoints.setText(getString(R.string.score_format, points, techList.size()));
        tvResult.setText("");
        generateQuestions(index);
        countDownTimer = new CountDownTimer(millisUntilFinished, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update tvTimer every 1 second to show the number of seconds remaining.
                tvTimer.setText(getString(R.string.timer_seconds, millisUntilFinished / 1000));
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
                    // Clear the dialogue
                    tvResult.setText("");
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
        ArrayList<String> techListTemp = new ArrayList<>(techList);
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

        Integer imageRes = map.get(techList.get(index));
        if (imageRes != null) {
            ivShowImage.setImageResource(imageRes);
        } else {
            // Optional: show a default image or do nothing
            Log.e("StartGameActivity", "Image resource is null for: " + techList.get(index));
        }
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
            // Clear the dialogue
            tvResult.setText("");
            // Go to GameOver screen with points
            Intent intent = new Intent(StartGameActivity.this, GameOverActivity.class);
            intent.putExtra("points", points);
            startActivity(intent);
            finish();
        } else {
            countDownTimer = null;
            tvResult.setText("");
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
        Random random = new Random();
        List<String> dialogList;

        if (answer.equals(correctAnswer)) {
            points++;
            tvPoints.setText(points + " / " + techList.size());
            dialogList = correctDialogues.get(correctAnswer);
            String randomDialogue = dialogList.get(random.nextInt(dialogList.size()));
            tvResult.setText(randomDialogue);
            ivShowImage.setImageResource(correctImageMap.get(correctAnswer));
        } else {
            dialogList = wrongDialogues.get(correctAnswer);
            String randomDialogue = dialogList.get(random.nextInt(dialogList.size()));
            tvResult.setText(randomDialogue);
            ivShowImage.setImageResource(wrongImageMap.get(correctAnswer));
        }
    }
}