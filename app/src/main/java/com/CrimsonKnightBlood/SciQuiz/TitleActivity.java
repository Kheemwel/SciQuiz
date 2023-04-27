package com.CrimsonKnightBlood.SciQuiz;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.os.Looper;

public class TitleActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
			Intent transition = new Intent(TitleActivity.this, SectionPageActivity.class);
            finish();
            startActivity(transition);
		}, 3000);
    }
}
