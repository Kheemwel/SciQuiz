package com.CrimsonKnightBlood.SciQuiz;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.CrimsonKnightBlood.SciQuiz.R;
import com.CrimsonKnightBlood.SciQuiz.Topic.*;

public class QuizSectionActivity extends Activity {
    private String cls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_section);

        Intent intent = getIntent();
        cls = intent.getStringExtra("targetClass");
    }

    public void MC(View v) {
        Intent intent = new Intent(getApplicationContext(), MCQuiz.class);
        intent.putExtra("targetClass", cls);
        startActivity(intent);
        finish();
    }

    public void TF(View v) {
        Intent intent = new Intent(getApplicationContext(), TFQuiz.class);
        intent.putExtra("targetClass", cls);
        startActivity(intent);
        finish();
    }
}