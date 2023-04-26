package com.CrimsonKnightBlood.SciQuiz;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.content.Context;
import android.content.SharedPreferences;

import android.view.View;

import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class OptionsPageActivity extends AppCompatActivity
{
	private Toolbar Tb;
	SwitchCompat sw;
	ImageView img;
	RadioGroup colorscheme;
	RadioButton bluehue, greenhue, purplehue, orangehue;

	SharedPreferences sp;
	SharedPreferences.Editor editor;
	
	int color, scheme;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.optionspage);
		
		sp = getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
		editor = sp.edit();
		
		img = findViewById(R.id.sound_image);
		sw = findViewById(R.id.sound_option);
		colorscheme = findViewById(R.id.radiogroup1);
		bluehue = findViewById(R.id.radiobutton1);
		greenhue = findViewById(R.id.radiobutton2);
		purplehue = findViewById(R.id.radiobutton3);
		orangehue = findViewById(R.id.radiobutton4);
		
		sw.setChecked(sp.getBoolean("activate_sound", true));
		if(sw.isChecked()) {
			img.setImageResource(R.drawable.sound_on);
		} else {
			img.setImageResource(R.drawable.sound_off);
		}
		
		Tb = findViewById(R.id.toolbar);
		setSupportActionBar(Tb);
		
		Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
		Tb.setNavigationIcon(R.drawable.abc_ic_ab_back_material);

        Tb.setNavigationOnClickListener(v -> finish());
		
		color = sp.getInt("color_scheme", 0);
		if(color == 0) {
			bluehue.setChecked(true);
		} else if(color == 1) {
			greenhue.setChecked(true);
		} else if(color == 2) {
			purplehue.setChecked(true);
		} else if(color == 3) {
			orangehue.setChecked(true);
		}
		
		ColorChange(color);

		colorscheme.setOnCheckedChangeListener((rg, id) -> {
			if(id == R.id.radiobutton1) {
				scheme = 0;
				ColorScheme(scheme);
				ColorChange(scheme);
			} else if(id == R.id.radiobutton2) {
				scheme = 1;
				ColorScheme(scheme);
				ColorChange(scheme);
			} else if(id == R.id.radiobutton3) {
				scheme = 2;
				ColorScheme(scheme);
				ColorChange(scheme);
			} else if(id == R.id.radiobutton4) {
				scheme = 3;
				ColorScheme(scheme);
				ColorChange(scheme);
			}
		});

		try {
			PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			String version = pInfo.versionName;
			TextView versionTextView = findViewById(R.id.txtVersion);
			versionTextView.setText("SciQuiz v" + version);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void OptionSave() {
		if(sw.isChecked()) {
			img.setImageResource(R.drawable.sound_on);
			editor.putBoolean("activate_sound", true);
			editor.apply();
		} else {
			img.setImageResource(R.drawable.sound_off);
			editor.putBoolean("activate_sound", false);
			editor.apply();
		}
	}
	
	public void ColorScheme(int clr) {
		editor.putInt("color_scheme", clr);
	}
	
	public void ColorChange(int clr) {
		if(clr == 0) {
			Tb.setBackgroundResource(R.color.lightblue_500);
		} else if(clr == 1) {
			Tb.setBackgroundResource(R.color.lightgreen_500);
		} else if(clr == 2) {
			Tb.setBackgroundResource(R.color.pink_500);
		} else if(clr == 3) {
			Tb.setBackgroundResource(R.color.amber_500);
		}
	}
	
	public void SoundOption(View v) {
		OptionSave();
	}
	@Override
	protected void onPause()
	{
		// TODO: Implement this method
		super.onPause();
		OptionSave();
		ColorScheme(scheme);
	}
	
}
