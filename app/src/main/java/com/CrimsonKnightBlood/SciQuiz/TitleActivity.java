package com.CrimsonKnightBlood.SciQuiz;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;

public class TitleActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent transition = new Intent(TitleActivity.this, SectionPageActivity.class);
				startActivity(transition);
			}
		}, 3000);
    }
}
