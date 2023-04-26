package com.CrimsonKnightBlood.SciQuiz;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import com.CrimsonKnightBlood.SciQuiz.Quiz.MCQuestions;

import java.text.DecimalFormat;
import java.util.Objects;

public class StatusPageActivity extends AppCompatActivity {

	private SharedPreferences sharedPreferences, appPreferences;
	private SharedPreferences.Editor editor;

	private TextView txt1, txt2, txt3, txt4, txt5;
	private EditText edTxt;
	private Toolbar toolbar;

	private String username, name;
	float astroAverage, bioAverage, chemistAverage, earthAverage, physAverage;
	float astroHighScore, bioHighScore, chemistHighScore, earthHighScore, physHighScore;
	float astroPoints, bioPoints, chemistPoints, earthPoints, physPoints;
	int astroTotal, bioTotal, chemistTotal, earthTotal, physTotal;
	int color;
	private final DecimalFormat decimalFormat = new DecimalFormat("0.##");

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statuspage);

		toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		
		toolbar.setNavigationOnClickListener(v -> finish());

		txt1 = findViewById(R.id.astronomy_average);
		txt2 = findViewById(R.id.biology_average);
		txt3 = findViewById(R.id.chemistry_average);
		txt4 = findViewById(R.id.earthscience_average);
		txt5 = findViewById(R.id.physics_average);

		edTxt = findViewById(R.id.username_edittext);

		sharedPreferences = getSharedPreferences("com.CrimsonKnightBlood.SciQuiz.sharedpreferences", Context.MODE_PRIVATE);
		appPreferences = getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		
		color = appPreferences.getInt("color_scheme", 0);
		ColorChange(color);

		name = sharedPreferences.getString("username", "Username");
		edTxt.setText(name);
		edTxt.addTextChangedListener(new TextWatcher() {
				@Override 
				public void beforeTextChanged(CharSequence s, int start, int count, int after)
				{ 

				} 

				@Override 
				public void onTextChanged(CharSequence s, int start, int before, int count)
				{ 
					username = edTxt.getText().toString();
					editor.putString("username", username);
					editor.commit();
				} 

				@Override 
				public void afterTextChanged(Editable s)
				{ 

				}
			});

		MCQuestions mc = new MCQuestions();
		astroTotal = mc.AstronomyQuestion.length + getQuestionsLength(R.raw.tf_astronomy, 1);
		bioTotal = mc.BiologyQuestion.length + getQuestionsLength(R.raw.tf_biology, 1);
		chemistTotal = mc.ChemistryQuestion.length + getQuestionsLength(R.raw.tf_chemistry, 1);
		earthTotal = mc.EarthScienceQuestion.length + getQuestionsLength(R.raw.tf_earthscience, 1);
		physTotal = mc.PhysicsQuestion.length + getQuestionsLength(R.raw.tf_physics, 1);

		Compute();
		Text();
	}
	
	private int getQuestionsLength(int resource, int answerIndex) {
		QACsvParser csv = new QACsvParser(getApplicationContext(), resource,";", answerIndex);
		return csv.getQuestions().size();
	}

	public void ColorChange(int clr) {
		if (clr == 0) {
			toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.lightblue_500));
		} else if (clr == 1) {
			toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.lightgreen_500));
		} else if (clr == 2) {
			toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.pink_500));
		} else if (clr == 3) {
			toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.amber_500));
		}
	}

	public void Compute()
	{
		astroPoints = sharedPreferences.getInt("astro_mcscore", 0) + sharedPreferences.getInt("astro_tfscore", 0);
		bioPoints = sharedPreferences.getInt("bio_mcscore", 0) + sharedPreferences.getInt("bio_tfscore", 0);
		chemistPoints = sharedPreferences.getInt("chemist_mcscore", 0) + sharedPreferences.getInt("chemist_tfscore", 0);
		earthPoints = sharedPreferences.getInt("earth_mcscore", 0) + sharedPreferences.getInt("earth_tfscore", 0);
		physPoints = sharedPreferences.getInt("phys_mcscore", 0) + sharedPreferences.getInt("phystfscore", 0);

		astroAverage = astroPoints / astroTotal;
		bioAverage = bioPoints / bioTotal;
		chemistAverage = chemistPoints / chemistTotal;
		earthAverage = earthPoints / earthTotal;
		physAverage = physPoints / physTotal;

		astroAverage *= 100;
		bioAverage *= 100;
		chemistAverage *= 100;
		earthAverage *= 100;
		physAverage *= 100;

		editor.putFloat("astro_avg", astroAverage);
		editor.putFloat("bio_avg", bioAverage);
		editor.putFloat("chemist_avg", chemistAverage);
		editor.putFloat("earth_avg", earthAverage);
		editor.putFloat("phs_avg", physAverage);
		editor.commit();

		astroHighScore = sharedPreferences.getFloat("astro_avg", 0) * astroTotal;
		bioHighScore = sharedPreferences.getFloat("bio_avg", 0) * bioTotal;
		chemistHighScore = sharedPreferences.getFloat("chemist_avg", 0) * chemistTotal;
		earthHighScore = sharedPreferences.getFloat("earth_avg", 0) * earthTotal;
		physHighScore = sharedPreferences.getFloat("phys_avg", 0) * physTotal;
	}

	public void Text()
	{
		txt1.setText(decimalFormat.format(astroAverage));
		if (astroAverage >= 90) {
			txt1.setTextColor(Color.GREEN);
		} else if (astroAverage >= 80 && astroAverage <= 89) {
			txt1.setTextColor(Color.YELLOW);
		} else {
			txt1.setTextColor(Color.RED);
		}

		txt2.setText(decimalFormat.format(bioAverage));
		if (bioAverage >= 90) {
			txt2.setTextColor(Color.GREEN);
		} else if (bioAverage >= 80 && bioAverage <= 89) {
			txt2.setTextColor(Color.YELLOW);
		} else {
			txt2.setTextColor(Color.RED);
		}

		txt3.setText(decimalFormat.format(chemistAverage));
		if (chemistAverage >= 90) {
			txt3.setTextColor(Color.GREEN);
		} else if (chemistAverage >= 80 && chemistAverage <= 89) {
			txt3.setTextColor(Color.YELLOW);
		} else {
			txt3.setTextColor(Color.RED);
		}

		txt4.setText(decimalFormat.format(earthAverage));
		if (earthAverage >= 90) {
			txt4.setTextColor(Color.GREEN);
		} else if (earthAverage >= 80 && earthAverage <= 89) {
			txt4.setTextColor(Color.YELLOW);
		} else {
			txt4.setTextColor(Color.RED);
		}

		txt5.setText(decimalFormat.format(physAverage));
		if (physAverage >= 90) {
			txt5.setTextColor(Color.GREEN);
		} else if (physAverage >= 80 && physAverage <= 89) {
			txt5.setTextColor(Color.YELLOW);
		} else {
			txt5.setTextColor(Color.RED);
		}
	}
}
