package com.CrimsonKnightBlood.SciQuiz.Section;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

import android.view.View;

import com.CrimsonKnightBlood.SciQuiz.R;
import com.CrimsonKnightBlood.SciQuiz.Topic.EcologyMC;
import com.CrimsonKnightBlood.SciQuiz.Topic.EcologyTF;
public class EcologySection extends Activity
{
	Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_section);
	}

	public void MC(View v) {
		i = new Intent(this, EcologyMC.class);
		startActivity(i);
		finish();
	}

	public void TF(View v) {
		i = new Intent(this, EcologyTF.class);
		startActivity(i);
		finish();
	}

}
