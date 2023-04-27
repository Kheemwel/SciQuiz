package com.CrimsonKnightBlood.SciQuiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.*;

public class TFQuiz  extends Activity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private MediaPlayer correctMP, wrongMP, endMP;
    private LinearLayout llTitle, llItem;
    private Button btn1, btn2;
    private TextView txtScore, txtItem, txtQuestion;
    private int questionLength;
    private int score = 0;
    private int numItems = 1;
    private int index = 0;
    private boolean answer;
    private boolean activated;
    private String cls = "";
    private List<String> questions;
    private Map<String, String> answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trueorfalse_quiz);

        btn1 = findViewById(R.id.tfbutton1);
        btn2 = findViewById(R.id.tfbutton2);

        TextView txtTitle = findViewById(R.id.tftitle);
        txtScore = findViewById(R.id.tfscore);
        txtItem = findViewById(R.id.tfitem);
        txtQuestion = findViewById(R.id.tfquestion);
        llTitle = findViewById(R.id.trueorfalsequizLinearLayout1);
        llItem = findViewById(R.id.trueorfalsequizLinearLayout2);

        sharedPreferences = getSharedPreferences("com.CrimsonKnightBlood.SciQuiz.sharedpreferences", Context.MODE_PRIVATE);
        SharedPreferences appPreferences = getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        activated = appPreferences.getBoolean("activate_sound", true);
        int color = appPreferences.getInt("color_scheme", 0);
        ColorChange(color);

        correctMP = MediaPlayer.create(this, R.raw.correct);
        wrongMP = MediaPlayer.create(this, R.raw.wrong);
        endMP = MediaPlayer.create(this, R.raw.congratulation);

        correctMP.setOnCompletionListener(mp -> {
            correctMP.release();
            correctMP = MediaPlayer.create(getApplication(), R.raw.correct);
        });

        wrongMP.setOnCompletionListener(mp -> {
            wrongMP.release();
            wrongMP = MediaPlayer.create(getApplication(), R.raw.wrong);
        });

        endMP.setOnCompletionListener(mp -> {
            endMP.release();
            endMP = MediaPlayer.create(getApplication(), R.raw.congratulation);
        });

        View.OnClickListener onClick = v -> {
            Button clickedBtn = (Button) v;
            boolean isBtn1 = clickedBtn.getId() == R.id.tfbutton1;

            if (answer == isBtn1) {
                CorrectSound();
                if (isBtn1) {
                    btn1.setBackgroundResource(R.drawable.correct_greenbutton);
                    btn2.setBackgroundResource(R.drawable.wrong_redbutton);
                } else {
                    btn1.setBackgroundResource(R.drawable.wrong_redbutton);
                    btn2.setBackgroundResource(R.drawable.correct_greenbutton);
                }
                score++;
            } else {
                WrongSound();
                if (isBtn1) {
                    btn1.setBackgroundResource(R.drawable.wrong_redbutton);
                    btn2.setBackgroundResource(R.drawable.correct_greenbutton);
                } else {
                    btn1.setBackgroundResource(R.drawable.correct_greenbutton);
                    btn2.setBackgroundResource(R.drawable.wrong_redbutton);
                }
            }

            txtScore.setText(String.valueOf(score));
            btn1.setEnabled(false);
            btn2.setEnabled(false);

            if (numItems != questionLength) {
                numItems++;

                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    txtItem.setText(numItems + "/" + questionLength);
                    index++;
                    UpdateQuestion(index);
                }, 1000);

            } else {
                SAVE();
                END();
            }
        };

        btn1.setOnClickListener(onClick);
        btn2.setOnClickListener(onClick);

        Intent intent = getIntent();
        cls = intent.getStringExtra("targetClass");

        int resource = 0;

        switch (cls) {
            case "Biology":
                resource = R.raw.tf_biology;
                break;
            case "EarthScience":
                resource = R.raw.tf_earthscience;
                break;
            case "Astronomy":
                resource = R.raw.tf_astronomy;
                break;
            case "Chemistry":
                resource = R.raw.tf_chemistry;
                break;
            case "Physics":
                resource = R.raw.tf_physics;
                break;
        }

        QACsvParser csv = new QACsvParser(getApplicationContext(), resource, ";",1);
        questions = new ArrayList<>(csv.getQuestions());
        answers = new HashMap<>(csv.getAnswers());
        questionLength = questions.size();

        Collections.shuffle(questions);

        txtTitle.setText(cls);
        txtScore.setText(String.valueOf(score));
        txtItem.setText(numItems + "/" + questionLength);
        UpdateQuestion(index);
    }

    public void CorrectSound() {
        if(activated) {
            if(correctMP.isPlaying()) {
                correctMP.stop();
                correctMP = MediaPlayer.create(this, R.raw.correct);
                correctMP.start();
            } else {
                correctMP.start();
            }
        }
    }

    public void WrongSound() {
        if(activated) {
            if(wrongMP.isPlaying()) {
                wrongMP.stop();
                wrongMP = MediaPlayer.create(this, R.raw.wrong);
                wrongMP.start();
            } else {
                wrongMP.start();
            }
        }
    }

    public void EndSound() {
        if(activated) {
            if(endMP.isPlaying()) {
                endMP.stop();
                endMP = MediaPlayer.create(this, R.raw.congratulation);
                endMP.start();
            } else {
                endMP.start();
            }
        }
    }

    public void ColorChange(int clr) {
        switch(clr) {
            case 0:
                llTitle.setBackgroundResource(R.color.blue_500);
                llItem.setBackgroundResource(R.color.lightblue_500);
                break;
            case 1:
                llTitle.setBackgroundResource(R.color.green_500);
                llItem.setBackgroundResource(R.color.lightgreen_500);
                break;
            case 2:
                llTitle.setBackgroundResource(R.color.purple_500);
                llItem.setBackgroundResource(R.color.pink_500);
                break;
            case 3:
                llTitle.setBackgroundResource(R.color.orange_500);
                llItem.setBackgroundResource(R.color.amber_500);
                break;
        }
    }

    public void BackButton(View v) {
        EXIT();
    }

    private void SAVE() {
        if(score > sharedPreferences.getInt(cls.toLowerCase() + "_tfscore", 0)) {
            editor.putInt(cls.toLowerCase() + "_tfscore", score);
            editor.apply();
        }
    }

    private void UpdateQuestion(int num) {
        txtQuestion.setText(questions.get(num));
        answer = Boolean.parseBoolean(answers.get(questions.get(num)));

        btn1.setBackgroundResource(R.drawable.button_style);
        btn2.setBackgroundResource(R.drawable.button_style);

        btn1.setEnabled(true);
        btn2.setEnabled(true);
    }

    private void END() {
        EndSound();
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build
                .setCancelable(false)
                .setTitle("Congratulations!!!")
                .setMessage("Your Score is " + score + " points")
                .setPositiveButton("Retry", (di, i) -> {
                    recreate();
                })
                .setNegativeButton("Quit", (di, i) -> finish())
                .create().show();
    }

    private void EXIT() {
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build
                .setCancelable(false)
                .setTitle("Hmmm...😐")
                .setMessage("The quiz is not yet done. Are you sure you want to exit?")
                .setPositiveButton("YES", (di, i) -> {
                    SAVE();
                    finish();
                })
                .setNegativeButton("NO", (di, i) -> di.dismiss())
                .create().show();
    }

    @Override
    public void onBackPressed() {
        EXIT();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        SAVE();
    }
}

