package com.CrimsonKnightBlood.SciQuiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
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

import java.util.Arrays;
import java.util.Collections;

public class MCQuiz extends Activity {
    SharedPreferences sharedPreferences, appPreferences;
    SharedPreferences.Editor editor;
    MediaPlayer correctMP, wrongMP, endMP;
    LinearLayout llTitle, llItem;
    Button btn1, btn2, btn3, btn4;
    TextView txtTitle, txtScore, txtItem, txtQuestion;

    private final MCQuestions Q = new MCQuestions();
    private final MCAstronomyChoices C = new MCAstronomyChoices();
    private final MCAnswers A = new MCAnswers();
    private final int questionLength = Q.AstronomyQuestion.length;
    private final int numQuestions = questionLength + 1;
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

        txtTitle.setText("Astronomy");
        txtScore.setText(score);
        txtItem.setText(numItems + "/" + questionLength);

        for(int i = 0; i < questionLength; i++) {
            arr[i] = i;
        }

        Collections.shuffle(Arrays.asList(arr));

        Index(arr[random]);
        UpdateQuestion(index);
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
                    if (numItems < numQuestions) {
                        txtItem.setText(numItems + "/" + questionLength);
                        random++;
                        Index(arr[random]);
                        UpdateQuestion(index);
                    }
                }, timeout);
            } else {
                WrongSound();
                button.setBackgroundResource(R.drawable.wrong_redbutton);
                for (Button btn : Arrays.asList(btn1, btn2, btn3, btn4)) {
                    if (btn.getText() == answer) {
                        btn.setBackgroundResource(R.drawable.correct_greenbutton);
                    }
                }
                numItems++;
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    if (numItems < numQuestions) {
                        txtItem.setText(numItems + "/" + questionLength);
                        random++;
                        Index(arr[random]);
                        UpdateQuestion(index);
                    }
                }, timeout);
            }
            btn1.setEnabled(false);
            btn2.setEnabled(false);
            btn3.setEnabled(false);
            btn4.setEnabled(false);
        } else {
            if (button.getText() == answer) {
                CorrectSound();
                button.setBackgroundResource(R.drawable.correct_greenbutton);
                score++;
                txtScore.setText(score);
            } else {
                WrongSound();
                button.setBackgroundResource(R.drawable.wrong_redbutton);
                for (Button btn : Arrays.asList(btn1, btn2, btn3, btn4)) {
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
        btn1.setText(C.getChoice1(num));
        btn2.setText(C.getChoice2(num));
        btn3.setText(C.getChoice3(num));
        btn4.setText(C.getChoice4(num));
        answer = A.getAstronomyAnswer(num);

        btn1.setBackgroundResource(R.drawable.button_style);
        btn2.setBackgroundResource(R.drawable.button_style);
        btn3.setBackgroundResource(R.drawable.button_style);
        btn4.setBackgroundResource(R.drawable.button_style);

        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
    }

    private void END() {
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build
                .setCancelable(false)
                .setTitle("Congratulations!!!")
                .setMessage("Your Score is " + score + " points")
                .setPositiveButton("Retry", (di, i) -> {
//                    startActivity(new Intent(getApplicationContext(), AstronomyMC.class));
//                    finish();
                    recreate();
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
