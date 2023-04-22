package com.CrimsonKnightBlood.SciQuiz.Section;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

import android.view.View;

import com.CrimsonKnightBlood.SciQuiz.R;
import com.CrimsonKnightBlood.SciQuiz.Topic.BotanyMC;
import com.CrimsonKnightBlood.SciQuiz.Topic.BotanyTF;
public class BotanySection extends Activity
{
	Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_section);
	}

	public void MC(View v) {
		i = new Intent(this, BotanyMC.class);
		startActivity(i);
		finish();
	}

	public void TF(View v) {
		i = new Intent(this, BotanyTF.class);
		startActivity(i);
		finish();
	}

}
