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

import java.text.DecimalFormat;
import java.util.*;

public class StatusPageActivity extends AppCompatActivity {

	private SharedPreferences sharedPreferences, appPreferences;
	private SharedPreferences.Editor editor;

	private TextView txt1, txt2, txt3, txt4, txt5;
	private EditText edTxt;
	private Toolbar toolbar;

	private String username, name;
	float astroAverage, bioAverage, chemistAverage, earthAverage, physAverage;
	int astroTotal, bioTotal, chemistTotal, earthTotal, physTotal;
	int color;
	private final DecimalFormat decimalFormat = new DecimalFormat("0.##");
	public final String[] SUBJECTS = {"astronomy", "biology", "chemistry", "earthscience", "physics"};

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

		astroTotal = getQuestionsLength(R.raw.mc_astronomy, 5) + getQuestionsLength(R.raw.tf_astronomy, 1);
		bioTotal = getQuestionsLength(R.raw.mc_astronomy, 5) + getQuestionsLength(R.raw.tf_biology, 1);
		chemistTotal = getQuestionsLength(R.raw.mc_astronomy, 5) + getQuestionsLength(R.raw.tf_chemistry, 1);
		earthTotal = getQuestionsLength(R.raw.mc_astronomy, 5) + getQuestionsLength(R.raw.tf_earthscience, 1);
		physTotal = getQuestionsLength(R.raw.mc_astronomy, 5) + getQuestionsLength(R.raw.tf_physics, 1);
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
		Map<String, Integer> mcScores = new HashMap<>();
		Map<String, Integer> tfScores = new HashMap<>();
		Map<String, Integer> points = new HashMap<>();
		Map<String, Float> averages = new HashMap<>();
		ArrayList<String> subjectList = new ArrayList<>(Arrays.asList(SUBJECTS));
		int[] totals = {astroTotal, bioTotal, chemistTotal, earthTotal, physTotal};

		for (String s : subjectList) {
			mcScores.put(s, sharedPreferences.getInt(s + "_mcscore", 0));
			tfScores.put(s, sharedPreferences.getInt(s + "_tfscore", 0));
			points.put(s, (mcScores.get(s) + tfScores.get(s)));
			averages.put(s, ((float) points.get(s) / totals[subjectList.indexOf(s)]) * 100);
			editor.putFloat(s + "_avg", averages.get(s));
		}
		editor.apply();

		astroAverage = sharedPreferences.getFloat("astronomy_avg", 0);
		bioAverage = sharedPreferences.getFloat("biology_avg", 0);
		chemistAverage = sharedPreferences.getFloat("chemistry_avg", 0);
		earthAverage = sharedPreferences.getFloat("earthscience_avg", 0);
		physAverage = sharedPreferences.getFloat("physics_avg", 0);
	}

	public void Text()
	{
		setTextAndColor(txt1, astroAverage);
		setTextAndColor(txt2, bioAverage);
		setTextAndColor(txt3, chemistAverage);
		setTextAndColor(txt4, earthAverage);
		setTextAndColor(txt5, physAverage);
	}

	private void setTextAndColor(TextView textView, double average) {
		textView.setText(decimalFormat.format(average));
		int textColor = Color.RED;
		if (average >= 90) {
			textColor = Color.GREEN;
		} else if (average >= 80 && average <= 89) {
			textColor = Color.YELLOW;
		}
		textView.setTextColor(textColor);
	}
}
