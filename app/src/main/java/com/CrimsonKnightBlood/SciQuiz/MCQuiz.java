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
import com.CrimsonKnightBlood.SciQuiz.Quiz.MCAnswers;
import com.CrimsonKnightBlood.SciQuiz.Quiz.MCAstronomyChoices;
import com.CrimsonKnightBlood.SciQuiz.Quiz.MCQuestions;
import com.CrimsonKnightBlood.SciQuiz.Topic.AstronomyMC;

import java.util.Arrays;
import java.util.Collections;

public class MCQuiz extends Activity
{
    SharedPreferences sharedPreferences, appPreferences;
    SharedPreferences.Editor editor;
    MediaPlayer correctMP, wrongMP, endMP;
    LinearLayout lltitle, llitem;
    Button bttn1, bttn2, bttn3, bttn4;
    TextView txtTitle, txtScore, txtItem, txtQuestion;

    private final MCQuestions Q = new MCQuestions();
    private final MCAstronomyChoices C = new MCAstronomyChoices();
    private final MCAnswers A = new MCAnswers();
    private final int questionLength = Q.AstronomyQuestion.length;
    private final int startQuestion = questionLength + 1;
    private int score = 0;
    private int numItems = 1;
    private int random = 0;
    private int index;
    boolean activated;
    int color;
    private String answer;
    int[] arr = new int[questionLength];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: Implement this method
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplechoice_quiz);

        bttn1 = findViewById(R.id.mcbutton1);
        bttn2 = findViewById(R.id.mcbutton2);
        bttn3 = findViewById(R.id.mcbutton3);
        bttn4 = findViewById(R.id.mcbutton4);

        txtTitle = findViewById(R.id.mctitle);
        txtScore = findViewById(R.id.mcscore);
        txtItem = findViewById(R.id.mcitem);
        txtQuestion = findViewById(R.id.mcquestion);
        lltitle = findViewById(R.id.multiplechoicequizLinearLayout1);
        llitem = findViewById(R.id.multiplechoicequizLinearLayout2);

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

        txtTitle.setText("Astronomy");
        txtScore.setText(score);
        txtItem.setText(numItems + "/" + questionLength);

        for(int i = 0; i < questionLength; i++) {
            arr[i] = i;
        }

        Collections.shuffle(Arrays.asList(arr));

        Index(arr[random]);
        UpdateQuestion(index);

        bttn1.setOnClickListener(v -> handleButtonClick(bttn1));
        bttn2.setOnClickListener(v -> handleButtonClick(bttn2));
        bttn3.setOnClickListener(v -> handleButtonClick(bttn3));
        bttn4.setOnClickListener(v -> handleButtonClick(bttn4));
    }

    private void handleButtonClick(Button button) {
        if (numItems != questionLength) {
            int timeout = 1000;
            if (button.getText() == answer) {
                CorrectSound();
                button.setBackgroundResource(R.drawable.correct_greenbutton);
                score++;
                txtScore.setText(score);
                numItems++;
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    if (numItems < startQuestion) {
                        txtItem.setText(numItems + "/" + questionLength);
                        random++;
                        Index(arr[random]);
                        UpdateQuestion(index);
                    }
                }, timeout);
            } else {
                WrongSound();
                button.setBackgroundResource(R.drawable.wrong_redbutton);
                for (Button btn : Arrays.asList(bttn1, bttn2, bttn3, bttn4)) {
                    if (btn.getText() == answer) {
                        btn.setBackgroundResource(R.drawable.correct_greenbutton);
                    }
                }
                numItems++;
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    if (numItems < startQuestion) {
                        txtItem.setText(numItems + "/" + questionLength);
                        random++;
                        Index(arr[random]);
                        UpdateQuestion(index);
                    }
                }, timeout);
            }
            bttn1.setEnabled(false);
            bttn2.setEnabled(false);
            bttn3.setEnabled(false);
            bttn4.setEnabled(false);
        } else {
            if (button.getText() == answer) {
                CorrectSound();
                button.setBackgroundResource(R.drawable.correct_greenbutton);
                score++;
                txtScore.setText(score);
            } else {
                WrongSound();
                button.setBackgroundResource(R.drawable.wrong_redbutton);
                for (Button btn : Arrays.asList(bttn1, bttn2, bttn3, bttn4)) {
                    if (btn.getText() == answer) {
                        btn.setBackgroundResource(R.drawable.correct_greenbutton);
                    }
                }
            }
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
                lltitle.setBackgroundResource(R.color.blue_500);
                llitem.setBackgroundResource(R.color.lightblue_500);
                break;
            case 1:
                lltitle.setBackgroundResource(R.color.green_500);
                llitem.setBackgroundResource(R.color.lightgreen_500);
                break;
            case 2:
                lltitle.setBackgroundResource(R.color.purple_500);
                llitem.setBackgroundResource(R.color.pink_500);
                break;
            case 3:
                lltitle.setBackgroundResource(R.color.orange_500);
                llitem.setBackgroundResource(R.color.amber_500);
                break;
        }
    }

    public void BackButton(View v) {
        EXIT();
    }

    private void SAVE() {
        if (score > sharedPreferences.getInt("astro_mcscore", 0)) {
            editor.putInt("astro_mcscore", score);
            editor.commit();
        }
    }

    private void Index(int indx) {
        index = indx;
    }

    private void UpdateQuestion(int num) {
        txtQuestion.setText(Q.getAstronomyQuestion(num));
        bttn1.setText(C.getChoice1(num));
        bttn2.setText(C.getChoice2(num));
        bttn3.setText(C.getChoice3(num));
        bttn4.setText(C.getChoice4(num));
        answer = A.getAstronomyAnswer(num);

        bttn1.setBackgroundResource(R.drawable.button_style);
        bttn2.setBackgroundResource(R.drawable.button_style);
        bttn3.setBackgroundResource(R.drawable.button_style);
        bttn4.setBackgroundResource(R.drawable.button_style);

        bttn1.setEnabled(true);
        bttn2.setEnabled(true);
        bttn3.setEnabled(true);
        bttn4.setEnabled(true);
    }

    private void END() {
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build
                .setCancelable(false)
                .setTitle("Congratulations!!!")
                .setMessage("Your Score is " + score + " points")
                .setPositiveButton("Retry", (di, i) -> {
                    startActivity(new Intent(getApplicationContext(), AstronomyMC.class));
                    finish();
                })
                .setNegativeButton("Quit", (di, i) -> finish())
                .create().show();
        EndSound();
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
        // TODO: Implement this method
        EXIT();
    }

    @Override
    protected void onPause()
    {
        // TODO: Implement this method
        super.onPause();
        SAVE();
    }
}
