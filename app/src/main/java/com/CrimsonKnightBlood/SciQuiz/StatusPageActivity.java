package com.CrimsonKnightBlood.SciQuiz;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import com.CrimsonKnightBlood.SciQuiz.Quiz.MCQuestions;
import com.CrimsonKnightBlood.SciQuiz.Quiz.TFQuestions;

import java.text.DecimalFormat;
import java.util.Objects;

public class StatusPageActivity extends AppCompatActivity
{
	MCQuestions mc = new MCQuestions();
	TFQuestions tf = new TFQuestions();

	SharedPreferences sp, ap;
	SharedPreferences.Editor editor;

	TextView txt1, txt2, txt3, txt4, txt5;
	EditText ed;

	private Toolbar Tb;

	String username, name;
	float astroAVG, bioAVG, chemistAVG, earthAVG, physAVG;
	float astroHS, bioHS, chemistHS, earthHS, physHS;
	float astroPT, bioPT, chemistPT, earthPT, physPT;
	int astro = mc.AstronomyQuestion.length + tf.AstronomyQuestion.length;
	int bio = mc.BiologyQuestion.length + tf.BiologyQuestion.length;
	int chemist = mc.ChemistryQuestion.length + tf.ChemistryQuestion.length;
	int earth = mc.EarthScienceQuestion.length + tf.EarthScienceQuestion.length;
	int phys = mc.PhysicsQuestion.length + tf.PhysicsQuestion.length;
	
	int color;
	
	DecimalFormat df = new DecimalFormat("0.##");

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statuspage);

		Tb = findViewById(R.id.toolbar);
		setSupportActionBar(Tb);

		Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		
		Tb.setNavigationOnClickListener(v -> finish());

		txt1 = findViewById(R.id.astronomy_average);
		txt2 = findViewById(R.id.biology_average);
		txt3 = findViewById(R.id.chemistry_average);
		txt4 = findViewById(R.id.earthscience_average);
		txt5 = findViewById(R.id.physics_average);

		ed = findViewById(R.id.username_edittext);

		sp = getSharedPreferences("com.CrimsonKnightBlood.SciQuiz.sharedpreferences", Context.MODE_PRIVATE);
		ap = getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
		editor = sp.edit();
		
		color = ap.getInt("color_scheme", 0);
		ColorChange(color);
		
		Compute();
		Text();

		name = sp.getString("username", "Username");
		ed.setText(name);
		ed.addTextChangedListener(new TextWatcher() { 
				@Override 
				public void beforeTextChanged(CharSequence s, int start, int count, int after)
				{ 

				} 

				@Override 
				public void onTextChanged(CharSequence s, int start, int before, int count)
				{ 
					username = ed.getText().toString();
					editor.putString("username", username);
					editor.commit();
				} 

				@Override 
				public void afterTextChanged(Editable s)
				{ 

				}
			});

	}

	public void ColorChange(int clr) {
		if (clr == 0) {
			Tb.setBackgroundColor(ContextCompat.getColor(this, R.color.lightblue_500));
		} else if (clr == 1) {
			Tb.setBackgroundColor(ContextCompat.getColor(this, R.color.lightgreen_500));
		} else if (clr == 2) {
			Tb.setBackgroundColor(ContextCompat.getColor(this, R.color.pink_500));
		} else if (clr == 3) {
			Tb.setBackgroundColor(ContextCompat.getColor(this, R.color.amber_500));
		}
	}

	public void Compute()
	{
		astroPT = sp.getInt("astro_mcscore", 0) + sp.getInt("astro_tfscore", 0);
		bioPT = sp.getInt("bio_mcscore", 0) + sp.getInt("bio_tfscore", 0);
		chemistPT = sp.getInt("chemist_mcscore", 0) + sp.getInt("chemist_tfscore", 0);
		earthPT = sp.getInt("earth_mcscore", 0) + sp.getInt("earth_tfscore", 0);
		physPT = sp.getInt("phys_mcscore", 0) + sp.getInt("phystfscore", 0);

		astroAVG = astroPT / astro;
		bioAVG = bioPT / bio;
		chemistAVG = chemistPT / chemist;
		earthAVG = earthPT / earth;
		physAVG = physPT / phys;

		astroAVG *= 100;
		bioAVG *= 100;
		chemistAVG *= 100;
		earthAVG *= 100;
		physAVG *= 100;

		editor.putFloat("astro_avg", astroAVG);
		editor.putFloat("bio_avg", bioAVG);
		editor.putFloat("chemist_avg", chemistAVG);
		editor.putFloat("earth_avg", earthAVG);
		editor.putFloat("phs_avg", physAVG);
		editor.commit();

		astroHS = sp.getFloat("astro_avg", 0) * astro;
		bioHS = sp.getFloat("bio_avg", 0) * bio;
		chemistHS = sp.getFloat("chemist_avg", 0) * chemist;
		earthHS = sp.getFloat("earth_avg", 0) * earth;
		physHS = sp.getFloat("phys_avg", 0) * phys;
	}

	public void Text()
	{
		txt1.setText(df.format(astroAVG));
		if (astroAVG >= 90) {
			txt1.setTextColor(0xff00ff00);
		} else if (astroAVG >= 80 && astroAVG <= 89) {
			txt1.setTextColor(0xff0099ff);
		} else {
			txt1.setTextColor(0xffff0000);
		}

		txt2.setText(df.format(bioAVG));
		if (bioAVG >= 90) {
			txt2.setTextColor(0xff00ff00);
		} else if (bioAVG >= 80 && bioAVG <= 89) {
			txt2.setTextColor(0xff0099ff);
		} else {
			txt2.setTextColor(0xffff0000);
		}

		txt3.setText(df.format(chemistAVG));
		if (chemistAVG >= 90) {
			txt3.setTextColor(0xff00ff00);
		} else if (chemistAVG >= 80 && chemistAVG <= 89) {
			txt3.setTextColor(0xff0099ff);
		} else {
			txt3.setTextColor(0xffff0000);
		}

		txt4.setText(df.format(earthAVG));
		if (earthAVG >= 90) {
			txt4.setTextColor(0xff00ff00);
		} else if (earthAVG >= 80 && earthAVG <= 89) {
			txt4.setTextColor(0xff0099ff);
		} else {
			txt4.setTextColor(0xffff0000);
		}

		txt5.setText(df.format(physAVG));
		if (physAVG >= 90) {
			txt5.setTextColor(0xff00ff00);
		} else if (physAVG >= 80 && physAVG <= 89) {
			txt5.setTextColor(0xff0099ff);
		} else {
			txt5.setTextColor(0xffff0000);
		}
	}

}
