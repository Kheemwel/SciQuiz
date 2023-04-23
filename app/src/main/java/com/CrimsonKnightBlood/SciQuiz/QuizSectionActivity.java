package com.CrimsonKnightBlood.SciQuiz;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.CrimsonKnightBlood.SciQuiz.R;
import com.CrimsonKnightBlood.SciQuiz.Topic.*;

public class QuizSectionActivity extends Activity {
    private Class<?> TFClass = null;
    private Class<?> MCClass = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_section);

        Intent intent = getIntent();
        String cls = intent.getStringExtra("targetClass");
        switch (cls) {
            case "BiologySection":
                TFClass = BiologyTF.class;
                MCClass = BiologyMC.class;
                break;
            case "EarthScienceSection":
                TFClass = EarthScienceTF.class;
                MCClass = EarthScienceMC.class;
                break;
            case "AstronomySection":
                TFClass = AstronomyTF.class;
                MCClass = AstronomyMC.class;
                break;
            case "ChemistrySection":
                TFClass = ChemistryTF.class;
                MCClass = ChemistryMC.class;
                break;
            case "PhysicsSection":
                TFClass = PhysicsTF.class;
                MCClass = PhysicsMC.class;
                break;
        }
    }

    public void MC(View v) {
        startActivity(new Intent(this, MCClass));
        finish();
    }

    public void TF(View v) {
        startActivity(new Intent(this, TFClass));
        finish();
    }
}