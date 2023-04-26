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

public class MCQuiz extends Activity {
    SharedPreferences sharedPreferences, appPreferences;
    SharedPreferences.Editor editor;
    MediaPlayer correctMP, wrongMP, endMP;
    LinearLayout llTitle, llItem;
    Button btn1, btn2, btn3, btn4;
    TextView txtTitle, txtScore, txtItem, txtQuestion;
    
    private int questionLength;
    private int score = 0;
    private int numItems = 1;
    private int index;
    boolean activated;
    int color;
    private String answer;
    private String cls = "";
    private List<String> questions;
    private Map<String, String> answers;
    private Map<String, List<String>> choices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplechoice_quiz);

        btn1 = findViewById(R.id.mcbutton1);
        btn2 = findViewById(R.id.mcbutton2);
        btn3 = findViewById(R.id.mcbutton3);
        btn4 = findViewById(R.id.mcbutton4);

        txtTitle = findViewById(R.id.mctitle);
        txtScore = findViewById(R.id.mcscore);
        txtItem = findViewById(R.id.mcitem);
        txtQuestion = findViewById(R.id.mcquestion);
        llTitle = findViewById(R.id.multiplechoicequizLinearLayout1);
        llItem = findViewById(R.id.multiplechoicequizLinearLayout2);

        sharedPreferences = getSharedPreferences("com.CrimsonKnightBlood.SciQuiz.sharedpreferences", Context.MODE_PRIVATE);
        appPreferences = getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        activated = appPreferences.getBoolean("activate_sound", true);
        color = appPreferences.getInt("color_scheme", 0);
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

        btn1.setOnClickListener(v -> handleButtonClick(btn1));
        btn2.setOnClickListener(v -> handleButtonClick(btn2));
        btn3.setOnClickListener(v -> handleButtonClick(btn3));
        btn4.setOnClickListener(v -> handleButtonClick(btn4));

        Intent intent = getIntent();
        cls = intent.getStringExtra("targetClass");

        int resource = 0;

        switch (cls) {
            case "Biology":
                resource = R.raw.mc_biology;
                break;
            case "EarthScience":
                resource = R.raw.mc_earthscience;
                break;
            case "Astronomy":
                resource = R.raw.mc_astronomy;
                break;
            case "Chemistry":
                resource = R.raw.mc_chemistry;
                break;
            case "Physics":
                resource = R.raw.mc_physics;
                break;
        }

        QACsvParser csv = new QACsvParser(getApplicationContext(), resource, ";",5);
        questions = new ArrayList<>(csv.getQuestions());
        answers = new HashMap<>(csv.getAnswers());
        choices = new HashMap<>(csv.getChoices());
        questionLength = questions.size();

        Collections.shuffle(questions);

        txtTitle.setText(cls);
        txtScore.setText(String.valueOf(score));
        txtItem.setText(numItems + "/" + questionLength);
        UpdateQuestion(index);
    }

    private void handleButtonClick(Button button) {
        if (button.getText().toString().equalsIgnoreCase(answer)) {
            CorrectSound();
            score++;
        } else {
            WrongSound();
        }

        for (Button b : Arrays.asList(btn1, btn2, btn3, btn4)) {
            if (b.getText().toString().equalsIgnoreCase(answer)) {
                b.setBackgroundResource(R.drawable.correct_greenbutton);
            } else {
                b.setBackgroundResource(R.drawable.wrong_redbutton);
            }
            b.setEnabled(false);
        }

        txtScore.setText(String.valueOf(score));

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
        if(score > sharedPreferences.getInt(cls.toLowerCase() + "_mcscore", 0)) {
            editor.putInt(cls.toLowerCase() + "_mcscore", score);
            editor.apply();
        }
    }

    private void UpdateQuestion(int num) {
        txtQuestion.setText(questions.get(num));
        List<String> choicesList = new ArrayList<>(choices.get(questions.get(num)));
        Collections.shuffle(choicesList);

        List<Button> buttonList = Arrays.asList(btn1, btn2, btn3, btn4);
        for (Button b : buttonList) {
            b.setText(choicesList.get(buttonList.indexOf(b)));
            b.setBackgroundResource(R.drawable.button_style);
            b.setEnabled(true);
        }

        answer = answers.get(questions.get(num));
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
    protected void onPause() {
        super.onPause();
        SAVE();
    }
}
